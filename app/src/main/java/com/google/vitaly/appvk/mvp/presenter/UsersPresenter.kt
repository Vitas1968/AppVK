package com.google.vitaly.appvk.mvp.presenter

import com.google.vitaly.appvk.mvp.model.entity.User
import com.google.vitaly.appvk.mvp.model.repo.VkFriendsRepo
import com.google.vitaly.appvk.mvp.presenter.list.IUserListPresenter
import com.google.vitaly.appvk.mvp.view.UsersView
import com.google.vitaly.appvk.mvp.view.list.UserItemView
import com.google.vitaly.appvk.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class UsersPresenter(val mainThreadScheduler: Scheduler, val accessToken: String, val id: String): MvpPresenter<UsersView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var friendsRepo: VkFriendsRepo
    val usersListPresenter = UsersListPresenter()

    class UsersListPresenter : IUserListPresenter {
        val friends = mutableListOf<User>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = friends.size
        override fun bindView(view: UserItemView) {
            val friend = friends[view.pos]
            view.setFirstName(friend.firstName)
            view.setLastName(friend.lastName)
            view.setCity(friend.city.title)
            view.loadAvatar(friend.photo_50)
        }
    }
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.friends[itemView.pos]
            router.navigateTo(Screens.FriendDetailsScreen(user))
        }
    }
    fun loadData() {
        friendsRepo.getFriends(id,accessToken)
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                Timber.d("Размер списка друзей-> "+users.size.toString())
                usersListPresenter.friends.addAll(users)
                val tmp="pmp"
                viewState.updateList()
            }, {
                Timber.e(it)
            })
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}
package com.google.vitaly.appvk.mvp.presenter

import com.google.vitaly.appvk.mvp.model.entity.wall.WallItemText
import com.google.vitaly.appvk.mvp.model.repo.WallPostRepo
import com.google.vitaly.appvk.mvp.presenter.list.IPostsListPresenter
import com.google.vitaly.appvk.mvp.presenter.list.IUserListPresenter
import com.google.vitaly.appvk.mvp.view.FriendDetailsView
import com.google.vitaly.appvk.mvp.view.list.IPostWallView
import com.google.vitaly.appvk.mvp.view.list.UserItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FriendDetailsPresenter(val mainThreadScheduler: Scheduler, val accessToken: String, val id: String): MvpPresenter<FriendDetailsView>() {

    @Inject
    lateinit var router: Router
    @Inject lateinit var wallPostRepo: WallPostRepo

    val wallPostListPresenter = PostListPresenter()
    class PostListPresenter: IPostsListPresenter {
        val posts = mutableListOf<WallItemText>()
        override var itemClickListener: ((UserItemView) -> Unit)?=null
        override fun getCount() = posts.size

        override fun bindView(view: IPostWallView) {
            val post = posts[view.pos]
            view.setPostWall(post.text.takeIf {it!=""})
        }

    }
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setFirstName()
        viewState.loadAvatar()
        viewState.init()
        loadData()
        }

    fun loadData() {
        wallPostRepo.getPostsWall(id,accessToken)
            .observeOn(mainThreadScheduler)
            .subscribe({posts ->
                wallPostListPresenter.posts.addAll(posts)
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
package com.google.vitaly.appvk.di.user

import com.google.vitaly.appvk.di.repository.RepositorySubcomponent
import com.google.vitaly.appvk.di.user.modules.UserModule
import com.google.vitaly.appvk.di.wallposts.WallPostSubComponent
import com.google.vitaly.appvk.mvp.presenter.AuthPresenter
import com.google.vitaly.appvk.mvp.presenter.UsersPresenter
import com.google.vitaly.appvk.ui.adapter.UsersRVAdapter
import com.google.vitaly.appvk.ui.fragment.AuthFragment
import com.google.vitaly.appvk.ui.fragment.UsersFragment
import dagger.Subcomponent



@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun wallPostSubComponent(): WallPostSubComponent

    fun injectUsersFragment(usersFragment: UsersFragment)
    fun injectUsersPresenter(usersPresenter: UsersPresenter)
    fun injectUsersRVAdapter(adapter: UsersRVAdapter)

    fun injectAuthPresenter(authPresenter: AuthPresenter)
}
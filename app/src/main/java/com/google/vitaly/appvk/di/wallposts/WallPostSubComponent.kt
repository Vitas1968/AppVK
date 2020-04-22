package com.google.vitaly.appvk.di.wallposts

import com.google.vitaly.appvk.di.wallposts.module.WallPostsModule
import com.google.vitaly.appvk.mvp.presenter.FriendDetailsPresenter
import com.google.vitaly.appvk.ui.fragment.FriendDetailsFragment
import dagger.Subcomponent

@WallPostsScope
@Subcomponent(
    modules = [
        WallPostsModule::class
    ]
)
interface WallPostSubComponent {
    fun injectFragment(friendDetailsFragment: FriendDetailsFragment)
    fun injectPresenter(friendDetailsPresenter: FriendDetailsPresenter)
}
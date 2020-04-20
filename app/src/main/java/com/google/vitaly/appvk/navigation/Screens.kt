package com.google.vitaly.appvk.navigation

import com.google.vitaly.appvk.mvp.model.entity.User
import com.google.vitaly.appvk.ui.fragment.AuthFragment
import com.google.vitaly.appvk.ui.fragment.FriendDetailsFragment
import com.google.vitaly.appvk.ui.fragment.UsersFragment


import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class AuthScreen() : SupportAppScreen() {
        override fun getFragment() = AuthFragment.newInstance()
    }
    class UsersScreen(val accessToken: String,val userId: String) : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance(accessToken,userId)
    }
    class FriendDetailsScreen(val friend: User) : SupportAppScreen() {
        override fun getFragment() = FriendDetailsFragment.newInstance(friend)
    }



}
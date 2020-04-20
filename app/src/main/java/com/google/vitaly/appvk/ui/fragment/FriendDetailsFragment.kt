package com.google.vitaly.appvk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.vitaly.appvk.R
import com.google.vitaly.appvk.mvp.model.entity.User
import com.google.vitaly.appvk.mvp.presenter.FriendDetailsPresenter
import com.google.vitaly.appvk.mvp.view.FriendDetailsView
import com.google.vitaly.appvk.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class FriendDetailsFragment : MvpAppCompatFragment(), FriendDetailsView, BackButtonListener {

    companion object {
        private const val USER_KEY = "user"
        fun newInstance(friend: User) = FriendDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_KEY, friend)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter:FriendDetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_details, container, false)
    }

    override fun backClicked(): Boolean {
        TODO("Not yet implemented")
    }

}

package com.google.vitaly.appvk.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.vitaly.appvk.R
import com.google.vitaly.appvk.mvp.model.image.IImageLoader
import com.google.vitaly.appvk.mvp.presenter.UsersPresenter
import com.google.vitaly.appvk.mvp.view.UsersView
import com.google.vitaly.appvk.ui.App
import com.google.vitaly.appvk.ui.BackButtonListener
import com.google.vitaly.appvk.ui.adapter.UsersRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class UsersFragment(): MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        const val ACCESS_TOKEN = "accessToken"
        const val USER_ID = "userId"
        fun newInstance(accessToken: String, userId: String) = UsersFragment().apply {
            arguments=Bundle()
            arguments?.let { it.putString(ACCESS_TOKEN,accessToken) }
            arguments?.let { it.putString(USER_ID,userId)}

        }
    }
    @InjectPresenter
    lateinit var presenter: UsersPresenter

    var adapter: UsersRVAdapter? = null
    private val userComponent = App.instance.userComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userComponent.injectUsersFragment(this)
    }
    @ProvidePresenter
    fun providePresenter() :UsersPresenter {
        val at=arguments?.let {it.getString(ACCESS_TOKEN, "at")}
        val uid=arguments?.let {it.getString(USER_ID, "uid")}
        return UsersPresenter(AndroidSchedulers.mainThread(), at!!, uid!!).apply {
            userComponent.injectUsersPresenter(this)
        }
    }

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
            userComponent.injectUsersRVAdapter(this)
        }
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backClicked(): Boolean {
        presenter.backClicked()
        return false
    }

}
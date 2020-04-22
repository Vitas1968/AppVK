package com.google.vitaly.appvk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.vitaly.appvk.R
import com.google.vitaly.appvk.mvp.model.entity.User
import com.google.vitaly.appvk.mvp.model.image.IImageLoader
import com.google.vitaly.appvk.mvp.presenter.FriendDetailsPresenter
import com.google.vitaly.appvk.mvp.view.FriendDetailsView
import com.google.vitaly.appvk.ui.App
import com.google.vitaly.appvk.ui.BackButtonListener
import com.google.vitaly.appvk.ui.adapter.PostWallRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_friend_details.*
import kotlinx.android.synthetic.main.item_user_card_view.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class FriendDetailsFragment : MvpAppCompatFragment(), FriendDetailsView, BackButtonListener {

    companion object {
        const val ACCESS_TOKEN = "accessToken"
        const val USER_ID = "userId"
        const val AVATAR_URL = "avatarUrl"
        const val USER_NAME = "userName"
        fun newInstance(accessToken: String, userId: String, avatarUrl: String, userName: String) = FriendDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(ACCESS_TOKEN,accessToken)
                putString(USER_ID,userId)
                putString(AVATAR_URL,avatarUrl)
                putString(USER_NAME,userName)
            }
        }
    }
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>
    @Inject
    lateinit var router: Router
    var adapter: PostWallRVAdapter? = null

    @InjectPresenter
    lateinit var presenter:FriendDetailsPresenter
    private val wallPostComponent = App.instance.wallPostComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_details, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wallPostComponent.injectFragment(this)
    }

    @ProvidePresenter
    fun providePresenter() :FriendDetailsPresenter {
        val at=arguments?.let {it.getString(ACCESS_TOKEN, "at")}
        val uid=arguments?.let {it.getString(USER_ID, "uid")}
        return FriendDetailsPresenter(AndroidSchedulers.mainThread(), at!!, uid!!).apply {
            wallPostComponent.injectPresenter(this)
        }
    }

    override fun init() {
        rv_wall_post.layoutManager = LinearLayoutManager(context)
        adapter = PostWallRVAdapter(presenter.wallPostListPresenter)
        rv_wall_post.adapter=adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun loadAvatar() {
        val avaUrl=arguments?.let {it.getString(AVATAR_URL, "au")}
        imageLoader.loadInto(avaUrl!!, iv_avatar)
    }

    override fun setFirstName() {
        tv_first_name.text=arguments?.let {it.getString(USER_NAME, "un")}
    }

    override fun backClicked()= presenter.backClicked()

}

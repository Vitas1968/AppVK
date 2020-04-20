package com.google.vitaly.appvk.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.vitaly.appvk.R
import com.google.vitaly.appvk.mvp.model.image.IImageLoader
import com.google.vitaly.appvk.mvp.presenter.list.IUserListPresenter
import com.google.vitaly.appvk.mvp.view.list.UserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.item_user.view.iv_avatar
import kotlinx.android.synthetic.main.item_user_card_view.view.*
import javax.inject.Inject

class UsersRVAdapter(val presenter: IUserListPresenter) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_card_view, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer,
        UserItemView {
        override var pos = -1
        override fun setFirstName(firstName: String) {
            containerView.first_name.text=firstName
        }

        override fun setLastName(lastName: String) {
            containerView.last_name.text=lastName
        }

        override fun setCity(cityName: String) {
            containerView.city_user.text=cityName
        }

        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, im_view_avatar)
        }
    }

}
package com.google.vitaly.appvk.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.vitaly.appvk.R
import com.google.vitaly.appvk.mvp.presenter.list.IPostsListPresenter
import com.google.vitaly.appvk.mvp.view.list.IPostWallView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.wall_post_card_view.view.*

class PostWallRVAdapter(val presenter: IPostsListPresenter) : RecyclerView.Adapter<PostWallRVAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wall_post_card_view, parent, false))

    override fun getItemCount()= presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, IPostWallView {
        override var pos= -1

        override fun setPostWall(post: String?) {
            containerView.wall_post.text= post
        }

    }
}
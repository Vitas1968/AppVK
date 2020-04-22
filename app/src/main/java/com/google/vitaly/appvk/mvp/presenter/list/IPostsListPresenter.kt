package com.google.vitaly.appvk.mvp.presenter.list

import com.google.vitaly.appvk.mvp.view.list.IPostWallView
import com.google.vitaly.appvk.mvp.view.list.UserItemView

interface IPostsListPresenter {
    var itemClickListener: ((UserItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: IPostWallView)
}
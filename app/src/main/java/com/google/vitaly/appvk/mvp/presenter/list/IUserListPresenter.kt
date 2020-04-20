package com.google.vitaly.appvk.mvp.presenter.list

import com.google.vitaly.appvk.mvp.view.list.UserItemView


interface IUserListPresenter {
    var itemClickListener: ((UserItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: UserItemView)
}
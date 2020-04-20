package ru.geekbrains.poplib.mvp.presenter.list

import ru.geekbrains.poplib.mvp.view.list.UserItemView

interface IUserListPresenter {
    var itemClickListener: ((UserItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: UserItemView)
}
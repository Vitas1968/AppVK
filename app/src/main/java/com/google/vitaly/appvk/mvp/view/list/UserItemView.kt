package ru.geekbrains.poplib.mvp.view.list

interface UserItemView {
    var pos: Int
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}
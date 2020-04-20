package com.google.vitaly.appvk.mvp.view.list

interface UserItemView {
    var pos: Int
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}
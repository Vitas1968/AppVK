package com.google.vitaly.appvk.mvp.view.list

interface UserItemView {
    var pos: Int
    fun setFirstName(firstName: String)
    fun setLastName(lastName: String)
    fun setCity(cityName: String)
    fun loadAvatar(url: String)
}
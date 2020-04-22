package com.google.vitaly.appvk.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FriendDetailsView: MvpView{
    fun init()
    fun updateList()
    fun loadAvatar()
    fun setFirstName()
}
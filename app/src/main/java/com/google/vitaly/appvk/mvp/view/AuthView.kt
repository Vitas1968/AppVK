package com.google.vitaly.appvk.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AuthView : MvpView {

    fun init()
    fun requestAccessToken(request: String)
}
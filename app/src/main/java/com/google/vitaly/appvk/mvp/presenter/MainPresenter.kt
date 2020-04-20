package com.google.vitaly.appvk.mvp.presenter

import com.google.vitaly.appvk.mvp.view.MainView
import com.google.vitaly.appvk.navigation.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(Screens.AuthScreen())
    }

    fun backClicked() {
        router.exit()
    }

}

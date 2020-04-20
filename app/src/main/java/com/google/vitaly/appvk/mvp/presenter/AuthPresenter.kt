package com.google.vitaly.appvk.mvp.presenter

import com.google.vitaly.appvk.mvp.view.AuthView
import com.google.vitaly.appvk.navigation.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

//запрос к API VK https://api.vk.com/method/friends.getOnline?v=5.52&access_token=

//запрос авторизации https://oauth.vk.com/authorize?
// client_id=5490057&
// display=page&redirect_uri=https://oauth.vk.com/blank.html&
// scope=friends&
// response_type=token&v=5.103

// ответ на запрос авторизации
//https://oauth.vk.com/blank.html
// #access_token=379caf77f0c288ba928d3e5dd113478f045879bb41b151bb36218a829569f020bce2170375394589f9433
// &expires_in=86400
// &user_id=587482032

@InjectViewState
class AuthPresenter: MvpPresenter<AuthView>() {
    @Inject
    lateinit var router: Router

//    @Inject
//    @Named("urlRequestAuth")
     val  urlRequestAuth="https://oauth.vk.com/authorize?" +
            "client_id=7400556&" +
            "display=page&redirect_uri=https://oauth.vk.com/blank.html&" +
            "scope=friends,photos,audio,wall,groups&" +
            "response_type=token&v=5.103"


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.requestAccessToken(urlRequestAuth)
    }

   fun getAccesToken(url:String){
       val accessToken= url.split("#")[1]
           .split("&")[0]
           .split("=")[1]
       val userId=url.split("#")[1]
           .split("&")[2]
           .split("=")[1]
       router.navigateTo(Screens.UsersScreen(accessToken,userId))
   }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}
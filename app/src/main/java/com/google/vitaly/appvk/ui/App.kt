package com.google.vitaly.appvk.ui

import android.app.Application
import com.google.vitaly.appvk.di.app.AppComponent
import com.google.vitaly.appvk.di.app.DaggerAppComponent
import com.google.vitaly.appvk.di.app.modules.AppModule
import com.google.vitaly.appvk.di.user.UserSubcomponent
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var instance: App

    }

    lateinit var appComponent: AppComponent
        private set

    private var tUserComponent: UserSubcomponent? = null

    val userComponent: UserSubcomponent
        get() = appComponent.userSubcomponent().also {
            tUserComponent = it
        }


//    val repositoryComponent: RepositorySubcomponent
//        get() = tUserComponent!!.repositorySubcomponent()

    override fun onCreate() {
        super.onCreate()
        instance = this

        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
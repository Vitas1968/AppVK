package com.google.vitaly.appvk.di.app

import com.google.vitaly.appvk.di.app.modules.*
import com.google.vitaly.appvk.di.user.UserSubcomponent
import com.google.vitaly.appvk.mvp.presenter.AuthPresenter
import com.google.vitaly.appvk.mvp.presenter.MainPresenter
import com.google.vitaly.appvk.ui.activity.MainActivity
import com.google.vitaly.appvk.ui.fragment.AuthFragment
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ImageModule::class,
        ApiModule::class,
        DatabaseModule::class

    ]
)
interface AppComponent {
    fun userSubcomponent(): UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)



    /*
    fun inject(repositoriesPresenter: RepositoriesPresenter)

    fun inject(repositoriesFragment: RepositoriesFragment)

    fun inject(repositoryPresenter: RepositoryPresenter)

     */
}
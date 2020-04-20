package com.google.vitaly.appvk.di.components

import com.google.vitaly.appvk.di.modules.AppModule
import com.google.vitaly.appvk.di.modules.CiceroneModule
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
       // RepoModule::class,
        CiceroneModule::class
       // ImageModule::class,
       // PresenterModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun injectAuthFragment(authFragment: AuthFragment)
    fun injectAuthPresenter(authPresenter: AuthPresenter)

    /*
    fun inject(repositoriesPresenter: RepositoriesPresenter)

    fun inject(repositoriesFragment: RepositoriesFragment)

    fun inject(repositoryPresenter: RepositoryPresenter)

     */
}
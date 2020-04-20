package com.google.vitaly.appvk.di.repository

import dagger.Subcomponent


@RepositoryScope
@Subcomponent(
    modules = [
       // RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
   // fun inject(userPresenter: UserPresenter)
   // fun inject(userFragment: UserFragment)
   // fun inject(repositoryPresenter: RepositoryPresenter)
}
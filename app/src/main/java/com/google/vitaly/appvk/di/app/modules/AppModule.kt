package com.google.vitaly.appvk.di.modules

import com.google.vitaly.appvk.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Singleton
    @Provides
    fun app(): App {
        return app
    }

}
package com.google.vitaly.appvk.di.app.modules

import androidx.room.Room
import com.google.vitaly.appvk.mvp.model.entity.room.db.Database
import com.google.vitaly.appvk.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App): Database {
        return Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .build()
    }

}
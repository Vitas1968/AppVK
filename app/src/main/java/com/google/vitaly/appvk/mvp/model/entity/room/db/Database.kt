package com.google.vitaly.appvk.mvp.model.entity.room.db

import android.content.Context
import androidx.room.RoomDatabase
import com.google.vitaly.appvk.mvp.model.entity.room.RoomCachedImage
import com.google.vitaly.appvk.mvp.model.entity.room.RoomCity
import com.google.vitaly.appvk.mvp.model.entity.room.RoomVkUser
import com.google.vitaly.appvk.mvp.model.entity.room.dao.ImageDao
import com.google.vitaly.appvk.mvp.model.entity.room.dao.UserDao


@androidx.room.Database(
    entities = [
        RoomVkUser::class,
        RoomCachedImage::class,
        RoomCity::class
    ],
    version = 3
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val imageDao: ImageDao


    companion object {
        const val DB_NAME = "Appvkdatabase.db"
    }
}
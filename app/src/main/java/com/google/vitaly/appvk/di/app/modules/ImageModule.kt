package com.google.vitaly.appvk.di.app.modules


import android.widget.ImageView
import com.google.vitaly.appvk.mvp.model.cache.image.IImageCache
import com.google.vitaly.appvk.mvp.model.cache.image.room.RoomImageCache
import com.google.vitaly.appvk.mvp.model.entity.room.db.Database
import com.google.vitaly.appvk.mvp.model.image.IImageLoader
import com.google.vitaly.appvk.mvp.network.NetworkStatus
import com.google.vitaly.appvk.ui.App
import com.google.vitaly.appvk.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named

@Module
class ImageModule {

    @Provides
    fun imageLoader(cache: IImageCache, networkStatus: NetworkStatus): IImageLoader<ImageView> {
        return GlideImageLoader(cache, networkStatus)
    }

    @Named("imageCachePath")
    @Provides
    fun imageCachePath(): String = (
            App.instance.externalCacheDir?.path
                ?: App.instance.getExternalFilesDir(null)?.path
                ?: App.instance.filesDir.path
            ) + File.separator + "image_cache"


    @Provides
    fun imageCache(@Named("imageCachePath") path: String, database: Database): IImageCache {
        return RoomImageCache(database, File(path))
    }

}
package ru.geekbrains.poplib.di.app.modules


import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.cache.image.IImageCache
import ru.geekbrains.poplib.mvp.model.cache.image.room.RoomImageCache
import ru.geekbrains.poplib.mvp.model.entity.room.db.Database
import ru.geekbrains.poplib.mvp.model.image.IImageLoader
import ru.geekbrains.poplib.mvp.model.network.NetworkStatus
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.image.GlideImageLoader
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
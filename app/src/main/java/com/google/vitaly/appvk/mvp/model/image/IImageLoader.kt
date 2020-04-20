package com.google.vitaly.appvk.mvp.model.image

import com.google.vitaly.appvk.mvp.model.cache.image.IImageCache


interface IImageLoader<T> {
    val cache: IImageCache
    fun loadInto(url: String, container: T)
}
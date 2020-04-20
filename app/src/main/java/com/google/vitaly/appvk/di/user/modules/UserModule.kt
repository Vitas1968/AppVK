package com.google.vitaly.appvk.di.user.modules

import com.google.vitaly.appvk.di.user.UserScope
import com.google.vitaly.appvk.mvp.model.api.IDataSource
import com.google.vitaly.appvk.mvp.model.cache.IVkUsersCache
import com.google.vitaly.appvk.mvp.model.cache.room.RoomVkUsersCache
import com.google.vitaly.appvk.mvp.model.entity.room.db.Database
import com.google.vitaly.appvk.mvp.model.repo.VkFriendsRepo
import com.google.vitaly.appvk.mvp.network.NetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
open class UserModule {

    @UserScope
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: NetworkStatus, cache: IVkUsersCache): VkFriendsRepo {
        return VkFriendsRepo(api, networkStatus, cache)
    }

    @UserScope
    @Provides
    fun usersCache(database: Database): IVkUsersCache {
        return RoomVkUsersCache(database)
    }

    /*
    @Named("urlRequestAuth")
    @Provides
    @UserScope
    fun urlRequestAuth()="https://oauth.vk.com/authorize?" +
            "client_id=7400556&" +
            "display=page&redirect_uri=https://oauth.vk.com/blank.html&" +
            "scope=friends,photos,audio,wall,groups&" +
            "response_type=token&v=5.103"

     */
}
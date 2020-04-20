package com.google.vitaly.appvk.di.repository

import com.google.vitaly.appvk.mvp.model.api.IDataSource
import com.google.vitaly.appvk.mvp.model.cache.IVkUsersCache
import com.google.vitaly.appvk.mvp.model.cache.room.RoomVkUsersCache
import com.google.vitaly.appvk.mvp.model.entity.room.db.Database
import com.google.vitaly.appvk.mvp.model.repo.VkFriendsRepo
import com.google.vitaly.appvk.mvp.network.NetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    fun repositoriesCache(database: Database): IVkUsersCache {
        return RoomVkUsersCache(database)
    }

    @RepositoryScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: NetworkStatus, cache: IVkUsersCache): VkFriendsRepo {
        return VkFriendsRepo(api, networkStatus, cache)
    }

}
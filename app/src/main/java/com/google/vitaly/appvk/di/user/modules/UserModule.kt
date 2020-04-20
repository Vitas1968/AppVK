package ru.geekbrains.poplib.di.user.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.di.user.UserScope
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.poplib.mvp.model.cache.room.RoomGithubUsersCache
import ru.geekbrains.poplib.mvp.model.entity.room.db.Database
import ru.geekbrains.poplib.mvp.model.network.NetworkStatus
import ru.geekbrains.poplib.mvp.model.repo.GithubUsersRepo

@Module
open class UserModule {

    @UserScope
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: NetworkStatus, cache: IGithubUsersCache): GithubUsersRepo {
        return GithubUsersRepo(api, networkStatus, cache)
    }

    @UserScope
    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }
}
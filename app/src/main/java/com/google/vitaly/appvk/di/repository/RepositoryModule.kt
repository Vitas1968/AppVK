package ru.geekbrains.poplib.di.repository.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.cache.IGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.entity.room.db.Database
import ru.geekbrains.poplib.mvp.model.network.NetworkStatus
import ru.geekbrains.poplib.mvp.model.repo.GithubRepositoriesRepo

@Module
open class RepositoryModule {

    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }

    @RepositoryScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: NetworkStatus, cache: IGithubRepositoriesCache): GithubRepositoriesRepo {
        return GithubRepositoriesRepo(api, networkStatus, cache)
    }

}
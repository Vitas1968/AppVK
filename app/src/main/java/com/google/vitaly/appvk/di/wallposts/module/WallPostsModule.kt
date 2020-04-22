package com.google.vitaly.appvk.di.wallposts.module

import com.google.vitaly.appvk.di.wallposts.WallPostsScope
import com.google.vitaly.appvk.mvp.model.api.IDataSource
import com.google.vitaly.appvk.mvp.model.repo.WallPostRepo
import dagger.Module
import dagger.Provides

@Module
open class WallPostsModule {
    @WallPostsScope
    @Provides
    open fun wallPostRepo(api: IDataSource): WallPostRepo {
        return WallPostRepo(api)
    }
}
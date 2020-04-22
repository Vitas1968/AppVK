package com.google.vitaly.appvk.mvp.model.repo

import com.google.vitaly.appvk.mvp.model.api.IDataSource
import com.google.vitaly.appvk.mvp.model.entity.wall.WallItemText
import com.google.vitaly.appvk.mvp.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class WallPostRepo(val api: IDataSource) {

    fun getPostsWall(userId: String, accessToken: String) =
        api.getPostsWall(userId,accessToken)
            .map{
               return@map it.response.items
            }.subscribeOn(Schedulers.io())
}
package com.google.vitaly.appvk.mvp.model.cache

import com.google.vitaly.appvk.mvp.model.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single



interface IVkUsersCache {
    fun getUser(id: Int): Single<User>
    fun putUser(user: User): Completable

    fun getUsers(): Single<List<User>>
    fun putUsers(users: List<User>): Completable
}
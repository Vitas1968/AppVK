package com.google.vitaly.appvk.mvp.model.repo


import com.google.vitaly.appvk.mvp.model.api.IDataSource
import com.google.vitaly.appvk.mvp.model.cache.IVkUsersCache
import com.google.vitaly.appvk.mvp.model.entity.User
import com.google.vitaly.appvk.mvp.model.entity.room.db.Database
import com.google.vitaly.appvk.mvp.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.Single.create
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

/*
create<List<User>> { response.response.items }.flatMap { users ->
                    Timber.d("Размер списка друзей-> "+users.size.toString())
                    return@flatMap cache.putUsers(users).toSingleDefault(users)
                }
 */
class VkFriendsRepo(val api: IDataSource, val networkStatus: NetworkStatus, val cache: IVkUsersCache) {

    fun getFriends(userId: String, accessToken: String) =networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getFriends(userId,accessToken).map { response ->
                return@map response.response.items}.flatMap {users->
                cache.putUsers(users).toSingleDefault(users)
            }
        }else{
            cache.getUsers()
        }
}.subscribeOn(Schedulers.io())

    /*networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getFriends()
                .flatMap { users ->
                    return@flatMap cache.putUsers(users).toSingleDefault(users)
                }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
    */

    /*
    fun getUser(username: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUser(username)
                .flatMap { user ->
                    return@flatMap cache.putUser(user).toSingleDefault(user)
                }
        } else {
           cache.getUser(username)
        }
    }.subscribeOn(Schedulers.io())
     */
}

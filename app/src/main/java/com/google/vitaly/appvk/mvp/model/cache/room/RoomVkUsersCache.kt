package com.google.vitaly.appvk.mvp.model.cache.room

import com.google.vitaly.appvk.mvp.model.cache.IVkUsersCache
import com.google.vitaly.appvk.mvp.model.entity.City
import com.google.vitaly.appvk.mvp.model.entity.User
import com.google.vitaly.appvk.mvp.model.entity.room.RoomCity
import com.google.vitaly.appvk.mvp.model.entity.room.RoomVkUser
import com.google.vitaly.appvk.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RoomVkUsersCache(val database: Database) : IVkUsersCache {

    override fun getUser(id: Int) = Single.fromCallable {
        return@fromCallable database.userDao.findById(id)?.run {
            val roomCity=database.userDao.findCityById(cityId)
            val city=roomCity?.let {transformRoomCityToCity(it)}
            User(id,
                firstNname,
                lastName,
                isClosed,
                canAccessClosed,
                nickname,
                online,
                bdate,
                photo_50,
                city!!,
                trackCode) }
            ?: throw RuntimeException("No such user in cache")
    }.subscribeOn(Schedulers.io())

    override fun putUser(user: User) = Completable.fromAction {

        val roomCity=transformCityToRoomCity(user)
        val roomUser = database.userDao.findById(user.id)?.apply {

            id = user.id
            firstNname = user.firstName
            lastName = user.lastName
            isClosed = user.isClosed
            canAccessClosed = user.canAccessClosed
            nickname = user.nickname
            online = user.online
            bdate = user.bdate ?: "no bdate"
            photo_50 = user.photo_50
            cityId=user.city.id
           // city = roomCity
            trackCode = user.trackCode
        } ?: RoomVkUser(user.id,
            user.firstName,
            user.lastName,
            user.isClosed,
            user.canAccessClosed,
            user.nickname,
            user.online,
            user.bdate ?: "no bdate",
            user.photo_50,
            user.city.id,
            user.trackCode)
        val rCity=getRoomCity(roomUser.cityId)!!
        database.userDao.insertCity(rCity)
        database.userDao.insert(roomUser)
    }.subscribeOn(Schedulers.io())

    private fun getRoomCity(cityId: Int) =
        database.userDao.findCityById(cityId)

    override fun getUsers()= Single.fromCallable {
        return@fromCallable database.userDao.getAll().map { roomVkUser ->
            roomVkUser.run {
                val roomCity=database.userDao.findCityById(cityId)
                val city=roomCity?.let{transformRoomCityToCity(it)}
                User(id,
                    firstNname,
                    lastName,
                    isClosed,
                    canAccessClosed,
                    nickname,
                    online,
                    bdate,
                    photo_50,
                    city!!,
                    trackCode) }
             }
    }.subscribeOn(Schedulers.io())


    override fun putUsers(users: List<User>) = Completable.fromAction {
        val roomUsers = users.map { user ->
            val cityId= transformCityToRoomCity(user).run {
                id }
            RoomVkUser(user.id,
                user.firstName,
                user.lastName,
                user.isClosed,
                user.canAccessClosed,
            user.nickname,user.
                online,
                user.bdate ?: "no bdate",
                user.photo_50,
                cityId,
                user.trackCode).apply {
//                if(getRoomCity(cityId)==null) {
//                    RoomCity(cityId, user.city.title)?.let {
//                        database.userDao.insertCity(it) }
//                }
                getRoomCity(cityId) ?: let {
                    RoomCity(cityId, user.city.title).let {
                        database.userDao.insertCity(it) }
                }

            } }
        database.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    private fun transformCityToRoomCity(user: User)=
        user.run {
            RoomCity(city.id, city.title)
        }
    private fun transformRoomCityToCity(roomCity: RoomCity)=
        roomCity.run { City(id,title) }
}
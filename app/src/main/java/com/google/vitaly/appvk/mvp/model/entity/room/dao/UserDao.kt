package com.google.vitaly.appvk.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.google.vitaly.appvk.mvp.model.entity.room.RoomCity
import com.google.vitaly.appvk.mvp.model.entity.room.RoomVkUser


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomVkUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: RoomVkUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomVkUser>)

    @Update
    fun update(user: RoomVkUser)

    @Update
    fun update(vararg user: RoomVkUser)

    @Update
    fun update(users: List<RoomVkUser>)

    @Delete
    fun delete(user: RoomVkUser)

    @Delete
    fun delete(vararg user: RoomVkUser)

    @Delete
    fun delete(users: List<RoomVkUser>)

    @Query("SELECT * FROM RoomVkUser")
    fun getAll(): List<RoomVkUser>

    @Query("SELECT * FROM RoomVkUser WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomVkUser?

    @Query("SELECT * FROM RoomCity WHERE id = :cityId LIMIT 1")
    fun findCityById(cityId: Int): RoomCity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(user: RoomCity)
}
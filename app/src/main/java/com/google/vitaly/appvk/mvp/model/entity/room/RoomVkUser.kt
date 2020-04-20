package com.google.vitaly.appvk.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomCity::class,
        parentColumns = ["id"],
        childColumns = ["cityId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomVkUser(
    @PrimaryKey  var id: Int,
    var firstNname: String,
    var lastName: String,
    var isClosed: Boolean,
    var canAccessClosed: Boolean,
    var nickname: String,
    var online: Int,
    var bdate: String?,
    var photo_50: String,
    var cityId: Int,
    var trackCode: String
)
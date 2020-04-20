package com.google.vitaly.appvk.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class RoomCity(

    @PrimaryKey
    val id: Int,
    val title: String
)
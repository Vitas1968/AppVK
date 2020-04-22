package com.google.vitaly.appvk.mvp.model.entity.wall

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize


abstract class Item : Parcelable {
    abstract val id: Int
    abstract val fromId: Int
    abstract val ownerId: Int
    abstract val date: Long
    abstract val postType: String
    abstract val text: String
}


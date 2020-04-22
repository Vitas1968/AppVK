package com.google.vitaly.appvk.mvp.model.entity.wall

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WallItem(
    @Expose override val id: Int,
    @Expose override val fromId: Int,
    @Expose override val ownerId: Int,
    @Expose override val date: Long,
    @Expose override val postType: String,
    @Expose override val text: String
):Item()
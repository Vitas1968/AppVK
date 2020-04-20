package com.google.vitaly.appvk.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VkResponse(
    @Expose val items: List<User>,
    @Expose val count: Int
): Parcelable
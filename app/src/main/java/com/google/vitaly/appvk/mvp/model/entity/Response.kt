package com.google.vitaly.appvk.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    @Expose val response: VkResponse
) : Parcelable
package com.google.vitaly.appvk.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    @Expose val id: Int,
    @Expose val title: String
) : Parcelable
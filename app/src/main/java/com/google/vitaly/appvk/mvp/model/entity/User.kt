package com.google.vitaly.appvk.mvp.model.entity


import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @Expose val id:Int,
    @Expose val firstName: String,
    @Expose val lastName: String,
    @Expose val isClosed: Boolean,
    @Expose val canAccessClosed: Boolean,
    @Expose val nickname: String,
    @Expose val online: Int,
    @Expose val bdate: String?,
    @Expose val photo_50: String,
    @Expose val city: City,
    @Expose val trackCode: String
) : Parcelable
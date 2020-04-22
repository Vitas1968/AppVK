package com.google.vitaly.appvk.mvp.model.entity.wall

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WallResponse(@Expose val response: WResponse ):Parcelable
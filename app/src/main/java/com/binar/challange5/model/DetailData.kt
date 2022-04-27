package com.binar.challange5.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailData(
    val title :String,
    val desc : String,
    val photo :String
):Parcelable

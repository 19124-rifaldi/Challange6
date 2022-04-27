package com.binar.challange5.test

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MyContact (val nama:String , val noHp : Long): Parcelable

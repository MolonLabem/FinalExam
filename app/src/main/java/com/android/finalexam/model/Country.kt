package com.android.finalexam.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val countryID: Int,
    val countryName: String,
    val id: Int,
) : Parcelable

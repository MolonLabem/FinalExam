package com.android.finalexam.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val categoryID: Int,
    val categoryName: String
) : Parcelable
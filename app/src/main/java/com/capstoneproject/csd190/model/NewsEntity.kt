package com.capstoneproject.csd190.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsEntity(
    var newsId : Int,
    var title : String,
    var description: String,
    var imgSrc: String,
    var text : String
):Parcelable

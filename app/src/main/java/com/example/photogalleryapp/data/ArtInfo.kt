package com.example.photogalleryapp.data

import androidx.annotation.DrawableRes

internal data class ArtInfo(
    @DrawableRes val artRes: Int,
    val artName: String,
    val artistName: String
)

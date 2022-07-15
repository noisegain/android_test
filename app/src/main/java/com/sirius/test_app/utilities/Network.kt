package com.sirius.test_app

import android.widget.ImageView
import coil.load
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

suspend fun loadImage(imageView: ImageView, imageURL: String) = withContext(Dispatchers.IO) {
    val src = URL(imageURL).readText().substringAfter("img src=\"").substringBefore('"')
    imageView.load(src)
}
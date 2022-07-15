package com.sirius.test_app.utilities

import android.widget.ImageView

fun setStars(stars: List<ImageView>, rating: Float) {
    for (i in stars.indices) {
        if (rating >= i + 1) {
            stars[i].setImageResource(android.R.drawable.star_big_on)
        }
    }
}
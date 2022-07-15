package com.sirius.test_app.utilities

import android.widget.ImageView
import com.sirius.test_app.R
import kotlin.math.round

fun setStars(stars: List<ImageView>, rating: Float) {
    for (i in 0 until minOf(round(rating).toInt(), stars.size)) {
        stars[i].setImageResource(R.drawable.ic_star_on)
    }
}
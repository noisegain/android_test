package com.sirius.test_app.model

import com.sirius.test_app.ReviewModel

sealed class RcItem {
    data class Description(val text: String): RcItem()
    data class RatingsHeader(val rating: Float, val revCnt: String) : RcItem()
    data class Tags(val names: List<String>) : RcItem()
    data class Reviews(val data: List<ReviewModel>) : RcItem()
}
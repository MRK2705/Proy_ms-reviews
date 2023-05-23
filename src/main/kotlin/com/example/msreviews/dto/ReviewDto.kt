package com.example.msreviews.dto

import java.util.Date

data class ReviewDto(
    var reviewDate: Date?,
    var reviewComment: String?,
    var reviewScore: Int?,
    var productId: Long?
) {
    constructor() : this(Date(), null, null, null)

    override fun toString(): String {
        return "ReviewDto(reviewDate=$reviewDate, reviewComment=$reviewComment, reviewScore=$reviewScore, productId=$productId)"
    }
}
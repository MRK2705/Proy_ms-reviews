package com.example.msreviews.dao

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "reviews")
class Review (
    var reviewDate: Date,
    var reviewComment: String,
    var reviewScore: Int,
    var productId: Long = 0,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var reviewId: Long = 0,
) {
    constructor() : this(Date(), "", 0, 0, 0)
}
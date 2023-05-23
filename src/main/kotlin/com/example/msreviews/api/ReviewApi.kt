package com.example.msreviews.api

import com.example.msreviews.bl.ReviewBl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/review")
class ReviewApi (private val reviewBl: ReviewBl){

    //api de registro de review
    @PostMapping("/register")
    fun registerReview(@RequestParam reviewComment: String,
                       @RequestParam reviewScore: Int, @RequestParam productId: Long): ResponseEntity<Any> {
        val reviewDto = reviewBl.registerReview(reviewComment, reviewScore, productId)
        return ResponseEntity.ok(reviewDto)
    }

    //api para actualizar un review
    @PutMapping("/update")
    fun updateReview(@RequestParam reviewId: Long, @RequestParam reviewComment: String,
                     @RequestParam reviewScore: Int): ResponseEntity<Any> {
        val reviewDto = reviewBl.updateReview(reviewId, reviewComment, reviewScore)
        return ResponseEntity.ok(reviewDto)
    }

    //api para eliminar un review
    @DeleteMapping("/delete")
    fun deleteReview(@RequestParam reviewId: Long): ResponseEntity<Any> {
        val reviewDto = reviewBl.deleteReview(reviewId)
        return ResponseEntity.ok(reviewDto)
    }

}
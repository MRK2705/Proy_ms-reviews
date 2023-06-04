package com.example.msreviews.api

import com.example.msreviews.bl.ReviewBl
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
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
    @CircuitBreaker(name = "reviewCB", fallbackMethod = "registerReviewFallback")
    @Bulkhead(name = "reviewBH", fallbackMethod = "registerReviewFallback")
    @RateLimiter(name = "reviewRL", fallbackMethod = "registerReviewFallback")
    @Retry(name = "reviewRT", fallbackMethod = "registerReviewFallback")
    fun registerReview(@RequestParam reviewComment: String,
                       @RequestParam reviewScore: Int, @RequestParam productId: Long): ResponseEntity<Any> {
        val reviewDto = reviewBl.registerReview(reviewComment, reviewScore, productId)
        return ResponseEntity.ok(reviewDto)
    }
    fun registerReviewFallback(reviewComment: String, reviewScore: Int, productId: Long, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

    //api para actualizar un review
    @PutMapping("/update")
    @CircuitBreaker(name = "reviewCB", fallbackMethod = "updateReviewFallback")
    @Bulkhead(name = "reviewBH", fallbackMethod = "updateReviewFallback")
    @RateLimiter(name = "reviewRL", fallbackMethod = "updateReviewFallback")
    @Retry(name = "reviewRT", fallbackMethod = "updateReviewFallback")
    fun updateReview(@RequestParam reviewId: Long, @RequestParam reviewComment: String,
                     @RequestParam reviewScore: Int): ResponseEntity<Any> {
        val reviewDto = reviewBl.updateReview(reviewId, reviewComment, reviewScore)
        return ResponseEntity.ok(reviewDto)
    }
    fun updateReviewFallback(reviewId: Long, reviewComment: String, reviewScore: Int, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

    //api para eliminar un review
    @DeleteMapping("/delete")
    @CircuitBreaker(name = "reviewCB", fallbackMethod = "deleteReviewFallback")
    @Bulkhead(name = "reviewBH", fallbackMethod = "deleteReviewFallback")
    @RateLimiter(name = "reviewRL", fallbackMethod = "deleteReviewFallback")
    @Retry(name = "reviewRT", fallbackMethod = "deleteReviewFallback")
    fun deleteReview(@RequestParam reviewId: Long): ResponseEntity<Any> {
        val reviewDto = reviewBl.deleteReview(reviewId)
        return ResponseEntity.ok(reviewDto)
    }
    fun deleteReviewFallback(reviewId: Long, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

}
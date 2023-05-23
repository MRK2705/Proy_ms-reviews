package com.example.msreviews.bl

import com.example.msreviews.dao.Repository.ReviewRepository
import com.example.msreviews.dao.Review
import com.example.msreviews.dto.ReviewDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewBl @Autowired constructor(private val reviewRepository: ReviewRepository){

    companion object {
        val objectMapper = jacksonObjectMapper()
        val LOGGER: Logger = LoggerFactory.getLogger(ReviewBl::class.java.name)
    }

    //funcion para registrar un review
    fun registerReview(reviewComment: String, reviewScore: Int, productId: Long): ReviewDto {
        LOGGER.info("Registrando review")
        val review: Review = Review()
        review.reviewComment = reviewComment
        review.reviewScore = reviewScore
        review.productId = productId
        reviewRepository.save(review)
        LOGGER.info("Review guardado en base de datos")
        val reviewDto = ReviewDto(review.reviewDate, review.reviewComment, review.reviewScore, review.productId)
        return reviewDto
    }

    //funcion para actualizar un review
    fun updateReview(reviewId: Long, reviewComment: String, reviewScore: Int): ReviewDto {
        LOGGER.info("Actualizando review")
        val review: Review = reviewRepository.findById(reviewId).get()
        review.reviewId = reviewId
        review.reviewComment = reviewComment
        review.reviewScore = reviewScore
        reviewRepository.save(review)
        LOGGER.info("Review actualizado en base de datos")
        val reviewDto = ReviewDto(review.reviewDate, review.reviewComment, review.reviewScore, review.productId)
        return reviewDto
    }

    //funcion para eliminar un review
    fun deleteReview(reviewId: Long): ReviewDto {
        LOGGER.info("Eliminando review")
        val review: Review = reviewRepository.findById(reviewId).get()
        reviewRepository.delete(review)
        LOGGER.info("Review eliminado en base de datos")
        val reviewDto = ReviewDto(review.reviewDate, review.reviewComment, review.reviewScore, review.productId)
        return reviewDto
    }

}
package com.example.msreviews.dao.Repository

import com.example.msreviews.dao.Review
import org.springframework.data.repository.CrudRepository

interface ReviewRepository: CrudRepository<Review, Long>

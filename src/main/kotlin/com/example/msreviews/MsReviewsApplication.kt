package com.example.msreviews

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class MsReviewsApplication

fun main(args: Array<String>) {
	runApplication<MsReviewsApplication>(*args)
}

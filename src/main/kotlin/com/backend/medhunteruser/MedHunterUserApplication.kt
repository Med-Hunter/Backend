package com.backend.medhunteruser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class MedHunterUserApplication

fun main(args: Array<String>) {
    runApplication<MedHunterUserApplication>(*args)
}

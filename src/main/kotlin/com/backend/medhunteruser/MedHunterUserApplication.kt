package com.backend.medhunteruser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MedHunterUserApplication

fun main(args: Array<String>) {
    runApplication<MedHunterUserApplication>(*args)
}

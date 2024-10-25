package com.backend.medhunteruser.global.config

import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties.Restclient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RestClientConfig {
    @Bean
    fun restClient(): Restclient {
        return Restclient()
    }
}
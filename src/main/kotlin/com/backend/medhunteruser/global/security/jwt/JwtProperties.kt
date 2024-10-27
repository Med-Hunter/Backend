package com.backend.medhunteruser.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
class JwtProperties {
    var issuer: String = ""
    var secretKey: String = ""
    var expiration: Long = 0
}
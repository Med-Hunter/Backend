package com.backend.medhunteruser.global.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenProvider(
    private val properties: JwtProperties
) {
    private val key: Key = SecretKeySpec(
        properties.secretKey.toByteArray(),
        SignatureAlgorithm.HS256.jcaName
    )

    fun generateAccessToken(
        userId: String,
        role: String,
    ): String {
        return Jwts.builder()
            .setSubject(userId)
            .setIssuer(properties.issuer)
            .setIssuedAt(Date())
            .setClaims(mapOf("role" to UserRole.valueOf(role)))
            .setExpiration(Date(System.currentTimeMillis() + properties.expiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(
        token: String
    ): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getUserIdFromToken(
        token: String
    ): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    enum class UserRole(
        val authorities: List<GrantedAuthority>
    ) {
        ROLE_USER(listOf(SimpleGrantedAuthority("ROLE_USER"))),
        ROLE_ADMIN(listOf(SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}

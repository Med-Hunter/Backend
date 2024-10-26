package com.backend.medhunteruser.infra.mongo

import com.backend.medhunteruser.domain.model.*
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "users")
data class UserEntity(
    @Id val id: String? = null,
    val name: String,
    var email: String,
    var password: String? = null,
    var picture: String? = null,
    val provider: AuthProvider,
    val providerId: String,
    val refreshToken: String,
    @CreatedDate val createdAt: LocalDateTime? = null,
    @LastModifiedDate val updatedAt: LocalDateTime? = null
) {
    constructor(
        name: String,
        email: String,
        provider: AuthProvider,
        providerId: String,
        refreshToken: String,
        picture: String? = null
    ) : this(
        id = null,
        name = name,
        email = email,
        password = null,
        picture = picture,
        provider = provider,
        providerId = providerId,
        refreshToken = refreshToken,
        createdAt = null,
        updatedAt = null
    )
    fun toDomain(): User {
        return User(
            id = id,
            personal = PersonalInfo(
                name = name,
                email = email,
                password = password,
                picture = picture,
            ),
            provider = ProviderInfo(
                provider = provider,
                providerId = providerId,
            ),
            refreshToken = RefreshToken(
                token = refreshToken,
            )
        )
    }
}

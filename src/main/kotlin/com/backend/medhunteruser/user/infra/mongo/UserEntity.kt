package com.backend.medhunteruser.user.infra.mongo

import com.backend.medhunteruser.user.domain.model.*
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
    val role: UserRole,
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
        role: String,
        picture: String? = null
    ) : this(
        id = null,
        name = name,
        email = email,
        password = null,
        role = UserRole.valueOf(role),
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
            role = role.name,
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

enum class UserRole {
    ROLE_USER,
    ROLE_ADMIN
}

package com.backend.medhunteruser.infra.jpa

import com.backend.medhunteruser.domain.model.User
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(unique = true, nullable = false)
    val googleId: String,

    @Column(nullable = false)
    val name: String,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(nullable = true)
    val password: String? = null,

    @Column(nullable = true)
    val picture: String? = null,

    @CreatedDate
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
) {
    fun toDomain(): User {
        return User(
            id = id,
            googleId = googleId,
            name = User.Name(name),
            email = User.Email(email),
            picture = picture?.let { User.Picture(it) },
        )
    }

    constructor(
        googleId: String,
        name: String,
        email: String,
        picture: String? = null
    ) : this(
        id = 0,
        googleId = googleId,
        name = name,
        email = email,
        picture = picture
    )
}

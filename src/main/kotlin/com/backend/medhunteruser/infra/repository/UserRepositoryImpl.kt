package com.backend.medhunteruser.infra.repository

import com.backend.medhunteruser.domain.model.User
import com.backend.medhunteruser.domain.repository.UserRepository
import com.backend.medhunteruser.infra.jpa.UserEntity
import com.backend.medhunteruser.infra.jpa.UserJpaRepository
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties.Restclient
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl (
    private val userJpaRepository : UserJpaRepository,
    private val restClient : Restclient
): UserRepository {
    override fun save(
        user: User
    ): User {
        val userEntity = UserEntity(
            googleId = user.googleId,
            name = user.name.value,
            email = user.email.value,
            picture = user.picture?.url
        )
        return userJpaRepository.save(userEntity).toDomain()
    }
}
package com.backend.medhunteruser.infra.repository

import com.backend.medhunteruser.domain.model.User
import com.backend.medhunteruser.domain.repository.UserRepository
import com.backend.medhunteruser.infra.mongo.UserEntity
import com.backend.medhunteruser.infra.mongo.UserMongoRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userMongoRepository: UserMongoRepository,
) : UserRepository {
    override fun save(
        user: User
    ): User {
        val userEntity = UserEntity(
            name = user.personal.name,
            email = user.personal.email,
            provider = user.provider.provider,
            providerId = user.provider.providerId,
            refreshToken = user.refreshToken.token,
            picture = user.personal.picture
        )
        return userMongoRepository.save(userEntity).toDomain()
    }
}
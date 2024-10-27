package com.backend.medhunteruser.user.infra.repository

import com.backend.medhunteruser.user.domain.model.User
import com.backend.medhunteruser.user.domain.repository.UserRepository
import com.backend.medhunteruser.user.infra.mongo.UserEntity
import com.backend.medhunteruser.user.infra.mongo.UserMongoRepository
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
            role = user.role,
            providerId = user.provider.providerId,
            refreshToken = user.refreshToken.token,
            picture = user.personal.picture
        )
        return userMongoRepository.save(userEntity).toDomain()
    }
}
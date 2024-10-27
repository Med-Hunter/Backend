package com.backend.medhunteruser.user.infra.mongo

import org.springframework.data.mongodb.repository.MongoRepository

interface UserMongoRepository : MongoRepository<UserEntity, String> {
}
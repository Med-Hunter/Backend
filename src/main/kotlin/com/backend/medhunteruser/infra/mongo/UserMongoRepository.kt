package com.backend.medhunteruser.infra.mongo

import org.springframework.data.mongodb.repository.MongoRepository

interface UserMongoRepository : MongoRepository<UserEntity, String> {
}
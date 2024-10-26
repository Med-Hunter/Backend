package com.backend.medhunteruser.domain.repository

import com.backend.medhunteruser.domain.model.User

interface UserRepository {
    fun save(user: User): User
}
package com.backend.medhunteruser.user.domain.repository

import com.backend.medhunteruser.user.domain.model.User

interface UserRepository {
    fun save(user: User): User
}
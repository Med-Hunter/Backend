package com.backend.medhunteruser.domain.model

import java.util.regex.Pattern

data class Email(
    val value: String
) {
    init {
        require(isValidEmail(value)) { "Invalid email format: $value" }
    }

    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$"
        private fun isValidEmail(email: String): Boolean {
            return Pattern.compile(EMAIL_REGEX).matcher(email).matches()
        }
    }
}
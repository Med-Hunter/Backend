package com.backend.medhunteruser.domain.model

import java.util.regex.Pattern

class User(
    val id: Long,
    val googleId: String,
    var name: Name,
    var email: Email,
    var picture: Picture? = null,
) {
    fun updateName(
        newName: Name
    ) {
        this.name = newName
    }

    fun updateEmail(
        newEmail: Email
    ) {
        this.email = newEmail
    }

    fun updatePicture(
        newPicture: Picture?
    ) {
        this.picture = newPicture
    }

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

    data class Name(
        val value: String
    ) {
        init {
            require(value.isNotBlank()) { "이름은 필수 입력 값 입니다." }
            require(value.matches(Regex("^[a-zA-Z가-힣]*$"))) { "이름은 영문, 한글만 입력 가능합니다." }
        }
    }

    data class Picture(
        val url: String
    ) {
        init {
            require(isValidUrl(url)) { "유효하지 않은 URL: $url" }
        }

        companion object {
            private fun isValidUrl(url: String): Boolean {
                return try {
                    java.net.URL(url)
                    true
                } catch (e: Exception) {
                    false
                }
            }
        }
    }
}
package com.backend.medhunteruser.domain.model

import java.util.regex.Pattern

class User(
    val id: String? = null,
    var personal: PersonalInfo,
    val provider: ProviderInfo,
    val refreshToken: RefreshToken,
) {
}

data class RefreshToken(
    val token: String,
) {
}

data class ProviderInfo(
    val provider: AuthProvider,
    val providerId: String,
) {
}

data class PersonalInfo(
    val name: String,
    val email: String,
    val password: String? = null,
    val picture: String? = null,
) {
    init {
        require(name.isNotBlank()) { "이름은 필수 입력 값 입니다." }
        require(name.matches(Regex("^[a-zA-Z가-힣]*$"))) { "이름은 영문, 한글만 입력 가능합니다." }
        require(email.isNotBlank()) { "이메일은 필수 입력 값 입니다." }
        require(isValidEmail(email)) { "Invalid email format: $email" }
        require(isValidUrl(picture)) { "유효하지 않은 URL: $picture" }
    }

    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$"

        private fun isValidUrl(
            url: String?
        ): Boolean {
            if (url == null) return true
            return try {
                java.net.URL(url)
                true
            } catch (e: Exception) {
                false
            }
        }

        private fun isValidEmail(
            email: String
        ): Boolean {
            return Pattern.compile(EMAIL_REGEX).matcher(email).matches()
        }
    }
}


package com.backend.medhunteruser.global.security.oauth2

import com.backend.medhunteruser.user.domain.model.*
import com.backend.medhunteruser.user.domain.repository.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import org.springframework.stereotype.Service

@Service
class OAuth2UserServiceImpl(
    private val userRepository: UserRepository
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(
        userRequest: OAuth2UserRequest
    ): OAuth2User {
        val delegate = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)
        val regId = userRequest.clientRegistration.registrationId
        val attributes = oAuth2User.attributes

        val mappedAttributes = when (regId) {
            "google" -> mapGoogleAttributes(attributes)
            "naver" -> mapNaverAttributes(attributes)
            "apple" -> mapAppleAttributes(attributes)
            else -> throw IllegalArgumentException("지원하지 않는 소셜 로그인: $regId")
        }

        saveOrUpdateUser(mappedAttributes, regId)

        return DefaultOAuth2User(
            setOf(OAuth2UserAuthority(mappedAttributes)),
            mappedAttributes,
            "name"
        )
    }

    private fun saveOrUpdateUser(
        attributes: Map<String, Any?>,
        regId: String
    ) {
        val user = User(
            id = attributes["id"] as String,
            personal = PersonalInfo(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                picture = attributes["picture"] as? String
            ),
            provider = ProviderInfo(
                provider = AuthProvider.valueOf(regId.uppercase()),
                providerId = attributes["id"] as String
            ),
            role = "ROLE_USER",
            refreshToken = RefreshToken(
                token = "refreshToken"
            )
        )
        userRepository.save(user)
    }

    private fun mapGoogleAttributes(
        attributes: Map<String, Any>
    ): Map<String, Any?> {
        return mapOf(
            "id" to attributes["sub"],
            "name" to attributes["name"],
            "email" to attributes["email"],
            "picture" to attributes["picture"] as? String
        )
    }

    private fun mapNaverAttributes(
        attributes: Map<String, Any>
    ): Map<String, Any?> {
        val response = attributes["response"] as Map<*, *>
        return mapOf(
            "id" to response["id"],
            "name" to response["name"],
            "email" to response["email"],
            "picture" to response["profile_image"] as? String
        )
    }

    private fun mapAppleAttributes(
        attributes: Map<String, Any>
    ): Map<String, Any?> {
        return mapOf(
            "id" to (attributes["sub"] ?: "unknown"),
            "email" to attributes["email"]
        )
    }
}

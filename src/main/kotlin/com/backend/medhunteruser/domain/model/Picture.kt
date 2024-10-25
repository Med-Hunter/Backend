package com.backend.medhunteruser.domain.model

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
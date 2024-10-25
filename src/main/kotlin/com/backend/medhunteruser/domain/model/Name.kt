package com.backend.medhunteruser.domain.model

data class Name(
    val value: String
) {
    init {
        require(value.isNotBlank()) { "이름은 필수 입력 값 입니다." }
        require(value.matches(Regex("^[a-zA-Z가-힣]*$"))) { "이름은 영문, 한글만 입력 가능합니다." }
    }
}
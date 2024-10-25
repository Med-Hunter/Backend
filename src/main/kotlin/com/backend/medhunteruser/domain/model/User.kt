package com.backend.medhunteruser.domain.model

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
}
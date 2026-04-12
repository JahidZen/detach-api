package com.detach.api.dto

import com.detach.api.enums.AuthProvider

data class UserRequest(
    val provider: AuthProvider = AuthProvider.LOCAL,
    val email: String,
    val password: String
)

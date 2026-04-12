package com.detach.api.controller

import com.detach.api.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/google")
    fun googleLogin(@RequestBody request: Map<String, String>): ResponseEntity<Map<String, String>> {
        val googleToken = request["id_token"] ?: throw IllegalArgumentException("id_token required")
        val jwt = authService.loginWithGoogle(googleToken)
        return ResponseEntity.ok(mapOf("token" to jwt))
    }
}
package com.detach.api.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.Date

@Service
class JwtService {
    private val jwtSecret = Keys.hmacShaKeyFor(System.getenv("JWT_DETACH_SECRET").toByteArray())


    fun generateToken(username: String): String {
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(jwtSecret)
            .compact()
    }
}
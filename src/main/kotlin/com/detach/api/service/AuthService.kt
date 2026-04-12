package com.detach.api.service

import com.detach.api.enums.AuthProvider
import com.detach.api.enums.UserRole
import com.detach.api.enums.UserStatus
import com.detach.api.model.User
import com.detach.api.repository.UserRepository
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    @Value("\${google.client.id}") private val googleClientId: String
) {
    fun loginWithGoogle(idTokenString: String): String {
        val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory.getDefaultInstance())
            .setAudience(listOf(googleClientId))
            .build()


        val idToken: GoogleIdToken? = try {
            verifier.verify(idTokenString)
        } catch (e: Exception) {
            null
        }

        if (idToken != null) {
            val payload = idToken.payload
            val email = payload.email

            val findUser = userRepository.findByEmail(email)?: run {
                val newUser = User(
                    email = email,
                    provider = AuthProvider.GOOGLE,
                    role = UserRole.USER,
                    status = UserStatus.ACTIVE,
                    )

                userRepository.save(newUser)
            }
            return jwtService.generateToken(findUser.email)
        } else {
            throw RuntimeException("Invalid Google Token")
        }
    }
}
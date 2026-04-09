package com.detach.api.model

import com.detach.api.enums.AuthProvider
import com.detach.api.enums.UserRole
import com.detach.api.enums.UserStatus
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    var email: String = "",


    var password: String? = null,

    @Enumerated(EnumType.STRING)
    var provider : AuthProvider = AuthProvider.LOCAL,

    @Enumerated(EnumType.STRING)
    var role: UserRole = UserRole.USER,


    var walletBalance: BigDecimal = BigDecimal.ZERO,


    @Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.ACTIVE,


    @CreationTimestamp
    @Column(updatable = false)
    val createdAt: LocalDateTime? = null,


    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null,


    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val rules: MutableSet<BlocklistRule> = mutableSetOf(),


    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val focusRules: MutableSet<FocusSession> = mutableSetOf()


    )
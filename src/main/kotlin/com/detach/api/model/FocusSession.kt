package com.detach.api.model

import com.detach.api.enums.FocusSessionStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "focus_sessions")
data class FocusSession(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var startTime: LocalDateTime,

    var endTime: LocalDateTime? = null,

    var durationMinutes: Int = 0,

    @Enumerated(EnumType.STRING)
    var status: FocusSessionStatus = FocusSessionStatus.ACTIVE,

    var stakeAmount: BigDecimal = BigDecimal.ZERO,

    @ManyToOne
    @JoinColumn("user_id")
    var user: User? = null
)

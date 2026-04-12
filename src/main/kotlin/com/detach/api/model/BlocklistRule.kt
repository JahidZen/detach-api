package com.detach.api.model

import com.detach.api.enums.BlocklistTypes
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "blocklist_rules")
data class BlocklistRule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var target: String,

    @Enumerated(EnumType.STRING)
    val blockType: BlocklistTypes = BlocklistTypes.APP,

    @ManyToOne
    @JoinColumn("user_id")
    var user: User? = null
) {
    fun targetValidation() {
        when (blockType) {
            BlocklistTypes.APP -> require(target.contains("com.")) {"Invalid package name for app"}
            BlocklistTypes.WEBSITE -> require(target.contains(".")) {"Invalid domain name for website"}
            BlocklistTypes.CATEGORY -> require(target.uppercase() == target) {"CATEGORY must be uppercase"}
        }
    }
}

package io.jonathanlee.registrationservice.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

@Document
data class ApplicationUser(
    @field:Id @field:Field("_id") val objectId: ObjectId,
    val id: String,
    private val username: String,
    private val password: String,
    val firstName: String,
    val lastName: String,
    val enabled: Boolean,
    @field:DBRef val registrationVerificationToken: RegistrationVerificationToken,
    @field:DBRef val passwordResetToken: PasswordResetToken,
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.NO_AUTHORITIES
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return this.enabled
    }

    override fun isAccountNonLocked(): Boolean {
        return this.enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.enabled
    }

    override fun isEnabled(): Boolean {
        return this.enabled
    }
}

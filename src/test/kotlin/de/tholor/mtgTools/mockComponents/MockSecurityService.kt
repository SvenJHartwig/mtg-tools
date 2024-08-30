package de.tholor.mtgTools.mockComponents

import de.tholor.mtgTools.shared.security.ISecurityService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

class MockSecurityService() : ISecurityService {
    override val authenticatedUser: UserDetails?
        get() {
            return User.withUsername("test")
                .password("{noop}test")
                .roles("USER")
                .build()
        }

    override fun logout() {
        TODO("Not yet implemented")
    }
}
package de.tholor.mtgTools.shared.security

import org.springframework.security.core.userdetails.UserDetails

interface ISecurityService {
    val authenticatedUser: UserDetails?
    fun logout()
}
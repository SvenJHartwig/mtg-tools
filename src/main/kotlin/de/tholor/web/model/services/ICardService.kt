package de.tholor.web.model.services

import org.springframework.stereotype.Service

@Service
interface ICardService {
    fun createNewCard(): Boolean
}
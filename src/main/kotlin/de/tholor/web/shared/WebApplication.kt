package de.tholor.web.shared

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["de.*"])
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}

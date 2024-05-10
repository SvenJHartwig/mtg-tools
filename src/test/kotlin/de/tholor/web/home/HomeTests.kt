package de.tholor.web.home

import de.tholor.web.home.controllers.HomeController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension

@ExtendWith(OutputCaptureExtension::class)
class HomeTests {

    @Test
    fun testHomeControllerGetData(output: CapturedOutput) {
        val homeController = HomeController()
        homeController.getData()
        assert(output.contains("In logger"))
        assert(output.contains("Response Code: 200"))
    }
}
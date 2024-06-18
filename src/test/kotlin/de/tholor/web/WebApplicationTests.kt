package de.tholor.web

import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@Transactional
@ActiveProfiles(profiles = ["dev"])
class WebApplicationTests {

    @Test
    fun contextLoads() {
    }

}

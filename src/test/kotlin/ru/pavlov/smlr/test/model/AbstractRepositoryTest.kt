package ru.pavlov.smlr.test.model

import com.github.springtestdbunit.DbUnitTestExecutionListener
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests
import ru.pavlov.smlr.SmlrApplication

@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@TestExecutionListeners(DbUnitTestExecutionListener::class)
@SpringApplicationConfiguration(classes = arrayOf(SmlrApplication::class))
@DirtiesContext
abstract class AbstractRepositoryTest : AbstractJUnit4SpringContextTests() {

}
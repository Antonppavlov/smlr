package ru.pavlov.smlr.model

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DbUnitConfiguration
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests
import ru.pavlov.smlr.SmlrApplication


@TestExecutionListeners(DbUnitTestExecutionListener::class)
@SpringApplicationConfiguration(classes = arrayOf(SmlrApplication::class))
@DirtiesContext
abstract class AbstractRepositoryTest : AbstractJUnit4SpringContextTests() {

}
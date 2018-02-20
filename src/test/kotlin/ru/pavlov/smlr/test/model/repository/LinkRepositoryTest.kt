package ru.pavlov.smlr.test.model.repository

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.pavlov.smlr.model.Link
import ru.pavlov.smlr.model.LinkRepository
import ru.pavlov.smlr.test.model.AbstractRepositoryTest
import java.util.*


@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkRepositoryTest.DATASET)
open class LinkRepositoryTest : AbstractRepositoryTest() {

    companion object {
        const val DATASET = "classpath:datasets/link-table.xml"
        private val LINK_1_ID: Long = 100500
        private val LINK_1_TEXT: String = "http://www.eveonline.com"
        private val LINK_TBS_TEXT: String = "http://www.ru"

        private val LINK_NOT_FOUND: Long = 1L
    }

    @Autowired
    lateinit var repository: LinkRepository

    @Test
    fun findOneExisting() {
        val got: Optional<Link> = repository.findOne(LINK_1_ID)
        assertThat(got.isPresent, equalTo(true))
        val link = got.get()
        assertThat(link.id, equalTo(LINK_1_ID))
        assertThat(link.text, equalTo(LINK_1_TEXT))
    }


    @Test
    fun findOneNotExisting() {
        val got: Optional<Link> = repository.findOne(LINK_NOT_FOUND)
        assertThat(got.isPresent, equalTo(false))
    }

    @Test
    fun saveNew() {
        assertThat(repository.findAll(), hasSize(3))

        val toBeSaved = Link(LINK_TBS_TEXT)
         val got: Link = repository.save(toBeSaved)

        assertThat(repository.findAll(), hasSize(4))

        assertThat(got.text, equalTo(LINK_TBS_TEXT))
    }

}
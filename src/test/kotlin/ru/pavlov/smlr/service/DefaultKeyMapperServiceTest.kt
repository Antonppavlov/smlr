package ru.pavlov.smlr.service

import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultKeyMapperServiceTest {

    var service: KeyMapperService = DefaultKeyMapperService()

    private val KEY: String = "aaaaaa"
    private val LINK: String = "http://www.eveonline.com"
    private val LINK_NEW: String = "http://www.wow.com"

    @Test
    fun clientCanAddNewKeyWithLink() {
        assertEquals(
                KeyMapperService.Add.Success(KEY, LINK),
                service.add(KEY, LINK)
        )

        assertEquals(
                KeyMapperService.Get.Link(LINK),
                service.getLink(KEY)
        )
    }


    @Test
    fun clientCanNotAddExistingKey() {
        service.add(KEY, LINK)

        assertEquals(
                KeyMapperService.Add.AlreadyExist(KEY),
                service.add(KEY, LINK_NEW)
        )

        assertEquals(
                KeyMapperService.Get.Link(LINK),
                service.getLink(KEY)
        )
    }

    @Test
    fun clientCanNotTakeLinkIfKeyIsNotFoundIsService() {
        assertEquals(KeyMapperService.Get.LinkNotFound(KEY),
                service.getLink(KEY)
        )
    }

}



package ru.pavlov.smlr.service

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DefaultKeyMapperServiceTest {
    companion object {
        private val KEY: String = "aaaaaa"
        private val LINK_A: String = "https://www.google.ru/"
        private val LINK_B: String = "https://www.yandex.ru/"

        private val KEY_A: String = "abc"
        private val ID_A: Long = 10000000L
        private val KEY_B: String = "cde"
        private val ID_B: Long = 10000001L
    }

    @InjectMocks
    var service: KeyMapperService = DefaultKeyMapperService()

    @Mock
    lateinit var converter: KeyConverterService

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(converter.keyToId(KEY_A)).thenReturn(ID_A)
        Mockito.`when`(converter.idToKey(ID_A)).thenReturn(KEY_A)
        Mockito.`when`(converter.keyToId(KEY_B)).thenReturn(ID_B)
        Mockito.`when`(converter.idToKey(ID_B)).thenReturn(KEY_B)
    }

    @Test
    fun clientCantAddLink() {
        val keyA = service.add(LINK_A)
        assertEquals(KeyMapperService.Get.Link(LINK_A), service.getLink(keyA))
        val keyB = service.add(LINK_B)
        assertEquals(KeyMapperService.Get.Link(LINK_B), service.getLink(keyB))

        assertNotEquals(keyA, keyB)
    }

    @Test
    fun clientCanNotTakeLinkIfKeyIsNotFoundIsService() {
        assertEquals(KeyMapperService.Get.LinkNotFound(KEY), service.getLink(KEY))
    }

}



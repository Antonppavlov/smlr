package ru.pavlov.smlr.service

import org.junit.Assert
import org.junit.Test
import java.util.*

class DefaultKeyConverterServiceTest {

    val service: KeyConverterService = DefaultKeyConverterService()
    @Test
    fun givenIdMustBeConvertableBothWays() {
        val random = Random()
        for (i in 0..1000) {
            val initialId = Math.abs(random.nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)
            Assert.assertEquals(initialId, id)
        }
    }
}
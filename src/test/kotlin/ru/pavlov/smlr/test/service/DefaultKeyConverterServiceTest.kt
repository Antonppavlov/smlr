package ru.pavlov.smlr.test.service

import org.junit.Assert
import org.junit.Test
import ru.pavlov.smlr.service.DefaultKeyConverterService
import ru.pavlov.smlr.service.KeyConverterService
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
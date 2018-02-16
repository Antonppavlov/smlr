package ru.pavlov.smlr.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.thymeleaf.util.NumberUtils.sequence
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class DefaultKeyMapperService : KeyMapperService {

    private val map: MutableMap<Long, String> = ConcurrentHashMap()

    @Autowired
    lateinit var converter: KeyConverterService

    val sequence = AtomicLong(10000000L)


    override fun add(link: String): String {
        val id = sequence.getAndIncrement()
        val key = converter.idToKey(id)

        map.put(id, link)

        return key
    }

    override fun getLink(key: String): KeyMapperService.Get {
        var id = converter.keyToId(key)
        var result = map[id]

        return if (result == null) {
            KeyMapperService.Get.LinkNotFound(key)
        } else {
            KeyMapperService.Get.Link(result)
        }
    }


}
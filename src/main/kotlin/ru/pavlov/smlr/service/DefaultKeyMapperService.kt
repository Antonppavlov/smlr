package ru.pavlov.smlr.service

import java.util.concurrent.ConcurrentHashMap

class DefaultKeyMapperService : KeyMapperService {

    private val map: MutableMap<String, String> = ConcurrentHashMap()

    override fun add(key: String, link: String): KeyMapperService.Add {
        if (map.containsKey(key)) {
            return KeyMapperService.Add.AlreadyExist(key)
        } else {
            map.put(key, link)
            return KeyMapperService.Add.Success(key, link)
        }

    }

    override fun getLink(key: String): KeyMapperService.Get {
        if (map.containsKey(key)) {
            val link: String = map.get(key)!!
            return KeyMapperService.Get.Link(link)
        } else {
            return KeyMapperService.Get.LinkNotFound(key)
        }
    }
}
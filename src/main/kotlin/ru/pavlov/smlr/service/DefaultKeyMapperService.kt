package ru.pavlov.smlr.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.pavlov.smlr.model.Link
import ru.pavlov.smlr.model.LinkRepository

@Component
open class DefaultKeyMapperService: KeyMapperService {

    @Autowired
    lateinit var converter: KeyConverterService

    @Autowired
    lateinit var repo: LinkRepository

    @Transactional
    override fun add(link: String) =
            converter.idToKey(repo.save(Link(link)).id)

    override fun getLink(key: String): KeyMapperService.Get {
        val result = repo.findOne(converter.keyToId(key))
        return if (result.isPresent) {
            KeyMapperService.Get.Link(result.get().text)
        } else {
            KeyMapperService.Get.NotFound(key)
        }
    }
}

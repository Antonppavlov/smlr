package ru.pavlov.smlr.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.pavlov.smlr.service.KeyMapperService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("{key}")
class RedirectController {
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://www.eveonline.com"

    @Autowired
    lateinit var service: KeyMapperService

    @RequestMapping
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {
        val resualt = service.getLink(key)

        when (resualt) {
            is KeyMapperService.Get.Link -> {
                response.setHeader(HEADER_NAME, resualt.link)
                response.status = 302
            }
            is KeyMapperService.Get.LinkNotFound ->{
                response.status = 404
            }
        }


//        while (resual) {
//            if (key == "aaaaaa") {
//                response.setHeader(HEADER_NAME, HEADER_VALUE)
//                response.status = 302
//            } else {
//                response.status = 404
//            }
//        }

    }
}
package ru.pavlov.smlr.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("{key}")
class RedirectController {
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://www.eveonline.com"
    @RequestMapping
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {
        if (key == "a") {
            response.setHeader(HEADER_NAME, HEADER_VALUE)
            response.status = 302
        } else {
            response.status = 404
        }

    }
}
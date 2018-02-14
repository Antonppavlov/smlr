package ru.pavlov.smlr.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.pavlov.smlr.SmlrApplication

/**
 * This class is developed by maxsyachin on 23.04.16
 * for smlr in version ru.fnnetrolle.smrl.controllers
 * under MIT license
 */

//@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(SmlrApplication::class))
@WebAppConfiguration
class RedirectControllerTest {

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext
    lateinit var mockMvc: MockMvc


    private val PATH = "a"
    private val BAD_PATH = "b"
    private val REDIRECT_STATUS: Int = 302
    private val NOT_FOUD_STATUS: Int = 404
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://www.eveonline.com"

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    @Test
    fun controllerMustRedirectUsWhenRequestIsSuccessful() {
        mockMvc.perform(get("/$PATH"))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    @Test
    fun controllerMustReturn404BadKey() {
        mockMvc.perform(get("/$BAD_PATH"))
                .andExpect(status().`is`(NOT_FOUD_STATUS))
    }
}
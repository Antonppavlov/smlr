package ru.pavlov.smlr.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.pavlov.smlr.SmlrApplication
import ru.pavlov.smlr.service.KeyMapperService

/**
 * This class is developed by maxsyachin on 23.04.16
 * for smlr in version ru.fnnetrolle.smrl.controllers
 * under MIT license
 */

@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(SmlrApplication::class))
@WebAppConfiguration
class RedirectControllerTest {
    companion object {
        private val PATH = "aaaaaa"
        private val BAD_PATH = "b"
        private val REDIRECT_STATUS: Int = 302
        private val NOT_FOUD_STATUS: Int = 404
        private val HEADER_NAME = "Location"
        private val HEADER_VALUE = "http://www.eveonline.com"
    }


    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @InjectMocks
    @Autowired
    lateinit var controller: RedirectController

    @Mock
    lateinit var service: KeyMapperService


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()

        Mockito.`when`(service.getLink(PATH)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        Mockito.`when`(service.getLink(BAD_PATH)).thenReturn(KeyMapperService.Get.LinkNotFound(BAD_PATH))
    }

    @Test
    fun controllerMustRedirectUsWhenRequestIsSuccessful() {
        mockMvc.
                perform(get("/$PATH"))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    @Test
    fun controllerMustReturn404BadKey() {
        mockMvc.perform(get("/$BAD_PATH"))
                .andExpect(status().`is`(NOT_FOUD_STATUS))
    }


}
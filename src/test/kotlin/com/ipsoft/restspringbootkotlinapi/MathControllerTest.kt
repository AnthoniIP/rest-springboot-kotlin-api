package com.ipsoft.restspringbootkotlinapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ipsoft.restspringbootkotlinapi.math.controller.MathController
import com.ipsoft.restspringbootkotlinapi.math.model.CalculateRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@WebMvcTest(MathController::class)
class MathControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    @Test
    fun `test successful evaluation`() {
        val requestBody = objectMapper.writeValueAsString(CalculateRequest("8+10%9/2"))

        mockMvc.perform(
            post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isOk)
            .andExpect(content().json("{\"result\":\"8.5\"}"))
    }

    @Test
    fun `test invalid expression`() {
        val requestBody = objectMapper.writeValueAsString(CalculateRequest("8+10%9teste2"))

        mockMvc.perform(
            post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isBadRequest)
            .andExpect(content().json("{\"message\":\"Invalid math operator\"}"))
    }

    @Test
    fun `test empty expression`() {
        val requestBody = objectMapper.writeValueAsString(CalculateRequest(""))

        mockMvc.perform(
            post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isOk)
            .andExpect(content().json("{\"result\":\"0.0\"}"))
    }
}
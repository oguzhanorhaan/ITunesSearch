package com.oguzhanorhan.itunessearch.datasource.remote

import com.nhaarman.mockitokotlin2.mock
import com.oguzhanorhan.itunessearch.datasource.model.ITunesItemDTO
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

@RunWith(JUnit4::class)
class ResponseHandlerTest {
    lateinit var responseHandler: ResponseHandler

    @Before
    fun setUp() {
        responseHandler = ResponseHandler()
    }

    @Test
    fun `if exception throws code 401 then return unauthorised`() {
        val httpException = HttpException(Response.error<List<ITunesItemDTO>>(401, mock()))
        val result = responseHandler.handleException<List<ITunesItemDTO>>(httpException)
        Assert.assertEquals("Unauthorised", result.message)
    }

    @Test
    fun `if timeout occurred then return timeout-error`() {
        val socketTimeoutException = SocketTimeoutException()
        val result = responseHandler.handleException<List<ITunesItemDTO>>(socketTimeoutException)
        Assert.assertEquals("Timeout", result.message)
    }
}

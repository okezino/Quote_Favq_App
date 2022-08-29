package com.example.favqs.domain.usecase

import com.example.favqs.data.remote.repository.QuotesRepositoryImp
import com.example.favqs.domain.model.Quotes
import com.example.favqs.domain.model.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetQuoteBySearchUseCaseTest{
    private lateinit var mockQuotesRepository : QuotesRepositoryImp
    private lateinit var getQuoteBySearchUseCase: GetQuoteBySearchUseCase


    @Before
    fun setUp(){
        mockQuotesRepository = mock()
        getQuoteBySearchUseCase = GetQuoteBySearchUseCase(mockQuotesRepository)
    }

    @Test
    fun `get success on searching quote List`(): Unit = runBlocking{
        val mockSuccess : Resource.Success<Quotes> = mock()
        whenever(mockQuotesRepository.searchForQuote("Market")).thenReturn(
            mockSuccess
        )

        val response = getQuoteBySearchUseCase.execute("Market")
        assertEquals(response.data, mockSuccess)
    }

    @Test
    fun `get error response on failed searching quote List`(): Unit = runBlocking{
        val mockError : Resource.Error<Quotes> = mock()
        whenever(mockQuotesRepository.searchForQuote("Market")).thenReturn(
            mockError
        )

        val response = getQuoteBySearchUseCase.execute("Market")
        assertEquals(response.data, mockError)
    }
}
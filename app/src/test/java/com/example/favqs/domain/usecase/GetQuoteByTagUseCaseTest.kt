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

class GetQuoteByTagUseCaseTest{
    private lateinit var mockQuotesRepository : QuotesRepositoryImp
    private lateinit var getQuoteByTagUseCase: GetQuoteByTagUseCase


    @Before
    fun setUp(){
        mockQuotesRepository = mock()
        getQuoteByTagUseCase = GetQuoteByTagUseCase(mockQuotesRepository)
    }

    @Test
    fun `get success on getting quote List by tag`(): Unit = runBlocking{
        val mockSuccess : Resource.Success<Quotes> = mock()
        whenever(mockQuotesRepository.listOfQuoteByTag("Family")).thenReturn(
            mockSuccess
        )

        val response = getQuoteByTagUseCase.execute("Family")
        assertEquals(response.data, mockSuccess)
    }

    @Test
    fun `get error response on failed quote List by tag`(): Unit = runBlocking{
        val mockError : Resource.Error<Quotes> = mock()
        whenever(mockQuotesRepository.listOfQuoteByTag("Family")).thenReturn(
            mockError
        )

        val response = getQuoteByTagUseCase .execute("Family")
        assertEquals(response.data, mockError)
    }
}
package com.example.favqs.domain.usecase

import com.example.favqs.data.remote.repository.QuotesRepositoryImp
import com.example.favqs.domain.model.Quotes
import com.example.favqs.domain.model.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetQuoteListUseCaseTest{
    private lateinit var mockQuotesRepository : QuotesRepositoryImp
    private lateinit var getQuoteListUseCase: GetQuoteListUseCase


    @Before
    fun setUp(){
        mockQuotesRepository = mock()
        getQuoteListUseCase = GetQuoteListUseCase(mockQuotesRepository)
    }

    @Test
    fun `get success on getting quote List`(): Unit = runBlocking{
            val mockSuccess : Resource.Success<Quotes> = mock()
            whenever(mockQuotesRepository.listOfAllQuote()).thenReturn(
                mockSuccess
            )

            val response = getQuoteListUseCase.execute(Unit)
            assertEquals(response.data, mockSuccess)
        }

    @Test
    fun `get error response on failed quote List`(): Unit = runBlocking{
        val mockError : Resource.Error<Quotes> = mock()
        whenever(mockQuotesRepository.listOfAllQuote()).thenReturn(
            mockError
        )

        val response = getQuoteListUseCase.execute(Unit)
        assertEquals(response.data, mockError)
    }
}
package com.example.favqs.data.remote.repository

import com.example.favqs.data.remote.QuoteApiService
import com.example.favqs.domain.base.BaseRepositoryService
import com.example.favqs.domain.model.Quotes
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.repository.QuotesRepository
import com.example.favqs.util.NETWORK_ERROR
import javax.inject.Inject

class QuotesRepositoryImp @Inject constructor(val quoteApiService: QuoteApiService) :
    QuotesRepository, BaseRepositoryService() {

    override suspend fun listOfAllQuote(): Resource<Quotes> = executeRequest {
        try {
            val response = quoteApiService.getListOfQuote()

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: NETWORK_ERROR)
        }
    }

    override suspend fun listOfQuoteByTag(tag: String): Resource<Quotes> = executeRequest {
        try {
            val response = quoteApiService.getQuoteByTag(tag)

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: NETWORK_ERROR)
        }
    }

    override suspend fun searchForQuote(searchWord: String): Resource<Quotes> = executeRequest {
        try {
            val response = quoteApiService.getSearchQuote(searchWord)

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: NETWORK_ERROR)
        }
    }
}
package com.example.favqs.domain.repository

import com.example.favqs.domain.model.Quotes
import com.example.favqs.domain.model.Resource

interface QuotesRepository {
    suspend fun listOfAllQuote(): Resource<Quotes>

    suspend fun listOfQuoteByTag(tag: String): Resource<Quotes>

    suspend fun searchForQuote(searchWord: String): Resource<Quotes>
}
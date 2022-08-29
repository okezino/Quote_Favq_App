package com.example.favqs.domain.usecase

import com.example.favqs.domain.base.BaseUseCase
import com.example.favqs.domain.model.Quotes
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.repository.QuotesRepository
import javax.inject.Inject

class GetQuoteBySearchUseCase @Inject constructor(val quotesRepository: QuotesRepository) :
    BaseUseCase<String, GetQuoteBySearchUseCase.Response>() {

    data class Response(
        val data: Resource<Quotes>
    )

    override suspend fun execute(params: String): Response {
        return Response(quotesRepository.searchForQuote(params))
    }
}
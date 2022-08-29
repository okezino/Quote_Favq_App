package com.example.favqs.domain.usecase

import com.example.favqs.domain.base.BaseUseCase
import com.example.favqs.domain.model.Quotes
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.repository.QuotesRepository
import javax.inject.Inject

class GetQuoteListUseCase @Inject constructor(val quotesRepository: QuotesRepository) : BaseUseCase<
        Unit, GetQuoteListUseCase.Response>() {
    data class Response(
        val data: Resource<Quotes>
    )

    override suspend fun execute(params: Unit): Response {
        return Response(quotesRepository.listOfAllQuote())
    }
}
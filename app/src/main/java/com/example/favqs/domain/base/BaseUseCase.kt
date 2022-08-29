package com.example.favqs.domain.base

abstract class BaseUseCase<in Params, out Response> {
    abstract suspend fun execute(params: Params): Response
}
package com.example.favqs.domain.usecase

import com.example.favqs.domain.base.BaseUseCase
import com.example.favqs.domain.model.AuthData
import com.example.favqs.domain.model.Login
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) : BaseUseCase<
        Login, LoginUseCase.Response>() {

    data class Response(
        val data: Resource<AuthData>
    )

    override suspend fun execute(params: Login): Response {
        return Response(authRepository.loginUser(params))
    }
}
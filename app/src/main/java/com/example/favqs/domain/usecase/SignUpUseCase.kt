package com.example.favqs.domain.usecase

import com.example.favqs.domain.base.BaseUseCase
import com.example.favqs.domain.model.AuthData
import com.example.favqs.domain.model.CreateUser
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(val authRepository: AuthRepository) :
    BaseUseCase<CreateUser,
            SignUpUseCase.Response>() {

    data class Response(
        val data: Resource<AuthData>
    )

    override suspend fun execute(params: CreateUser): Response {
        return Response(authRepository.signUpUser(params))
    }
}
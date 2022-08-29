package com.example.favqs.data.remote.repository

import com.example.favqs.data.remote.QuoteApiService
import com.example.favqs.domain.base.BaseRepositoryService
import com.example.favqs.domain.model.AuthData
import com.example.favqs.domain.model.CreateUser
import com.example.favqs.domain.model.Login
import com.example.favqs.domain.model.Resource
import com.example.favqs.domain.repository.AuthRepository
import com.example.favqs.util.NETWORK_ERROR
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val quoteApiService: QuoteApiService) :
    AuthRepository, BaseRepositoryService() {
    override suspend fun loginUser(user: Login): Resource<AuthData> = executeRequest {
        try {
            var response = quoteApiService.loginUser(user)
            Resource.Success(response)

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: NETWORK_ERROR)
        }

    }

    override suspend fun signUpUser(user: CreateUser): Resource<AuthData> = executeRequest {
        try {
            var response = quoteApiService.createUser(user)
            Resource.Success(response)

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: NETWORK_ERROR)
        }
    }
}
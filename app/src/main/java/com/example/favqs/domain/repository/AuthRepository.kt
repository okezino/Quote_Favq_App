package com.example.favqs.domain.repository

import com.example.favqs.domain.model.AuthData
import com.example.favqs.domain.model.CreateUser
import com.example.favqs.domain.model.Login
import com.example.favqs.domain.model.Resource

interface AuthRepository {
    suspend fun loginUser(user: Login): Resource<AuthData>
    suspend fun signUpUser(user: CreateUser): Resource<AuthData>
}
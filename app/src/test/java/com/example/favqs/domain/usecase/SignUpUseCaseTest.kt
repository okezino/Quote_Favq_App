package com.example.favqs.domain.usecase

import com.example.favqs.data.remote.repository.AuthRepositoryImp
import com.example.favqs.domain.model.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SignUpUseCaseTest{
    private lateinit var mockAuthRepository: AuthRepositoryImp
    private lateinit var getSignUpUseCase: SignUpUseCase

    @Before
    fun setUp() {
        mockAuthRepository = mock()
        getSignUpUseCase = SignUpUseCase(mockAuthRepository)
    }


    @Test
    fun `get success resource on network call`() = runBlocking{
        val mockSuccessData : Resource.Success<AuthData> = mock()
        val login : CreateUser = mock()
        whenever(mockAuthRepository.signUpUser(login)).thenReturn(
            mockSuccessData
        )
        val response = getSignUpUseCase.execute(login)

        assertEquals(response.data, mockSuccessData)
    }

    @Test
    fun `get Error Resource on Failed Network Call`() = runBlocking{
        val mockFailedData : Resource.Error<AuthData> = mock()
        val login : CreateUser= mock()
        whenever(mockAuthRepository.signUpUser(login)).thenReturn(
            mockFailedData
        )
        val response = getSignUpUseCase.execute(login)

        assertEquals(response.data, mockFailedData)
    }
}
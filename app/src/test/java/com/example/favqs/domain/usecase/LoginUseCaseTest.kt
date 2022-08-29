package com.example.favqs.domain.usecase

import com.example.favqs.data.remote.repository.AuthRepositoryImp
import com.example.favqs.domain.model.AuthData
import com.example.favqs.domain.model.Login
import com.example.favqs.domain.model.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {

    private lateinit var mockAuthRepository: AuthRepositoryImp
    private lateinit var getLoginUseCase: LoginUseCase

    @Before
    fun setUp() {
        mockAuthRepository = mock()
        getLoginUseCase = LoginUseCase(mockAuthRepository)
    }


    @Test
    fun `get success resource on network call`() = runBlocking{
        val mockSuccessData : Resource.Success<AuthData> = mock()
        val login : Login = mock()
        whenever(mockAuthRepository.loginUser(login)).thenReturn(
           mockSuccessData
        )
        val response = getLoginUseCase.execute(login)

        assertEquals(response.data, mockSuccessData)
    }

    @Test
    fun `get Error Resource on Failed Network Call`() = runBlocking{
        val mockFailedData : Resource.Error<AuthData> = mock()
        val login : Login = mock()
        whenever(mockAuthRepository.loginUser(login)).thenReturn(
            mockFailedData
        )
        val response = getLoginUseCase.execute(login)

        assertEquals(response.data, mockFailedData)
    }
}
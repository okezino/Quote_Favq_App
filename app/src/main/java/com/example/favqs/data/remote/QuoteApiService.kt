package com.example.favqs.data.remote

import com.example.favqs.domain.model.AuthData
import com.example.favqs.domain.model.CreateUser
import com.example.favqs.domain.model.Login
import com.example.favqs.domain.model.Quotes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuoteApiService {

    @POST("api/session")
    suspend fun loginUser(@Body user : Login) : AuthData

    @POST("api/users")
    suspend fun createUser(@Body user : CreateUser) : AuthData

    @GET("api/quotes")
    suspend fun getQuoteByTag(@Query("filter") tag : String,
                              @Query("type") tagType : String = "tag") : Quotes

    @GET("/api/quotes")
    suspend fun getListOfQuote() : Quotes

    @GET("/api/quotes")
    suspend fun getSearchQuote(
        @Query("filter") searchWord : String
    ) : Quotes

}
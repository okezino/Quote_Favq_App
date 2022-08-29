package com.example.favqs.domain.model

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("User-Token")
    val userToken: String? = null,
    val login: String? = null,
    @SerializedName("error_code")
    val errorCode: String? = null,
    val message : String? = null
)
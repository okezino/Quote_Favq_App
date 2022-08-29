package com.example.favqs.domain.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("user")
    val user: UserLog
)
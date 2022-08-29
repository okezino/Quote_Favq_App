package com.example.favqs.domain.model

import com.google.gson.annotations.SerializedName

data class CreateUser(
    @SerializedName("user")
    val user: User
)
package com.example.favqs.domain.model

data class Quotes(
val page : Int,
val lastPage : Boolean,
val quotes : List<QuoteData>
)

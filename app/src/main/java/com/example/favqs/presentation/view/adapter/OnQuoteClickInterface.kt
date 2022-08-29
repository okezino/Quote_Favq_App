package com.example.favqs.presentation.view.adapter


import com.example.favqs.domain.model.QuoteData

interface OnQuoteClickInterface {
    fun onQuoteClick(quote : QuoteData,imagePosition : Int)
}
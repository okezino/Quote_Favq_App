package com.example.favqs.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class QuoteData(
    val id: Int,
    val dialogue: Boolean,
    val tags: List<String>,
    val url: String,
    @SerializedName("favorites_count")
    val favoritesCount: Int,
    @SerializedName("upvotes_count")
    val upVotesCount: Int,
    @SerializedName("downvotes_count")
    val downVotesCount: Int,
    val author: String,
    val author_permalink: String,
    val body: String
) : Parcelable
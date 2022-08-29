package com.example.favqs.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favqs.R
import com.example.favqs.data.local.Images
import com.example.favqs.databinding.QuoteItemViewBinding
import com.example.favqs.domain.model.QuoteData

class QuoteListViewHolder(private val binding: QuoteItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        quoteData: QuoteData,
        onTagClickListener: OnTagClickListener,
        onQuoteClickInterface: OnQuoteClickInterface,
        imageInt : Int
    ) {

        with(binding) {
            quote.text = quoteData.body
            authorName.text = quoteData.author

            imageLayout.setBackgroundResource(Images.ImageList[imageInt % 10])

            imageLayout.setOnClickListener {
                onQuoteClickInterface.onQuoteClick(quoteData, imageInt)
            }

            upgrade.setOnClickListener {
                it.setBackgroundResource(R.drawable.ic_baseline_thumb_up_24)
               downgrade.setBackgroundResource(R.drawable.ic_outline_thumb_down_24)
            }

            downgrade.setOnClickListener {
                it.setBackgroundResource(R.drawable.ic_baseline_thumb_down_24)
               upgrade.setBackgroundResource(R.drawable.ic_outline_thumb_up_24)
            }

            fav.setOnClickListener {
                it.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
            }

            updateTagView(quoteData, onTagClickListener)

        }

    }

    private fun updateTagView(quoteData : QuoteData, onTagClickListener : OnTagClickListener){
        with(binding){
            if (quoteData.tags.isNotEmpty()) {
                val tagAdapter = TagAdapter(onTagClickListener)
                tagAdapter.setData(quoteData.tags)
                mainTagRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                GridLayoutManager(itemView.context, 3)
                mainTagRecyclerView.adapter = tagAdapter
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): QuoteListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = QuoteItemViewBinding.inflate(layoutInflater, parent, false)
            return QuoteListViewHolder(binding)
        }
    }

}
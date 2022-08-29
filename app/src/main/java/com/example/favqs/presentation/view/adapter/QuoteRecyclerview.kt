package com.example.favqs.presentation.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.favqs.domain.model.QuoteData

class QuoteRecyclerview(private val onQuoteClickInterface: OnQuoteClickInterface,
                        private val onTagClickListener: OnTagClickListener): RecyclerView.Adapter<QuoteListViewHolder>() {

    var quoteList = listOf<QuoteData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteListViewHolder {
       return QuoteListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: QuoteListViewHolder, position: Int) {
        val currentItem = quoteList[position]
        holder.bind(currentItem, onTagClickListener, onQuoteClickInterface = onQuoteClickInterface, position)
    }


    override fun getItemCount(): Int {
       return quoteList.size
    }

    fun setData(list : List<QuoteData>){
        val diffUtilClass = DiffUtilClass(quoteList, list)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilClass)
        this.quoteList = list
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
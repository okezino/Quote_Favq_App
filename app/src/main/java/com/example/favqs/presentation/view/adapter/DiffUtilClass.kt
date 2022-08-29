package com.example.favqs.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.favqs.domain.model.QuoteData

class DiffUtilClass(val oldList: List<QuoteData>, val newList: List<QuoteData>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList[newItemPosition].id &&
                oldList[oldItemPosition].author === newList[newItemPosition].author &&
                oldList[oldItemPosition].author_permalink === newList[newItemPosition].author_permalink &&
                oldList[oldItemPosition].body === newList[newItemPosition].body &&
                oldList[oldItemPosition].tags === newList[newItemPosition].tags
    }
}

class DiffUtilTag(val oldTag: List<String>, val newTag: List<String>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldTag.size
    }

    override fun getNewListSize(): Int {
        return newTag.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTag[oldItemPosition] === newTag[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTag[oldItemPosition] === newTag[newItemPosition]

    }
}
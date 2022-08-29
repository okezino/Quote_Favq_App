package com.example.favqs.presentation.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class TagAdapter(private val onTagClickListener: OnTagClickListener? = null) : RecyclerView.Adapter<TagViewHolder>() {

    var tagList = listOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return  TagViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
       holder.bind(tagList[position], onTagClickListener = onTagClickListener)
    }

    override fun getItemCount(): Int {
       return tagList.size
    }

    fun setData(list : List<String>){
        val diffUtilTag= DiffUtilTag(tagList, list)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilTag)
        this.tagList = list
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
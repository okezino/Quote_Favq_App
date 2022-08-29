package com.example.favqs.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favqs.databinding.ItemTagBinding

class TagViewHolder(private val binding: ItemTagBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tag : String, onTagClickListener: OnTagClickListener?) {
        binding.tagName.text = tag
        binding.tagName.setOnClickListener {
            onTagClickListener?.onTagClick(tag)
        }
    }

    companion object {
        fun from(parent: ViewGroup): TagViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemTagBinding.inflate(layoutInflater, parent, false)
            return TagViewHolder(binding)
        }
    }
}
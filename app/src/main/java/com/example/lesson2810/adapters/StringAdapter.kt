package com.example.lesson2810.adapters

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StringAdapter: RecyclerView.Adapter<StringAdapter.StringViewHolder>(){
    private val list = mutableListOf<String>()

    inner class StringViewHolder(
        val textView: TextView
    ): RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val textView = TextView(parent.context)
        return StringViewHolder(textView)
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.textView.text = list[position]
    }

    fun submitList(list: List<String>) {
        with(this.list){
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
}
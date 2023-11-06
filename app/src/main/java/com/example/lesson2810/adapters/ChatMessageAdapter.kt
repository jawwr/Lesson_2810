package com.example.lesson2810.adapters

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2810.data.ChatMessageEntry

class ChatMessageAdapter : RecyclerView.Adapter<ChatMessageAdapter.ChatMessageViewHolder>() {
    lateinit var chatEntry: ChatMessageEntry
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        val textView = TextView(parent.context)
        return ChatMessageViewHolder(textView)
    }

    override fun getItemCount(): Int = chatEntry.messages.size

    override fun onBindViewHolder(holder: ChatMessageViewHolder, position: Int) {
        holder.textView.text = "Message: ${chatEntry.messages[position]}"
        holder.textView.textSize = 20f
    }

    inner class ChatMessageViewHolder(
        val textView: TextView
    ) : RecyclerView.ViewHolder(textView)
}
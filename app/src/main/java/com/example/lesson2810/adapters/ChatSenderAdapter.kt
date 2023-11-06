package com.example.lesson2810.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2810.data.ChatMessageEntry
import com.example.lesson2810.databinding.ChatMessageItemBinding

class ChatSenderAdapter(
    private val onItemClick: (ChatMessageEntry) -> Unit
) : RecyclerView.Adapter<ChatSenderAdapter.ChatMessageViewHolder>() {

    private val messageEntries = mutableListOf<ChatMessageEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ChatMessageItemBinding.inflate(inflater, parent, false)
        return ChatMessageViewHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int = messageEntries.size

    override fun onBindViewHolder(holder: ChatMessageViewHolder, position: Int) {
        holder.bind(messageEntries[position])
    }

    fun submitList(list: List<ChatMessageEntry>) = with(this.messageEntries) {
        clear()
        addAll(list)
        notifyDataSetChanged()
    }

    inner class ChatMessageViewHolder(
        private val binding: ChatMessageItemBinding,
        private val onItemClick: (ChatMessageEntry) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(messageEntry: ChatMessageEntry) = with(binding) {
            messageText.text = "From: ${messageEntry.author}"

            root.setOnClickListener {
                onItemClick(messageEntry)
            }
        }
    }
}
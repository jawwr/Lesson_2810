package com.example.lesson2810.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2810.R
import com.example.lesson2810.adapters.ChatMessageAdapter
import com.example.lesson2810.databinding.FragmentSenderMessageListBinding

class SenderMessageListFragment : Fragment(R.layout.fragment_sender_message_list) {
    private val binding: FragmentSenderMessageListBinding by viewBinding()
    private val args: SenderMessageListFragmentArgs by navArgs()
    private var adapter: ChatMessageAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ChatMessageAdapter(args.messages)
        binding.sender.text = "From: ${args.messages.author}"
        initRecycler()
    }

    private fun initRecycler() {
        with(binding.messageList) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = this@SenderMessageListFragment.adapter
        }
    }
}
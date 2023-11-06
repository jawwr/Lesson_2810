package com.example.lesson2810.fragments

import android.Manifest.permission.READ_SMS
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2810.adapters.ChatSenderAdapter
import com.example.lesson2810.viewModels.MessageViewModel
import com.example.lesson2810.R
import com.example.lesson2810.data.ChatMessageEntry
import com.example.lesson2810.databinding.FragmentSmsSenderListBinding

class SmsListFragment : Fragment(R.layout.fragment_sms_sender_list) {
    private val binding: FragmentSmsSenderListBinding by viewBinding()
    private val messageViewModel: MessageViewModel by viewModels()
    private val chatSenderAdapter = ChatSenderAdapter(::onChatClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissions()
        initRecycler()
        bindDataRequest()
    }

    private fun onChatClick(chatMessageEntry: ChatMessageEntry) {
        val direction = SmsListFragmentDirections.actionSenderListToSenderMessageListFragment(
            messages = chatMessageEntry
        )
        findNavController().navigate(direction)
    }

    private fun bindDataRequest() {
        messageViewModel.liveData.observe(viewLifecycleOwner) {
            chatSenderAdapter.submitList(it)
        }
    }

    private fun initRecycler() {
        with(binding.messageRecyclerView) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = this@SmsListFragment.chatSenderAdapter
        }
    }

    private fun checkPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            READ_SMS
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) {
                if (it) {
                    messageViewModel.loadMessages(requireContext().contentResolver)
                }
            }.launch(READ_SMS)
        } else {
            messageViewModel.loadMessages(requireContext().contentResolver)
        }
    }
}

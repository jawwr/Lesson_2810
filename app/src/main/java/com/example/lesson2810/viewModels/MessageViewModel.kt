package com.example.lesson2810.viewModels

import android.content.ContentResolver
import android.provider.Telephony
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson2810.data.ChatMessageEntry

class MessageViewModel : ViewModel() {
    private val _liveData = MutableLiveData<List<ChatMessageEntry>>()
    val liveData: LiveData<List<ChatMessageEntry>>
        get() = _liveData

    fun loadMessages(contentResolver: ContentResolver) {
        val cursor = contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val smsSenderList: MutableList<ChatMessageEntry> = mutableListOf()
        if (cursor?.moveToFirst() == true) {
            val chatMessageMap: MutableMap<String, MutableList<String>> = mutableMapOf()
            do {
                val address = cursor.getString(
                    cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)
                )
                val body = cursor.getString(
                    cursor.getColumnIndexOrThrow(Telephony.Sms.BODY)
                )
                if (chatMessageMap[address] == null) {
                    chatMessageMap[address] = mutableListOf()
                }
                chatMessageMap[address]?.add(body)
            } while (cursor.moveToNext());
            for (entry in chatMessageMap.entries) {
                smsSenderList.add(ChatMessageEntry(entry.key, entry.value))
            }
        }
        cursor?.close()
        _liveData.postValue(smsSenderList)
    }
}
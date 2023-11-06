package com.example.lesson2810.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatMessageEntry(
    val author: String,
    val messages: List<String>
) : Parcelable
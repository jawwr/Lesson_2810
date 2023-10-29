package com.example.lesson2810

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageViewModel: ViewModel() {
    fun makeDataRequest(list: List<String>) {
        _liveData.postValue(list)
    }

    private val _liveData = MutableLiveData<List<String>>()
    val liveData: LiveData<List<String>>
        get() = _liveData



}
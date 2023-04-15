package com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository

class MainViewModel(private val repository: iRepository) : ViewModel() {

    private val _name = MutableLiveData<String>("NickSeven")
    var name : LiveData<String> = _name

    var listData : LiveData<ArrayList<iFile>> = repository.getData()

    fun setName(value : String) {
        _name.postValue(value)
    }

    fun getAllData() {
        listData = repository.getData()
    }

    fun refreshData() {
        listData = repository.getAllStorage()
    }

}
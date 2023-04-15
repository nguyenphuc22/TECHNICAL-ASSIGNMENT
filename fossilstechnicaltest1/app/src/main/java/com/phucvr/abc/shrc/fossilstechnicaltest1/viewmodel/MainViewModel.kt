package com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: iRepository) : ViewModel() {

    private val _name = MutableLiveData<String>("NickSeven")
    var name : LiveData<String> = _name

    val _listData = mutableStateListOf<iFile>()

    fun setName(value : String) {
        _name.postValue(value)
    }

    fun getAllData() {
        _listData.addAll(repository.getData())
    }

    fun refreshData() {
        _listData.addAll(repository.getAllStorage())
    }

    fun changeCurrentFolder(iFile: iFile) {

    }

}
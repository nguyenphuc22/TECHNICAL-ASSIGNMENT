package com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel

import android.util.Log
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository

class FilterViewModel(iRepository: iRepository) : MainViewModel(iRepository) {

    companion object {
        private val TAG = FilterViewModel::class.java.simpleName
    }

    fun filterFiles(name : String) {
        this.listData.clear()
        val result = this.fileManager.findAllFilesWithName(name)
        this.listData.addAll(result)
        this.listData
    }
}
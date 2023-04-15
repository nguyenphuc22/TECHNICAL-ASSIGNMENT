package com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.phucvr.abc.shrc.fossilstechnicaltest1.core.FileManager
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository

class MainViewModel(private val repository: iRepository) : ViewModel(), iOnClickFolder{
    val listData = mutableStateListOf<iFile>()
    val fileManager = FileManager.getInstance()

    fun getAllData() {
        val data = repository.getData()
        fileManager.setData(data)
        fileManager.getCurrentFiles().let {
            if (it.isFolder())
            {
                val folder = it as Folder
                listData.addAll(folder.getChildren())
            }
        }
    }

    fun refreshData() {
        val data = repository.getAllStorage()
        fileManager.setData(data)
        fileManager.getCurrentFiles().let {
            if (it.isFolder())
            {
                val folder = it as Folder
                listData.addAll(folder.getChildren())
            }
        }
        listData.addAll(data)
    }

    override fun onClickFolder(iFile: iFile) {
        fileManager.nextTo(iFile)
        fileManager.getCurrentFiles().let {
            if (it.isFolder())
            {
                val folder = it as Folder
                listData.clear()
                listData.addAll(folder.getChildren())
            }
        }
    }

    override fun onClickFile(iFile: iFile) {
    }

    override fun onClickBack() {
        fileManager.backTo()
        fileManager.getCurrentFiles().let {
            if (it.isFolder())
            {
                val folder = it as Folder
                listData.clear()
                listData.addAll(folder.getChildren())
            }
        }
    }
}
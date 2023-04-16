package com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.phucvr.abc.shrc.fossilstechnicaltest1.MainActivity
import com.phucvr.abc.shrc.fossilstechnicaltest1.core.FileManager
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.Settings
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort


class MainViewModel(private val repository: iRepository) : ViewModel(), iOnClickFolder{

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    val listData = mutableStateListOf<iFile>()
    val fileManager = FileManager.getInstance()
    private var isIncreasedSort = true
    private var TYPE_SORT = TypeSort.NAME

    private var callBack: (iFile) -> Unit = {}

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
                listData.clear()
                listData.addAll(folder.getChildren())
            }
        }
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
        callBack(iFile)
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

    fun callBackFile(callBack: (iFile) -> Unit = {}) {
        this.callBack = callBack
    }

    fun turnOnModeEssentials() {
        val temp = ArrayList<iFile>()
        for (iFile in this.listData) {
            if (iFile.isFolder() && Settings.LIST_FOLDER_ESSENTIAL.contains(iFile.getName())) {
                temp.add(iFile)
            }
        }
        listData.clear()
        listData.addAll(temp)
    }

    fun turnOffModeEssentials() {
        getAllData()
    }

    fun sort() {
        when(TYPE_SORT) {
            TypeSort.NAME -> {
                if (isIncreasedSort) {
                    this.listData.sortBy { it.getName() }
                } else {
                    this.listData.sortByDescending { it.getName() }
                }
            }
            TypeSort.TYPE -> {
                if (isIncreasedSort) {
                    this.listData.sortBy { it.getExtension() }
                } else {
                    this.listData.sortByDescending { it.getExtension() }
                }
            }
            TypeSort.DATE -> {
                if (isIncreasedSort) {
                    this.listData.sortBy { it.getLastModified() }
                } else {
                    this.listData.sortByDescending { it.getLastModified() }
                }
            }
            TypeSort.SIZE -> {
                if (isIncreasedSort) {
                    this.listData.sortBy { it.getSize() }
                } else {
                    this.listData.sortByDescending { it.getSize() }
                }
            }
        }
    }
}
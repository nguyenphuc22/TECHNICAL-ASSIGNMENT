package com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.File
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository

class RepositoryLocalDevice : iRepository {

    private val data: MutableLiveData<ArrayList<iFile>> = MutableLiveData<ArrayList<iFile>>()

    override fun getAllStorage(): LiveData<ArrayList<iFile>> {
        val result = ArrayList<iFile>()
        val internalStore = Environment.getExternalStorageDirectory()
        addAllFiles(result,internalStore)
        data.postValue(result)
        return data
    }

    override fun getData(): LiveData<ArrayList<iFile>> {
        data.value?.let {
            if (it.isEmpty()) {
                return getAllStorage()
            } else {
                return data
            }
        }
        return getAllStorage()
    }

    private fun addAllFiles(result : ArrayList<iFile>,file : java.io.File) {
        if (file.isFile) {
            result.add(File(file))
            return
        }
        if (file.isDirectory) {
            val temp = ArrayList<iFile>()
            if (file.listFiles() != null) {
                for (item in file.listFiles()) {
                    if (item.isFile) {
                        addAllFiles(result,item)
                    }
                    else {
                        addAllFiles(temp,item)
                    }
                }
            }
            result.add(Folder(temp,file))
        }
    }

}
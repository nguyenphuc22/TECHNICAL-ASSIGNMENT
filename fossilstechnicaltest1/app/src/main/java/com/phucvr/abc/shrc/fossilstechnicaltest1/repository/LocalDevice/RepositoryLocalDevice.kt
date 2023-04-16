package com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice

import android.os.Environment
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.File
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository

class RepositoryLocalDevice : iRepository {

    private val data = ArrayList<iFile>()

    override fun getAllStorage(): ArrayList<iFile> {
        val result = ArrayList<iFile>()
        val internalStore = Environment.getExternalStorageDirectory()
        addAllFiles(result,internalStore)
        data.addAll(result)
        return data
    }

    override fun getData(): ArrayList<iFile> {
        data.let {
            if (it.isEmpty()) {
                return getAllStorage()
            } else {
                return data
            }
        }
        return getAllStorage()
    }

    private fun addAllFiles(result : ArrayList<iFile>,file : java.io.File) {
        Log.d("NickSeven","${file.canonicalFile} | ${file.name}")
        if (file.isFile) {
            result.add(File(file))
            return
        }
        if (file.isDirectory) {
            val temp = ArrayList<iFile>()
            if (file.listFiles() != null) {
                for (item in file.listFiles()!!) {
                    addAllFiles(temp,item)
                }
            }
            result.add(Folder(temp,file))
        }
    }

}
package com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice

import android.os.Environment
import android.util.Log
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.File
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository
import java.io.*

class RepositoryLocalDevice : iRepository {

    companion object {
        private val TAG = RepositoryLocalDevice::class.java.simpleName
    }

    private val data = ArrayList<iFile>()

    override fun getAllStorage(): ArrayList<iFile> {
        val result = ArrayList<iFile>()
        val internalStore = Environment.getExternalStorageDirectory()
        addAllFiles(result,internalStore)
        data.clear()
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

    fun moveFile(file: iFile, pathTo : String) {
        moveFile(file.getPath(),file.getFullName(),pathTo)
    }

    override fun moveFiles(list: List<iFile>, pathTo: String) {
        for (iFile in list) {
            moveFile(iFile,pathTo)
        }
    }

    private fun moveFile(inputPath: String, inputFile: String, outputPath: String) {
        var input: InputStream? = null
        var output: OutputStream? = null
        try {

            //create output directory if it doesn't exist
            val dir = java.io.File(outputPath)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            input = FileInputStream(inputPath)
            output = FileOutputStream(outputPath + "/" + inputFile)
            val buffer = ByteArray(1024)
            var read: Int
            while (input.read(buffer).also { read = it } != -1) {
                output.write(buffer, 0, read)
            }
            input.close()
            input = null

            // write the output file
            output.flush()
            output.close()
            output = null

            // delete the original file
            java.io.File(inputPath).delete()
        } catch (fnfe1: FileNotFoundException) {
            Log.e(TAG,"${fnfe1.message}")
        } catch (e: Exception) {
            Log.e(TAG,"${e.message!!}", )
        }
    }


    private fun addAllFiles(result : ArrayList<iFile>,file : java.io.File) {
        Log.d(TAG,"${file.canonicalFile} | ${file.name}")
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
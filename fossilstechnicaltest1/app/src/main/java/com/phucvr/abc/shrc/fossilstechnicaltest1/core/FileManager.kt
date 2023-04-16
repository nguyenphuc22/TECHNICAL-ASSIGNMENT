package com.phucvr.abc.shrc.fossilstechnicaltest1.core

import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import java.util.*
import kotlin.collections.ArrayList

class FileManager private constructor(){

    private object Holder { val INSTANCE = FileManager() }

    private val stackCurrentPath = Stack<String>()

    private val allData = ArrayList<iFile>()

    fun currentPath() : String {
        return stackCurrentPath.peek()
    }

    fun backTo() : String {
        if (stackCurrentPath.empty()) {
            return allData.first().getPath()
        }
        return stackCurrentPath.pop()
    }

    fun nextTo(iFile: iFile) {
        stackCurrentPath.push(iFile.getPath())
    }

    fun setData(data : ArrayList<iFile>) {
        allData.clear()
        allData.addAll(data)
        stackCurrentPath.clear()
        stackCurrentPath.add(data.first().getPath())
    }

    fun getCurrentFiles() : iFile {
        for (iFile in this.allData) {
            var path = allData.first().getPath()
            if (!stackCurrentPath.empty()) {
                path = stackCurrentPath.peek()
            }
            val result = iFile.findWithPath(path)
            if (result != null) {
                return result
            }
        }
        return allData.first()
    }

    companion object {
        @JvmStatic
        fun getInstance(): FileManager {
            return Holder.INSTANCE
        }
    }
}
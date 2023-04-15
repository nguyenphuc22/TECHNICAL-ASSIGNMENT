package com.phucvr.abc.shrc.fossilstechnicaltest1.model

interface iFile {
    fun getName() : String

    fun getLastModified() : Long

    fun getCount() : Int

    fun getPath() : String

    fun getPathWithOutName() : String

    fun getExtension() : String

    fun getSize() : Long

    fun isFile() : Boolean

    fun isFolder() : Boolean

    fun findWithPath(path : String) : iFile?
}
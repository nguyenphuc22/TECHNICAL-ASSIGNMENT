package com.phucvr.abc.shrc.fossilstechnicaltest1.model

class File : iFile {

    private lateinit var name : String
    private var lastModified : Long = 0
    private var size : Long = 0
    private var count : Int = 1
    private lateinit var path : String
    private lateinit var pathWithOutName : String
    private lateinit var extension : String

    constructor(
        name: String,
        lastModified: Long,
        size: Long,
        path: String,
        pathWithOutName: String,
        extension: String
    ) {
        this.name = name
        this.lastModified = lastModified
        this.size = size
        this.count = 1
        this.path = path
        this.pathWithOutName = pathWithOutName
        this.extension = extension
    }

    constructor(file : java.io.File) :
            this(name = file.name,
                lastModified = file.lastModified(),
                size = file.length(),
                path = file.path,
                pathWithOutName = file.canonicalPath,
                extension = file.extension)


    override fun getName(): String {
        return name
    }

    override fun getLastModified(): Long {
        return lastModified
    }

    override fun getCount(): Int {
        return count
    }

    override fun getPath(): String {
        return path
    }

    override fun getPathWithOutName(): String {
        return pathWithOutName
    }

    override fun getExtension(): String {
        return extension
    }

    override fun getSize(): Long {
        return size
    }

    override fun isFile(): Boolean {
        return true
    }

    override fun isFolder(): Boolean {
        return false
    }

    override fun findWithPath(path: String): iFile? {
        if (this.path.equals(path))
            return this
        return null
    }

    override fun findWithName(name: String): List<iFile> {
        if (this.name.contains(name))
            return listOf(this)
        return listOf()
    }
}
package com.phucvr.abc.shrc.fossilstechnicaltest1.model


class Folder : iFile {
    private var children : ArrayList<iFile>
    private var name : String
    private var lastModified : Long = 0
    private var size : Long = 0
    private var count : Int = 1
    private var path : String
    private var pathWithOutName : String
    private var extension : String

    constructor(
        children: ArrayList<iFile>?,
        name: String,
        lastModified: Long,
        size: Long,
        path: String,
        pathWithOutName: String,
        extension: String
    ) {

        if (children != null) {
            this.children = children
            this.count = children.size
        }
        else {
            this.children = ArrayList()
            this.count = 0
        }

        this.name = name
        this.lastModified = lastModified
        this.size = size
        this.path = path
        this.pathWithOutName = pathWithOutName
        this.extension = extension
    }

    constructor(
        children: ArrayList<iFile>?,
        file: java.io.File
    ) : this(children = children,
        name = file.name,
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
        return false
    }

    override fun isFolder(): Boolean {
        return true
    }

    override fun findWithPath(path: String): iFile? {
        if (this.path.equals(path))
            return this
        for (iFile in this.children) {
            val temp = iFile.findWithPath(path)
            if (temp != null )
                return temp
        }
        return null
    }

    override fun findWithName(name: String): List<iFile> {
        val result = ArrayList<iFile>()

        if (this.name.contains(name))
            result.add(this)

        for (iFile in this.children) {
            val temp = iFile.findWithName(name)
            if (temp.isNotEmpty() )
                result.addAll(temp)
        }

        return result
    }

    fun add(file : iFile) {
        this.children.add(file)
    }

    fun remove(file : iFile) {
        this.children.remove(file)
    }

    fun getChildren() : ArrayList<iFile> {
        return this.children
    }

    fun sort(type : String) : ArrayList<iFile> {
        return this.children
    }
}
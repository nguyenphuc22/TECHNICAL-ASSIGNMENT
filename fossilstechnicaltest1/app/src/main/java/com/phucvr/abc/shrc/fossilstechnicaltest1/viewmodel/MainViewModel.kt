package com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.phucvr.abc.shrc.fossilstechnicaltest1.core.FileManager
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.iRepository
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.Settings
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort


open class MainViewModel(private val repository: iRepository) : ViewModel(), iOnClickFolder{

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
        const val MODE_LIST_ESSENTIAL = "Essentials"
        const val MODE_LIST_ALL = "All"
    }

    val listData = mutableStateListOf<iFile>()
    val listSelected = mutableStateListOf<iFile>()
    val fileManager = FileManager.getInstance()
    private var isIncreasedSort = true
    private var TYPE_SORT = TypeSort.NAME
    private var callBack: (iFile) -> Unit = {}



    private val menusLeft = listOf(MODE_LIST_ESSENTIAL,MODE_LIST_ALL)
    private val menusRight = listOf(TypeSort.TYPE.name,TypeSort.DATE.name,TypeSort.NAME.name,TypeSort.SIZE.name)
    private var modeList = menusLeft.first()

    var isShowLeftMenus = mutableStateOf(1.0f)
    var isShowRightMenus = mutableStateOf(1.0f)

    fun getMenuLeft() : List<String> {
        return menusLeft
    }

    fun getModeList() : String {
        return modeList
    }

    fun getTypeSort() : Pair<TypeSort,Boolean> {
        return Pair(TYPE_SORT,isIncreasedSort)
    }

    fun getMenuRight() : List<String> {
        return menusRight
    }

    fun setModeList(mode : String) {
        this.modeList = mode
    }

    fun getAllData() {
        val data = repository.getData()
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
        showOnOffMenusLeft()
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
        showOnOffMenusLeft()
    }

    fun showOnOffMenusLeft() {
        if (fileManager.isRootFolder()) {
            showLeftMenus()
        } else {
            hideLeftMenus()
        }
    }

    fun isRootFolder() : Boolean {
        return fileManager.isRootFolder()
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

    fun isModeEssentials() : Boolean {
        if (this.modeList.equals(MODE_LIST_ESSENTIAL)) {
            return true
        }
        return false
    }

    fun backStack() {
        onClickBack()
        if (isRootFolder()) {
            if (isModeEssentials()) {
                turnOnModeEssentials()
            } else {
                turnOffModeEssentials()
            }
            sort()
        }
    }

    fun copyFiles(list: List<iFile>) {
        this.repository.copyFiles(list,this.fileManager.currentPath())
    }

    fun moveFiles(list : List<iFile>, pathTo : String) {
        this.repository.moveFiles(list, pathTo)
    }

    fun moveFiles(list : List<iFile>) {
        this.repository.moveFiles(list, this.fileManager.currentPath())
    }

    fun deleteFile(list : List<iFile>) {
        this.repository.deleteFiles(list)
    }

    fun hideRightMenus() {
        this.isShowRightMenus.value = 0.0F
    }

    fun showRightMenus() {
        this.isShowRightMenus.value = 1.0F
    }

    fun hideLeftMenus() {
        this.isShowLeftMenus.value = 0.0F
    }

    fun showLeftMenus() {
        this.isShowLeftMenus.value = 1.0F
    }

    fun getCurrentPath() {
        this.fileManager.currentPath()
    }

    fun sort(typeSort: TypeSort? = null, isIncreased: Boolean? = null) {
        typeSort?.let {
            TYPE_SORT = typeSort
        }
        isIncreased?.let {
            isIncreasedSort = isIncreased
        }
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
                    this.listData.sortBy { it.getCount() }
                } else {
                    this.listData.sortByDescending { it.getCount() }
                }
            }
        }
    }
}
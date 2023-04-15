package com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel

import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile

interface iOnClickFolder {

    fun onClickFolder(iFile: iFile)

    fun onClickFile(iFile: iFile)

    fun onClickBack()

}
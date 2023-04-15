package com.phucvr.abc.shrc.fossilstechnicaltest1.repository

import androidx.lifecycle.LiveData
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile

interface iRepository {

    fun getAllStorage() : ArrayList<iFile>

    fun getData() : ArrayList<iFile>
}
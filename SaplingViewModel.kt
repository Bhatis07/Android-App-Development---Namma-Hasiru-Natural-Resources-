package com.internshipproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaplingViewModel(application: Application) : AndroidViewModel(application) {
    private val saplingDao: SaplingDao = AppDatabase.getInstance(application).saplingDao()
    val allSaplings: LiveData<List<Sapling>> = saplingDao.getAllSaplings()

    fun insert(sapling: Sapling) = viewModelScope.launch(Dispatchers.IO) {
        saplingDao.insert(sapling)
    }

    fun update(sapling: Sapling) = viewModelScope.launch(Dispatchers.IO) {
        saplingDao.update(sapling)
    }
}

package com.aulakotlin.appnotas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aulakotlin.appnotas.data.db.AppDatabase
import com.aulakotlin.appnotas.data.model.Folder
import kotlinx.coroutines.launch

class FolderViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    val folders: LiveData<List<Folder>> = db.folderDao().getAll()

    fun insert(folder: Folder) {
        viewModelScope.launch {
            db.folderDao().insert(folder)
        }
    }

    fun delete(folder: Folder) = viewModelScope.launch {
        db.folderDao().delete(folder)
    }


}
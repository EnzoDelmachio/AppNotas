package com.aulakotlin.appnotas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aulakotlin.appnotas.data.db.AppDatabase
import com.aulakotlin.appnotas.data.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)

    fun getNotes(folderId: Long) = db.noteDao().getNotesByFolderId(folderId)

    fun insert(note: Note) = viewModelScope.launch {
        db.noteDao().insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        db.noteDao().update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        db.noteDao().delete(note)
    }

}
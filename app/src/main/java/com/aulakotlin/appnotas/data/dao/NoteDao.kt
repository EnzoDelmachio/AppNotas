package com.aulakotlin.appnotas.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aulakotlin.appnotas.data.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes WHERE folderId = :folderId ORDER BY timestamp DESC")
    fun getNotesByFolderId(folderId: Long): LiveData<List<Note>>

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

}
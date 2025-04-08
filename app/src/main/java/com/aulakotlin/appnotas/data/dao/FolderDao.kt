package com.aulakotlin.appnotas.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aulakotlin.appnotas.data.model.Folder

@Dao
interface FolderDao {

    @Query("SELECT * FROM folders ORDER BY name")
    fun getAll(): LiveData<List<Folder>>

    @Insert
    suspend fun insert(folder: Folder)

}
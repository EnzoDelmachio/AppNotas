package com.aulakotlin.appnotas.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aulakotlin.appnotas.data.dao.FolderDao
import com.aulakotlin.appnotas.data.dao.NoteDao
import com.aulakotlin.appnotas.data.model.Folder
import com.aulakotlin.appnotas.data.model.Note

@Database(entities = [Note::class, Folder::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun folderDao(): FolderDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notes_db"
                ).build().also { INSTANCE = it }
            }
    }
}
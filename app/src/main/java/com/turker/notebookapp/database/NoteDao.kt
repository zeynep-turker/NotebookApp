package com.turker.notebookapp.database

import androidx.room.*
import com.turker.notebookapp.entity.Note

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)
}
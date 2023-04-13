package com.turker.notebookapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.turker.notebookapp.entity.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNotes(): LiveData<List<Note>>
}
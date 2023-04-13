package com.turker.notebookapp.repository

import androidx.lifecycle.LiveData
import com.turker.notebookapp.database.NoteDao
import com.turker.notebookapp.entity.Note

class NoteRepository(private val notesDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) = notesDao.insert(note)

    suspend fun update(note: Note) = notesDao.update(note)

    suspend fun delete(note: Note) = notesDao.delete(note)
}
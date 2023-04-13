package com.turker.notebookapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.turker.notebookapp.database.Database
import com.turker.notebookapp.entity.Note
import com.turker.notebookapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    private val noteRepository: NoteRepository

    init {
        val noteDb = Database.getNoteDatabase(application)
        val noteDao = noteDb!!.noteDao()
        noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.allNotes
    }

    fun insert(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        noteRepository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }
}
package com.turker.notebookapp.adapter

import com.turker.notebookapp.entity.Note

interface RecyclerListener {
    fun cardOnClick(note: Note)
    fun deleteButtonOnClick(note: Note)
}
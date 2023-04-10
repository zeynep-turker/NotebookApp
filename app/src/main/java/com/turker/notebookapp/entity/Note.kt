package com.turker.notebookapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note(title: String, content : String) {
    @PrimaryKey(autoGenerate = true)
    val ID: Int = 0

    @ColumnInfo(name = "title")
    val title: String? = title

    @ColumnInfo(name = "content")
    val content: String? = content
}
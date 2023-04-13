package com.turker.notebookapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note(title: String, content: String, imageUrl: String?) {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0

    @ColumnInfo(name = "title")
    var title: String = title

    @ColumnInfo(name = "content")
    var content: String = content

    @ColumnInfo(name = "image_url")
    var imageUrl: String? = imageUrl
}
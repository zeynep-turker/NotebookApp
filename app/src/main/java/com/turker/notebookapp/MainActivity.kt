package com.turker.notebookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.turker.notebookapp.database.Database
import com.turker.notebookapp.entity.Note

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database: Database? = Database.getNoteDatabase(this)

        val note = Note("A", "B")
        database?.noteDao()?.insert(note)

    }
}
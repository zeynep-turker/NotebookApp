package com.turker.notebookapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.turker.notebookapp.entity.Note


@androidx.room.Database(entities = [Note::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: Database? = null

        fun getNoteDatabase(context: Context): Database? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context, Database::class.java, "note.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }
}
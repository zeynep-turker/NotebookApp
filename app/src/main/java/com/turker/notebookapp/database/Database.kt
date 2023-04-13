package com.turker.notebookapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.turker.notebookapp.entity.Note

@androidx.room.Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: Database? = null

        fun getNoteDatabase(context: Context): Database? {

            if (instance == null) {
                synchronized(Database::class.java) {
                    if (instance == null){
                        instance = Room.databaseBuilder(context,
                            Database::class.java, "note_database").allowMainThreadQueries().build()
                    }
                }
            }
            return instance
        }
    }
}
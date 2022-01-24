package com.example.inote.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inote.Model.Notes
import com.example.inote.dao.NotesDao

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase()
{
    abstract fun myNotesDao():NotesDao

    companion object
    {
        @Volatile
        var INSTANCE:NotesDatabase? = null

        fun getDatabaseInstance(context: Context):NotesDatabase
        {
            var tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabsaeInstance =
                    Room.databaseBuilder(context,NotesDatabase::class.java,"iNotes").allowMainThreadQueries().build()
                INSTANCE = roomDatabsaeInstance
                return roomDatabsaeInstance
            }
        }
    }
}
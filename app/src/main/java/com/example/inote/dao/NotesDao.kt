package com.example.inote.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.inote.Model.Notes

@Dao
interface NotesDao
{
    @Query("Select * from iNotes")
    fun getNote(): LiveData<List<Notes>>

    @Query("Select * from iNotes where priority = 3 ")
    fun getHighNote(): LiveData<List<Notes>>

    @Query("Select * from iNotes where priority = 2 ")
    fun getMediumNote(): LiveData<List<Notes>>

    @Query("Select * from iNotes where priority = 1 ")
    fun getLowNote(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note:Notes)

    @Query("Delete from iNotes where id=:id")
    fun deleteNote(id:Int)

    @Update
    fun updateNote(note:Notes)
}
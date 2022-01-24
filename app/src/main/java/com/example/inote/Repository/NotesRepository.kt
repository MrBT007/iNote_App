package com.example.inote.Repository

import androidx.lifecycle.LiveData
import com.example.inote.Model.Notes
import com.example.inote.dao.NotesDao

class NotesRepository(val dao:NotesDao)
{
    fun getAllNotes(): LiveData<List<Notes>>
    {
        return dao.getNote()
    }
    fun insertNote(note:Notes)
    {
        return dao.insertNote(note)
    }
    fun updateNote(note: Notes)
    {
        return dao.updateNote(note)
    }
    fun deleteNote(id:Int)
    {
        return dao.deleteNote(id)
    }
}
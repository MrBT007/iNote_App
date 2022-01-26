package com.example.inote.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.inote.Database.NotesDatabase
import com.example.inote.Model.Notes
import com.example.inote.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application)
{
    val repository:NotesRepository

    // Init block executes first
    init {
        // If INSTANCE.dao not work then replace it with getDatabaseInstance
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun addNotes(data: Notes)
    {
        repository.insertNote(data)
    }

    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()
    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun insertNotes(notes:Notes)
    {
        repository.insertNote(notes)
    }

    fun updateNotes(notes: Notes)
    {
        repository.updateNote(notes)
    }

    fun deleteNotes(id: Int)
    {
        repository.deleteNote(id)
    }
}
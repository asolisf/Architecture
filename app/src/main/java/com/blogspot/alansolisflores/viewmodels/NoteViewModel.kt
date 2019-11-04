package com.blogspot.alansolisflores.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.blogspot.alansolisflores.entities.Note
import com.blogspot.alansolisflores.repositories.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository
    private val allNotes: LiveData<List<Note>>

    init{
        noteRepository = NoteRepository(application)
        allNotes = noteRepository.getAllNotes()

    }

    fun insert(note: Note) = viewModelScope.launch{
        noteRepository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch{
        noteRepository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch{
        noteRepository.delete(note)
    }

    fun deleteAll() = viewModelScope.launch{
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>>{
        return this.allNotes
    }
}
package com.blogspot.alansolisflores.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.blogspot.alansolisflores.dao.NoteDAO
import com.blogspot.alansolisflores.databases.NoteDatabase
import com.blogspot.alansolisflores.entities.Note
import kotlinx.coroutines.*

class NoteRepository {

    private val noteDao: NoteDAO

    private val allNotes: LiveData<List<Note>>

    constructor(application: Application){
        val noteDatabase: NoteDatabase = NoteDatabase.getInstance(application, GlobalScope)
        noteDao = noteDatabase.noteDAO()
        allNotes = noteDao.getAll()
    }

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note)

    }

    suspend fun delete(note: Note){
        noteDao.delete(note)

    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAll()
    }

    fun getAllNotes(): LiveData<List<Note>>{
        return noteDao.getAll()
    }
}
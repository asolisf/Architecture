package com.blogspot.alansolisflores.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.blogspot.alansolisflores.entities.Note

@Dao
interface NoteDAO {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAll(): LiveData<List<Note>>
}
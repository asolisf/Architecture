package com.blogspot.alansolisflores.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.alansolisflores.R
import com.blogspot.alansolisflores.adapters.NoteAdapter
import com.blogspot.alansolisflores.entities.Note
import com.blogspot.alansolisflores.viewmodels.NoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.recyclerView = findViewById(R.id.recyclerListView)
        val adapter = NoteAdapter(emptyList<Note>(),R.id.noteItem,this)
        this.recyclerView.adapter = adapter

        this.noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        this.noteViewModel.getAllNotes().observe(this, Observer { notes ->
            notes?.let {
                adapter.setNotes(it)
            }
        })
    }
}

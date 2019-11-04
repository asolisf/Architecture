package com.blogspot.alansolisflores.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.alansolisflores.R
import com.blogspot.alansolisflores.entities.Note

class NoteAdapter(notes: List<Note>,layout: Int,context: Context):
    RecyclerView.Adapter<NoteAdapter.Companion.ViewHolder>() {

    private lateinit var notes: List<Note>

    private val layout: Int

    private val context: Context

    init{
        this.notes = notes
        this.context = context
        this.layout = layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(this.layout,parent,false)
        val viewHolder = ViewHolder(itemView)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return this.notes.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Bind(this.notes[position].title,this.notes[position].description,this.notes[position].priority)
    }

    fun setNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    companion object{
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val title: TextView
            private val description: TextView
            private val priority: TextView

            init{
                this.title = itemView.findViewById(R.id.title)
                this.description = itemView.findViewById(R.id.title)
                this.priority = itemView.findViewById(R.id.title)
            }

            fun Bind(title: String,description: String,protity: Int?){
                this.title.setText(title)
                this.description.setText(description)
                this.priority.setText(priority.toString())
            }
        }
    }
}
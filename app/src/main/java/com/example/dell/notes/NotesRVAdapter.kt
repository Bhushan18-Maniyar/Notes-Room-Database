package com.example.dell.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NotesRVAdapter(val context: Context, private val listener: INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes =  ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }


    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text

    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
    }
}

interface INotesRVAdapter {
    fun onClick(note: Note)
}
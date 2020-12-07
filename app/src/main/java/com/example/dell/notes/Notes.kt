package com.example.dell.notes

import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notes.*

class Notes : AppCompatActivity(), INotesRVAdapter {


    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)


        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this,  Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })


    }

    override fun onClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted!", Toast.LENGTH_SHORT).show()
    }

    fun insert(view: View) {
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "${noteText} inserted!", Toast.LENGTH_SHORT).show()
        }
    }
}

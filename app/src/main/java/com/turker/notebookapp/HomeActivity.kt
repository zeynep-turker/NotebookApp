package com.turker.notebookapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.turker.notebookapp.adapter.NoteAdapter
import com.turker.notebookapp.adapter.RecyclerListener
import com.turker.notebookapp.databinding.ActivityHomeBinding
import com.turker.notebookapp.entity.Note
import com.turker.notebookapp.viewmodel.NoteViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var adapter: NoteAdapter
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        adapter = NoteAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setListener(object : RecyclerListener {
            override fun cardOnClick(note: Note) {
                val intent = Intent(applicationContext, NoteActivity::class.java)
                intent.putExtra("id", note.ID)
                intent.putExtra("title", note.title)
                intent.putExtra("content", note.content)
                intent.putExtra("imageUrl", note.imageUrl)
                startActivityForResult(intent, 1)
            }

            override fun deleteButtonOnClick(note: Note) {
                noteViewModel.delete(note)
            }
        })

        observeData()
    }

    private fun observeData() {
        binding.saveNoteBtn.setOnClickListener {
            saveNote()
        }

        noteViewModel.allNotes.observe(this, Observer
        { notes ->
            notes.let {
                if (notes.isEmpty()) binding.noNoteTxt.visibility = View.VISIBLE
                else binding.noNoteTxt.visibility = View.GONE
                adapter.setNotes(it)
            }
        })
    }

    private fun saveNote() {
        val intent = Intent(this, NoteActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra("id", -1)
            val title = data?.getStringExtra("title") ?: ""
            val content = data?.getStringExtra("content") ?: ""
            val imageUrl = data?.getStringExtra("imageUrl") ?: ""

            val note = Note(title, content, imageUrl)

            if (id != -1) {
                note.ID = id!!
                noteViewModel.update(note)
                Toast.makeText(applicationContext, "Güncellendi", Toast.LENGTH_LONG).show()
            } else {
                noteViewModel.insert(note)
                Toast.makeText(applicationContext, "Eklendi", Toast.LENGTH_LONG).show()
            }

        }
    }
}
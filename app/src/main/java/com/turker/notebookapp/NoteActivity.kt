package com.turker.notebookapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.turker.notebookapp.database.Database
import com.turker.notebookapp.databinding.ActivityNoteBinding
import com.turker.notebookapp.entity.Note

class NoteActivity : AppCompatActivity() {
    private var note: Note? = null
    private var database: Database? = null
    private lateinit var binding: ActivityNoteBinding
    private var processType = ProcessType.CREATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Database.getNoteDatabase(this)

        if (intent.getIntExtra("id", -1) != -1) {
            processType = ProcessType.UPDATE
            note = Note(
                intent.getStringExtra("title").toString(),
                intent.getStringExtra("content").toString(),
                intent.getStringExtra("imageUrl").toString()
            )
            note!!.ID = intent.getIntExtra("id", -1)
            updateUI()
        }

        observeData()
    }

    private fun observeData() {
        binding.saveNote.setOnClickListener {
            saveNote()
        }
        binding.title.doAfterTextChanged {
            changeSaveBtnVisibility(
                it.toString(),
                binding.content.text.toString(),
                binding.imageUrl.text.toString()
            )
        }
        binding.content.doAfterTextChanged {
            changeSaveBtnVisibility(
                binding.title.text.toString(),
                it.toString(),
                binding.imageUrl.text.toString()
            )
        }
        binding.imageUrl.doAfterTextChanged {
            changeSaveBtnVisibility(
                binding.title.text.toString(),
                binding.content.text.toString(),
                it.toString()
            )
        }
    }

    private fun changeSaveBtnVisibility(title: String, content: String, imageUrl: String) {
        binding.apply {
            if (processType == ProcessType.UPDATE) {
                note?.let { note ->
                    if (title.isNotEmpty() && imageUrl.isNotEmpty() && content.isNotEmpty() && (title != note.title || imageUrl != note.imageUrl || content != note.content))
                        saveNote.visibility = View.VISIBLE
                    else
                        saveNote.visibility = View.GONE
                }
            } else {
                if (title.isNotEmpty() && imageUrl.isNotEmpty() && content.isNotEmpty())
                    saveNote.visibility = View.VISIBLE
                else
                    saveNote.visibility = View.GONE
            }
        }
    }

    private fun updateUI() {
        binding.title.setText(note?.title)
        binding.content.setText(note?.content)
        binding.imageUrl.setText(note?.imageUrl)
    }

    private fun saveNote() {
        val returnIntent = Intent()

        if (processType == ProcessType.UPDATE) returnIntent.putExtra("id", note?.ID)
        returnIntent.putExtra("title", binding.title.text?.trim().toString())
        returnIntent.putExtra("content", binding.content.text?.trim().toString())
        returnIntent.putExtra("imageUrl", binding.imageUrl.text?.trim().toString())
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    enum class ProcessType {
        UPDATE, CREATE
    }
}
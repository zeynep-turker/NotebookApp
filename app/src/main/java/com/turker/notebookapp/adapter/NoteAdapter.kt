package com.turker.notebookapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turker.notebookapp.databinding.ItemviewNoteBinding
import com.turker.notebookapp.entity.Note

class NoteAdapter(private val context: Context) :
    RecyclerView.Adapter<NoteAdapter.NoteItemViewHolder>() {

    private var notes: List<Note> = mutableListOf()
    private lateinit var listener: RecyclerListener

    fun setListener(listener: RecyclerListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.NoteItemViewHolder = NoteItemViewHolder(
        ItemviewNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val note: Note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(allNotes: List<Note>) {
        notes = allNotes
        notifyDataSetChanged()
    }

    inner class NoteItemViewHolder(private val binding: ItemviewNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.title.text = note.title
            binding.content.text = note.content

            Glide.with(context).load(note.imageUrl).fitCenter().into(binding.imageUrl)

            binding.noteCardView.setOnClickListener {
                listener.cardOnClick(note)
            }
            binding.deleteNoteBtn.setOnClickListener {
                listener.deleteButtonOnClick(note)
            }
        }
    }
}
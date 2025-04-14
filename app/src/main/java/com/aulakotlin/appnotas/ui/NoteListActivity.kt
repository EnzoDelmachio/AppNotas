package com.aulakotlin.appnotas.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aulakotlin.appnotas.adapter.NoteAdapter
import com.aulakotlin.appnotas.databinding.ActivityNoteListBinding
import com.aulakotlin.appnotas.viewModel.NoteViewModel

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var adapter: NoteAdapter
    private var folderId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receber o ID da pasta
        folderId = intent.getLongExtra("folderId", -1L)
        if (folderId == -1L) {
            finish()
            return
        }

        adapter = NoteAdapter(
            notes = emptyList(),
            onClick = { note ->
                val intent = Intent(this, EditNoteActivity::class.java)
                intent.putExtra("noteId", note.id)
                startActivity(intent)
            },
            onLongClick = { note ->
                AlertDialog.Builder(this)
                    .setTitle("Excluir nota")
                    .setMessage("Tem certeza que deseja excluir esta nota?")
                    .setPositiveButton("Sim") { _, _ ->
                        viewModel.delete(note)
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
        )


        binding.recyclerNotes.layoutManager = LinearLayoutManager(this)

        binding.recyclerNotes.adapter = adapter

        viewModel.getNotes(folderId).observe(this) { notes ->
            adapter.updateData(notes)
        }

        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            intent.putExtra("folderId", folderId)
            startActivity(intent)
        }
    }
}

package com.aulakotlin.appnotas.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aulakotlin.appnotas.data.db.AppDatabase
import com.aulakotlin.appnotas.data.model.Note
import com.aulakotlin.appnotas.databinding.ActivityEditNoteBinding
import com.aulakotlin.appnotas.viewModel.NoteViewModel

class EditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding
    private val viewModel: NoteViewModel by viewModels()

    private var noteId: Long = -1L
    private var folderId: Long = -1L
    private var currentNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteId = intent.getLongExtra("noteId", -1L)
        folderId = intent.getLongExtra("folderId", -1L)

        if (noteId != -1L) {
            // Editando uma nota existente
            Thread {
                val note = AppDatabase.getInstance(this).noteDao().getById(noteId)
                runOnUiThread {
                    if (note != null) {
                        currentNote = note
                        binding.editTitle.setText(note.title)
                        binding.editContent.setText(note.content)
                    }
                }
            }.start()
        }

        binding.btnSave.setOnClickListener {
            val title = binding.editTitle.text.toString().trim()
            val content = binding.editContent.text.toString().trim()

            if (title.isEmpty()) {
                Toast.makeText(this, "Informe um título", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val noteToSave = if (currentNote != null) {
                currentNote!!.copy(title = title, content = content)
            } else {
                Note(
                    title = title,
                    content = content,
                    folderId = folderId
                )
            }

            if (currentNote != null) {
                viewModel.update(noteToSave)
            } else {
                viewModel.insert(noteToSave)
            }

            finish() // Fecha e volta à lista
        }
    }
}

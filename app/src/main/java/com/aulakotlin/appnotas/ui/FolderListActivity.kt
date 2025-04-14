package com.aulakotlin.appnotas.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aulakotlin.appnotas.adapter.FolderAdapter
import com.aulakotlin.appnotas.data.model.Folder
import com.aulakotlin.appnotas.databinding.ActivityFolderListBinding
import com.aulakotlin.appnotas.viewModel.FolderViewModel

class FolderListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFolderListBinding
    private val viewModel: FolderViewModel by viewModels()
    private lateinit var adapter: FolderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFolderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o adapter
        adapter = FolderAdapter(
            folders = emptyList(),
            onClick = { folder ->
                val intent = Intent(this, NoteListActivity::class.java)
                intent.putExtra("folderId", folder.id)
                startActivity(intent)
            },
            onLongClick = { folder ->
                AlertDialog.Builder(this)
                    .setTitle("Excluir pasta")
                    .setMessage("Tem certeza que deseja excluir a pasta \"${folder.name}\"?\nTodas as notas dessa pasta também serão apagadas.")
                    .setPositiveButton("Sim") { _, _ ->
                        viewModel.delete(folder)
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
        )


        // RecyclerView
        binding.recyclerFolders.layoutManager = LinearLayoutManager(this)

        binding.recyclerFolders.adapter = adapter

        // Observar dados
        viewModel.folders.observe(this) { folders ->
            adapter.updateData(folders)
        }

        // Botão de adicionar
        binding.fabAdd.setOnClickListener {
            // Criar uma pasta simples só com nome para testar
            val novaPasta = Folder(name = "Nova Pasta")
            viewModel.insert(novaPasta)
        }
    }
}

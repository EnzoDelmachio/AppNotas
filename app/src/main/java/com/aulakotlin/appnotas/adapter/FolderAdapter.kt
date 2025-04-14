package com.aulakotlin.appnotas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aulakotlin.appnotas.data.model.Folder
import com.aulakotlin.appnotas.databinding.ItemFolderBinding

class FolderAdapter(
    private var folders: List<Folder>,
    private val onClick: (Folder) -> Unit,
    private val onLongClick: (Folder) -> Unit
) : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    inner class FolderViewHolder(val binding: ItemFolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(folder: Folder) {
            binding.tvFolderName.text = folder.name

            binding.root.setOnClickListener {
                onClick(folder)
            }

            binding.root.setOnLongClickListener {
                onLongClick(folder)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val binding = ItemFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FolderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(folders[position])
    }

    override fun getItemCount(): Int = folders.size

    fun updateData(newList: List<Folder>) {
        folders = newList
        notifyDataSetChanged()
    }
}

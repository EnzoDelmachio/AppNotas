package com.aulakotlin.appnotas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aulakotlin.appnotas.ui.FolderListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Redireciona direto pra tela de pastas
        startActivity(Intent(this, FolderListActivity::class.java))
        finish()
    }
}

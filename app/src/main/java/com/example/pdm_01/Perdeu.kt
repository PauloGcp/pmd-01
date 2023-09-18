package com.example.pdm_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Perdeu : AppCompatActivity() {
    private lateinit var resultado: TextView
    private lateinit var novoJogo: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perdeu)
        this.resultado = findViewById(R.id.resultado)
        this.novoJogo = findViewById(R.id.novoJogo)
        if(intent.hasExtra("STATUS")){
            resultado.text = intent.getStringExtra("STATUS")
        }
        this.novoJogo.setOnClickListener{
            reiniciar()
        }
    }
    fun reiniciar() {
        finish()
    }
}
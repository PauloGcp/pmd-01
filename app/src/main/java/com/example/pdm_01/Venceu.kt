package com.example.pdm_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Venceu : AppCompatActivity() {
    private lateinit var resultado: TextView
    private lateinit var novoJogo: Button
    private lateinit var nomeDoGanhador: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venceu)
        this.resultado = findViewById(R.id.resultado)
        this.novoJogo = findViewById(R.id.novoJogo)
        this.nomeDoGanhador = findViewById(R.id.nomeGanhador)
        if(intent.hasExtra("STATUS")){
            resultado.text = intent.getStringExtra("STATUS")
        }

        this.novoJogo.setOnClickListener{
            if(nomeDoGanhador.text.toString().isEmpty()){
                Toast.makeText(this, "Informe o nome do ganhador", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            reiniciar()
        }
    }
    fun reiniciar() {
        val intent = Intent().apply{
            putExtra("GANHADOR", nomeDoGanhador.text.toString())
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}
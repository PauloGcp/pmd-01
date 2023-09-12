package com.example.pdm_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.OnLongClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var menorValor: TextView
    private lateinit var maiorValor: TextView
    private lateinit var valorDoInput: EditText
    private lateinit var status: TextView
    private lateinit var botaoDoChute: Button
    private var jogo = Jogo(0, 100);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menorValor = findViewById(R.id.menorValor)
        maiorValor = findViewById(R.id.maiorValor)
        status = findViewById(R.id.status)
        valorDoInput = findViewById(R.id.valorDoInput)
        botaoDoChute = findViewById(R.id.botaoDoChute)

        menorValor.text = jogo.menorNumero.toString()
        maiorValor.text = jogo.maiorNumero.toString()

        botaoDoChute.setOnClickListener{
            val tentativa = if (valorDoInput.toString().isEmpty()) null else valorDoInput.toString().toInt()

            if (tentativa === null){
                Toast.makeText(this, "Informe um número válido", Toast.LENGTH_LONG).show()
                //esse return faz com que saia do lambda em que está contido (neste caso o setOnClickListener)
                return@setOnClickListener
            }
            status.text = jogo.realizarJogada(tentativa)

            when(status.toString()){
                "Em andamento" -> {
                    Toast.makeText(this, "Jogo em andamento", Toast.LENGTH_LONG).show()
                }
                "Perdeu." -> {
                    Toast.makeText(this, "Você Perdeu", Toast.LENGTH_LONG).show()
                }
                "Venceu!" -> {
                    Toast.makeText(this, "Você Venceu", Toast.LENGTH_LONG).show()
                }
            }
            status.setOnLongClickListener(OnLongClickListener{
                this.jogo = Jogo(1, 100)
                menorValor.text = jogo.menorNumero.toString()
                menorValor.text = jogo.menorNumero.toString()
                status.text = jogo.status
                true
            })
        }
    }
}
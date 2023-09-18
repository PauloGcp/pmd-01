package com.example.pdm_01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.OnLongClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var menorValor: TextView
    private lateinit var maiorValor: TextView
    private lateinit var valorDoInput: EditText
    private lateinit var status: TextView
    private lateinit var botaoDoChute: Button
    private lateinit var nomeDoGanhador: TextView
    private var jogo = Jogo(0, 100);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //inicialização
        menorValor = findViewById(R.id.menorValor)
        maiorValor = findViewById(R.id.maiorValor)
        status = findViewById(R.id.status)
        valorDoInput = findViewById(R.id.valorDoInput)
        botaoDoChute = findViewById(R.id.botaoDoChute)
        nomeDoGanhador = findViewById(R.id.nomeGanhador)
        //exibição default
        menorValor.text = jogo.menorNumero.toString()
        maiorValor.text = jogo.maiorNumero.toString()
        status.text = jogo.status.toString()

        //criacao do contrato
        val contrato = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                nomeDoGanhador.text = "Último vencedor: " + it.data?.getStringExtra("GANHADOR")
            }
        }


        botaoDoChute.setOnClickListener{
            val tentativa = if (valorDoInput.text.toString().isEmpty()) null else valorDoInput.text.toString().toInt()
            if (tentativa === null){
                Toast.makeText(this, "Informe um número válido", Toast.LENGTH_LONG).show()
                //esse return faz com que saia do lambda em que está contido (neste caso o setOnClickListener)
                return@setOnClickListener
            }
            status.text = jogo.realizarJogada(tentativa)
            menorValor.text = jogo.menorNumero.toString()
            maiorValor.text = jogo.maiorNumero.toString()
            Toast.makeText(this, jogo.feedBack.toString(), Toast.LENGTH_LONG).show()
            if(jogo.status.toString() == "Venceu"){
                val intent = Intent(this, Venceu::class.java).apply{
                    putExtra("STATUS", jogo.status.toString())
                }
                contrato.launch(intent)
            } else if(jogo.status.toString() == "Perdeu"){
                val intent = Intent(this, Perdeu::class.java).apply{
                    putExtra("STATUS", jogo.status.toString())
                }
                contrato.launch(intent)
            }
        }
    }
    override fun onRestart() {
        super.onRestart()
        this.jogo = Jogo(1, 100)
        menorValor.text = jogo.menorNumero.toString()
        maiorValor.text = jogo.maiorNumero.toString()
        valorDoInput.text.clear()
        status.text = jogo.status

    }
}
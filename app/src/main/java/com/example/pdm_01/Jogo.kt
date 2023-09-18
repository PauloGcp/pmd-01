package com.example.pdm_01
import android.util.Log
import kotlin.random.Random

class Jogo {
    var menorNumero: Int = 0;
    var maiorNumero: Int = 0;
    var numeroSorteado: Int = 0;
    var status: String = "";
    var feedBack: String = "";

    constructor (menor: Int, maior: Int){
        this.maiorNumero = maior;
        this.menorNumero = menor;
        this.numeroSorteado = Random.nextInt(menor, maior);
        this.status = "Em andamento";
        this.feedBack = "";
        Log.i("menor", menorNumero.toString())
        Log.i("maior", maiorNumero.toString())
    }

    fun realizarJogada(numeroEscolhido: Int): String {

        Log.i("numeroSorteado", numeroSorteado.toString())
        if(numeroEscolhido === numeroSorteado) {
            status = "Venceu";
        } else if(numeroEscolhido < menorNumero || numeroEscolhido > maiorNumero){
            status = "Perdeu";
        } else if (numeroEscolhido < numeroSorteado) {
            feedBack = "Escolha um numero maior"
            menorNumero = numeroEscolhido;
        } else if(numeroEscolhido > numeroSorteado){
            feedBack = "Escolha um numero menor"
            maiorNumero = numeroEscolhido;
        }
        return status;
    }

}
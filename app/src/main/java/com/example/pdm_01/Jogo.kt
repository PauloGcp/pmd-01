package com.example.pdm_01
import kotlin.random.Random

class Jogo {

    var menorNumero: Int = 0;
    var maiorNumero: Int = 0;
    var numeroSorteado: Int = 0;
    var status: String = "";

    constructor (menor: Int, maior: Int){
        this.maiorNumero = maior;
        this.menorNumero = menor;
        this.numeroSorteado = Random.nextInt(menor, maior);
        this.status = "Em andamento";
    }

    fun realizarJogada(numeroEscolhido: Int): String {
        if(numeroEscolhido === numeroSorteado) {
            status = "Venceu!";
            return status;
        } else if(numeroEscolhido < menorNumero || numeroEscolhido > maiorNumero){
            status = "Perdeu.";
            return status;
        } else if (numeroEscolhido < numeroSorteado) {
            menorNumero = numeroEscolhido;
        } else if(numeroEscolhido > numeroSorteado){
            maiorNumero = numeroEscolhido;
        }
        return status;
    }

}
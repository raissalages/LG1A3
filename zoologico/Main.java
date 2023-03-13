package com.company;

public class Main {

    public static void main(String[] args) {

        Ave avinha = new Ave("paulo", 2, "gente", false, "bicudinho");
        Ave raissinha = new Ave("raissinha", 5, "linda", true, "bicudona");

        Zoologico zoo = new Zoologico();

        zoo.adicionarAnimal(avinha);
        zoo.adicionarAnimal(raissinha);

        zoo.listarAnimais();

        zoo.alimentarAnimais();

    }
}

package com.company;

public abstract class  Animal implements Alimentavel{

    private String nome;
    private int idade;
    private String especie;
    private Boolean alimentado;

    public abstract void emitirSom();

    public Animal(String nome, int idade, String especie, Boolean alimentado){
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.alimentado = alimentado;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getAlimentado(){
        return alimentado;
    }

    public void setAlimentado(){
        this.alimentado = true;
    }

}



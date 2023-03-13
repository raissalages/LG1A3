package com.company;

public  class Réptil extends Animal {

    private double tempetaturaCorporal ;


    public Réptil(String nome, int idade, String especie, Boolean alimentado, double tempetaturaCorporal) {
        super(nome, idade, especie, alimentado);
        this.tempetaturaCorporal = tempetaturaCorporal;
    }


    @Override
    public void emitirSom(){
        System.out.println("aaaaaaaaaaaaa");
    }

    @Override
    public void alimentar() {

    }
}

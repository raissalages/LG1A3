package com.company;

public  class Ave extends Animal {

    private String tipoBico;

    public Ave(String nome, int idade, String especie, Boolean alimentado, String tipoBico) {
        super(nome, idade, especie, alimentado);
        this.tipoBico = tipoBico;
    }


    @Override
    public void emitirSom() {
        System.out.println("pru");

    }

    @Override
    public void alimentar() {

    }
}

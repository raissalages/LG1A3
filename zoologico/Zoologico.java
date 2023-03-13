package com.company;

import java.util.ArrayList;
import java.util.List;

    public class Zoologico {


        private List<Animal> animais;

        public Zoologico() {
            animais = new ArrayList<>();
        }

        public void adicionarAnimal(Animal animalzinho) {
            animais.add(animalzinho);
        }


        public void listarAnimais() {
            for (int i = 0; i < animais.size(); i++) {
                System.out.println("--> " + (animais.get(i)).getNome());
            }
        }

        public void alimentarAnimais() {
            for (int i = 0; i < animais.size(); i++) {
                if (!(animais.get(i).getAlimentado())) {
                    (animais.get(i)).alimentar();
                    (animais.get(i)).setAlimentado();
                    System.out.println((animais.get(i)).getNome() + " comeu");

                }
                else{
                    System.out.println((animais.get(i)).getNome() + " já está alimentado");
                }
            }
        }
    }
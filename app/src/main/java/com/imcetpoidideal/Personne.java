package com.imcetpoidideal;

import java.text.DecimalFormat;

public class Personne {
    private float taille;
    private float poids;
    private int Age;

    public Personne(float taille, float poids, int age) {
        this.taille = taille;
        this.poids = poids;
        Age = age;
    }

    public double imc() {
        return poids / Math.pow(taille, 2);
    }

    public double getPoidIdeal(boolean estHomme) {
        if (estHomme) {
        return taille-100-(taille-150)/4;
        } else {
        return taille-100-(taille-150)/2.5;
        }
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");

        return "Personne{" +
                "taille=" + taille +" m"+
                ", poids=" + poids +" Kg"+
                ", Age=" + Age +" ans"+
                ", imc= " + df.format(imc()) +
                '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Random;

/**
 *
 * @author henrisuominen
 * Hallinnoi pakkaa ja pakkojen määrästä riippuen siinä on n*52 korttia.
 */
public class Pakka {
    private Kortti[] pakka;
    private int ylin;
    private int pakkoja;
    
    public Pakka(int lkm) {
        this.pakkoja = lkm;
        this.ylin = 0;
        this.pakka = new Kortti[52 * pakkoja];
        for (int i = 0; i < pakkoja; i++) {
            for (int j = 0; j < 13; j++) {
                Kortti kortti1 = new Kortti(j + 1, "Hertta");
                Kortti kortti2 = new Kortti(j + 1, "Pata");
                Kortti kortti3 = new Kortti(j + 1, "Risti");
                Kortti kortti4 = new Kortti(j + 1, "Ruutu");
                this.pakka[4 * j + 52 * i] = kortti1;
                this.pakka[4 * j + 1 + 52 * i] = kortti2;
                this.pakka[4 * j + 2 + 52 * i] = kortti3;
                this.pakka[4 * j + 3 + 52 * i] = kortti4;
            }
        }
        this.sekoita();
    }
    
    public Kortti[] getPakka() {
        return this.pakka;
    }
    
    /**
     * sekoittaa pakan ja sen kortit
    */
    public void sekoita() {
        this.ylin = 0;
        Random random = new Random();
        for (int i = 0; i < pakkoja * 52; i++) {
            int rn = (int) Math.floor(random.nextDouble() * (pakkoja * 52 - 1) + 1);
            Kortti apu = this.pakka[i];
            this.pakka[i] = this.pakka[(i + rn) % (pakkoja * 52 - 1)];
            this.pakka[(i + rn) % (pakkoja * 52 - 1)] = apu;
        }
    }
    
    /**
     * palauttaa pakan päällinmäisimmän kortin
     * @return kortti
    */
    public Kortti getYlin() {
        if (ylin > (pakkoja * 52 - 1)) {
            return null;
        }
        return pakka[ylin++];
    }
    
    /**
     * Palauttaa pakan päällinmäisimmän kortin.
     * @return true, jos kaikki kortit on jo palautettu getYlin metodilla, muulloin false.
    */
    public boolean isEmpty() {
        if (ylin > (pakkoja * 52 - 1)) {
            return true;
        }
        return false;
    }
    
    /**
     * Kertoo kuinka suuri osa pakasta on vielä jäljellä.
     * @return palauttaa luvun 0 ja 1 välillä riippuen siitä 
     * että kuinka suuri osa pakasta on pelattu.
    */
    public double paljonkoJaljella() {
        int a = 52 * this.pakkoja - this.ylin;
        int b = 52 * this.pakkoja;
        double  c =100 * a/b;
        return c;
    }
}

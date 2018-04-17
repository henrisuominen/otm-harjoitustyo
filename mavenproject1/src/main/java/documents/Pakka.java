/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documents;

import java.util.Random;

/**
 *
 * @author henrisuominen
 */
public class Pakka {
    private Kortti[] pakka;
    private int ylin;
    private int pakkoja;
    
    public Pakka(int lkm) {
        this.pakkoja = lkm;
        this.ylin = 0;
        this.pakka = new Kortti[52*pakkoja];
        for (int i = 0; i < pakkoja; i++) {
            for (int j = 0; j < 13; j++) {
                Kortti kortti1 = new Kortti(j+1,"Hertta");
                Kortti kortti2 = new Kortti(j+1,"Pata");
                Kortti kortti3 = new Kortti(j+1,"Risti");
                Kortti kortti4 = new Kortti(j+1,"Ruutu");
                this.pakka[4*j + 52*i] = kortti1;
                this.pakka[4*j + 1 + 52*i] = kortti2;
                this.pakka[4*j + 2 + 52*i] = kortti3;
                this.pakka[4*j + 3 + 52*i] = kortti4;
            }
        }
        this.sekoita();
    }
    
    public Kortti[] getPakka() {
        return this.pakka;
    }
    
    public void tulostaPakka() {
        for (int i = 0; i < pakkoja*52; i++) {
            System.out.println(pakka[i]);
        }
    }
    
    public void sekoita() {
        this.ylin = 0;
        Random random = new Random();
        for (int i = 0; i < pakkoja*52; i++) {
            int rn = (int) Math.floor(random.nextDouble()*(pakkoja*52-1) + 1);
            Kortti apu = this.pakka[i];
            this.pakka[i] = this.pakka[(i+rn)%(pakkoja*52-1)];
            this.pakka[(i+rn)%(pakkoja*52-1)] = apu;
        }
    }
    
    public Kortti getYlin() {
        if (ylin > (pakkoja*52-1)) {
            return null;
        }
        return pakka[ylin++];
    }
    
    public boolean isEmpty() {
        if (ylin > (pakkoja*52-1)) {
            return true;
        }
        return false;
    }
}

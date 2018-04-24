/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Dealer;
import domain.Kasi;
import domain.Kortti;
import domain.Pelaaja;
import domain.Pakka;

/**
 *
 * @author henrisuominen
 */
public class Peli {
    private Pakka pakka;
    private Pelaaja[] pelaajat;

    private Kasi[] kadet;
    private Dealer dealer;
    
    public Peli(Pakka pakka, Pelaaja[] pelaajat) {
        this.pakka = pakka;
        this.pelaajat = pelaajat;

        this.kadet = new Kasi[2 * pelaajat.length];
        this.dealer = null;
    }
    
    public void sekoita() {
        this.pakka.sekoita();
    }
    
    public void panokset() {
        
    }
    
    public boolean kierros() {
        return true;
    }
    
    public void voitot() {
        if (this.dealer == null) {
            return;
        }
        for (int i = 0; i < kadet.length; i++) {
            if (this.kadet[i] != null) {
                if (this.kadet[i].getSumma() > this.dealer.getSumma() && this.kadet[i].getSumma() < 22) {
                    this.kadet[i].voitto();
                    System.out.println("JEE JEE VOITIT:" + this.kadet[i].getPelaaja() + " : " + 2 * this.kadet[i].getPanos());
                } else if (this.dealer.getSumma() > 21) {
                    this.kadet[i].voitto();
                    System.out.println("JEE JEE VOITIT:" + this.kadet[i].getPelaaja() + " : " + 2 * this.kadet[i].getPanos());
                }
            }
        }
    }
    
    public void split(int luku) {
        if (kadet[luku].getPelaaja().annaPanos(kadet[luku].getPanos())) {
            if (kadet[luku].onSamat()) {
                Kortti kortti1 = kadet[luku].getKortit().get(0);
                Kortti kortti2 = kadet[luku].getKortit().get(1);
                kadet[luku] = new Kasi(kortti1, pakka.getYlin(), kadet[luku].getPanos(), kadet[luku].getPelaaja());
                System.out.println(kadet[luku] + " : " + kadet[luku].getSumma());
                boolean active = true;
                for (int i = 0; i < kadet.length; i++) {
                    if (kadet[i] == null && active) {
                        kadet[i] = new Kasi(kortti2, pakka.getYlin(), kadet[luku].getPanos(), kadet[luku].getPelaaja());
                        System.out.println(kadet[i] + " : " + kadet[i].getSumma());
                        active = false;
                    }
                }
            }
        }
    }
    
}

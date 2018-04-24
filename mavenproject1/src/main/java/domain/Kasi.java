/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Kortti;
import domain.Pelaaja;
import java.util.ArrayList;

/**
 *
 * @author henrisuominen
 */
public class Kasi {
    private ArrayList<Kortti> kortit;
    private int panos;
    private Pelaaja pelaaja;
    
    public Kasi(Kortti kortti1, Kortti kortti2, int panos, Pelaaja pelaaja) {
        this.kortit = new ArrayList<>();
        this.kortit.add(kortti1);
        this.kortit.add(kortti2);
        this.panos = panos;
        this.pelaaja = pelaaja;
    }
    
    public boolean onSamat() {
        if (this.kortit.size() == 2 && this.kortit.get(0).getLuku() == this.kortit.get(1).getLuku()) {
            return true;
        }
        return false;
    }
    
    public void lisaa(Kortti kortti) {
        this.kortit.add(kortti);
    }
    
    public int getMinSumma() {
        int summa = 0;
        for (int i = 0; i < kortit.size(); i++) {
            switch (kortit.get(i).getLuku()) {
                case 1:
                    summa++;
                    break;
                case 11:
                    summa += 10;
                    break;
                case 12:
                    summa += 10;
                    break;
                case 13:
                    summa += 10;
                    break;
                default:
                    summa += kortit.get(i).getLuku();
            }
        }
        return summa;
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    public int getSumma() {
        int summa = this.getMinSumma();
        boolean assia = false;
        for (int i = 0; i < kortit.size(); i++) {
            if (kortit.get(i).getLuku() == 1) {
                assia = true;
                break;
            }
        }
        if (summa < 12 && assia) {
            summa += 10;
        }
        return summa;
    }
        
    public String toString() {
        return this.kortit.toString();
    }
    
    public void voitto() {
        if (this.getSumma() == 21) {
            this.pelaaja.lisaaRahaa(3 * panos);
        } else {
            this.pelaaja.lisaaRahaa(2 * panos);
        }
    }
    
    public int getPanos() {
        return this.panos;
    }
    
    public void setPanos(int panos) {
        this.panos = panos;
        this.pelaaja.annaPanos(40);
    }

    public ArrayList<Kortti> getKortit() {
        return this.kortit;
    }
}

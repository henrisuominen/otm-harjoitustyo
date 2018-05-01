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
 * pelaajan kättä kuvaava luokka.
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
    
    /**
     * Tarkistaa, etää onko aloituskäden kortit samat.
     * @return true, jos käden kaksi korttia ovat saman arvoisia, muuten false.
     */
    public boolean onSamat() {
        if (this.kortit.size() == 2 && this.kortit.get(0).getLuku() == this.kortit.get(1).getLuku()) {
            return true;
        }
        return false;
    }
    
    /**
     * Lisaa pakan päällimmäisen kortin käteen, jos käden summa on alle 21.
     * @param pakka pelissä käytettävä pakka
     */
    public void lisaa(Pakka pakka) {
        if (!pakka.isEmpty() && this.getMinSumma() < 21) {
            this.kortit.add(pakka.getYlin());
        }
    }
    
    /**
     * palauttaa korttien summan olettan kaikki ässät arvoisiksi.
     * @return palauttaa korttien arvojen summan.
     */
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
    
    /**
     * palauttaa korttien summan olettan kaikki ässät 11:n arvoisiksi, jos 
     * kaden summa ei ole suurempi kuin 21. Jos summa ylittää 21, niin tämä 
     * metodi olettaa ässät 1:n arvoiseksi.
     * @return palauttaa korttien arvojen summan.
     */
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
    
    /**
     * Lisää pelaajalle 2 kertaa panoksen verran rahaa, ellei pelaaja
     * summannut 21, jolloin panos palautetaan 3 kertaisena.
     */
    public void voitto() {
        if (this.getSumma() == 21) {
            this.pelaaja.lisaaRahaa((int) (2.5 * panos));
        } else {
            this.pelaaja.lisaaRahaa(2 * panos);
        }
    }
    
    /**
     * Tarkistaa voittiko pelaaja ja jos se voitti niin se kutsuu metodin voitto.
     * @param dealer pelin dealer
     * @return true, jos pelaaja voitti, muutoin false
     */
    public boolean voittojenJako(Dealer dealer) {
        if (this.getSumma() > dealer.getSumma() && this.getSumma() < 22) {
            this.voitto();
            return true;
        } else if (dealer.getSumma() > 21 && this.getSumma() < 22) {
            this.voitto();
            return true;
        }
        return false;
    }
    
    public int getPanos() {
        return this.panos;
    }
    
    /**
     * asettaa panoksen ja kutsuu pelaajan annaPanos metodin.
     * @param panos luku jonka verran halutaan panostaa
     */
    public void setPanos(int panos) {
        this.panos = panos;
        this.pelaaja.annaPanos(panos);
    }

    public ArrayList<Kortti> getKortit() {
        return this.kortit;
    }
}

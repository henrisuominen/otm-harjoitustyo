/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Kortti;
import domain.Pakka;
import java.util.ArrayList;

/**
 *
 * @author henrisuominen
 * luokka joka hallinnoi dealerin kättä.
 */
public class Dealer {
    private ArrayList<Kortti> kortit;

    public Dealer() {
        this.kortit = new ArrayList<>();
    }
    
    /**
     * Lisaa kortin dealerin käteen. 
     */
    public void lisaa(Kortti kortti) {
        this.kortit.add(kortti);
    }
    
    public ArrayList<Kortti> getKortit() {
        return this.kortit;
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
     * Poistaa kaikki kortit dealerilta.
     */
    public void tyhjenna() {
        this.kortit.clear();
    }
    
    /**
     * Nostaa kortteja dealerille kasinosääntöjen mukaisesti, kunnes korttien 
     * summa on suurempi tai yhtäsuuri kuin 17.
     * @param pakka 
     */
    public void nostaKortteja(Pakka pakka) {
        boolean valmis = false;
        while (!valmis) {
            if (this.getSumma() < 17) {
                this.lisaa(pakka.getYlin());
            } else {
                valmis = true;
            }
        }
    }
}

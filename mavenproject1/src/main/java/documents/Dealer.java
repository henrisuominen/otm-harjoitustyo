/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documents;

import documents.Kortti;
import documents.Pakka;
import java.util.ArrayList;

/**
 *
 * @author henrisuominen
 */
public class Dealer {
    private ArrayList<Kortti> kortit;

    
    public Dealer() {
        this.kortit = new ArrayList<>();
    }
    
    public void lisaa(Kortti kortti) {
        this.kortit.add(kortti);
    }
    
    public ArrayList<Kortti> getKortit() {
        return this.kortit;
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

    public boolean valmis() {
        if (this.getSumma() < 17) {
            return false;
        }
        return true;
    }
    
    public void tyhjenna() {
        this.kortit.clear();
    }
    
    public void nostaKortteja(Pakka pakka) {
        boolean valmis = false;
        while(!valmis) {
            if (this.getSumma() < 17) {
                this.lisaa(pakka.getYlin());
            } else {
                valmis = true;
            }
        }
    }
}

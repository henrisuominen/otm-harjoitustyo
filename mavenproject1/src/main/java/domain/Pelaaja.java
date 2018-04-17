/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author henrisuominen
 */
public class Pelaaja {
    private int rahaa;
    private String nimi;
    
    public Pelaaja(String nimi, int rahaa) {
        this.nimi = nimi;
        this.rahaa = rahaa;
    }
    
    public String toString() {
        return this.nimi + ": " + this.rahaa;
    }
    
    public boolean annaPanos(int panos) {
        if (panos == 0) {
            return false;
        } else if (this.rahaa >= panos) {
            this.rahaa -= panos;
            return true;
        }
        return false;
    }
    
    public void lisaaRahaa(int maara) {
        this.rahaa += maara;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getRahaa() {
        return this.rahaa;
    }
}

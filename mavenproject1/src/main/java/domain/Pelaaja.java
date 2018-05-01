/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author henrisuominen
 * Luokka tarjoaa tavan hallinnoida pelaajan rahaa.
 */
public class Pelaaja {
    private int id;
    private int rahaa;
    private String nimi;
    
    public Pelaaja(String nimi, int rahaa) {
        this.nimi = nimi;
        this.rahaa = rahaa;
        this.id = nimi.hashCode();
    }
    
    public String toString() {
        return this.nimi + ": " + this.rahaa;
    }
    
    /**
     * panoksen antaminen ennen peliä
     * @param   panos   pelaajalta nostettava panos
     * @return true, jos pelaajalla on tarpeeksi rahaa pelatakseen, muuten false
    */
    public boolean annaPanos(int panos) {
        if (panos == 0) {
            return false;
        } else if (this.rahaa >= panos) {
            this.rahaa -= panos;
            return true;
        }
        return false;
    }
    
    /**
     * lisätään pelaajalle rahaa
     * @param maara paljonko lisätään
    */
    public void lisaaRahaa(int maara) {
        this.rahaa += maara;
        
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getRahaa() {
        return this.rahaa;
    }
    
    public int getId() {
        return this.id;
    }
}

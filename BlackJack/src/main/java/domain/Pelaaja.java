/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 * Luokka tarjoaa tavan hallinnoida pelaajan rahaa.
 */
public class Pelaaja {
    private int id;
    private int rahaa;
    private String nimi;
    private String huijauskoodi;
    
    public Pelaaja(String nimi, int rahaa) {
        this.nimi = nimi;
        this.rahaa = rahaa;
        this.id = nimi.hashCode();
        this.huijauskoodi = "";
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
        if (this.rahaa >= panos) {
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
    
    /**
     * tarkistaa ovatko kaksi pelaajaa samat.
     * @param o vastaanottaa Objectin mutta olettaa vain Pelaaja-olioita
     * @return true jos pelaajat ovat samannimiset, muulloin false
    */
    @Override
    public boolean equals(Object o) {
        Pelaaja p  = (Pelaaja) o;
        if (this.nimi.equals(p.nimi)) {
            return true;
        }
        return false;
    }
    
    /**
     * Tätä metodia ei ole olemassakaan!
     * Unohda kaikki näkemäsi!
     * @param i vastaanottaa numeron, jonka perusteella osaa sanoa onko oikea huijauskoodi suoritettu.
     */
    public void cheatCode(int i) {
        if (i == 0) {
            huijauskoodi = "";
        } else {
            huijauskoodi += "" + i;
            if (huijauskoodi.equals("113213")) {
                this.rahaa += 1000;
            }
        }
    }
}

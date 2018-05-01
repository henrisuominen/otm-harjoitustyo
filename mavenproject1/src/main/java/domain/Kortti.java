/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author henrisuominen
 * yksinkertainen tietorakenne, joka tallettaa muuttujia maa ja luku.
 */
public class Kortti {
    private String maa;
    private int luku;
    
    public Kortti(int luku, String maa) {
        this.maa = maa;
        this.luku = luku;
    }
    
    public String getMaa() {
        return this.maa;
    }
    
    public int getLuku() {
        return this.luku;
    }
    
    public String toString() {
        return this.luku + ":" + this.maa;
    } 
    
    /**
     * palauttaa jokaiselle kortille uniikin lyhenteen, joka on sama kuin 
     * millä nimelle kortteja vastaavat kuvat ovat nimettyjä
     *
     * @return korttia kuvaava lyhenne
     */
    public String toPicture() {
        String apu;
        switch(this.maa) {
            case "Hertta":
                apu = "H";
                break;
            case "Ruutu":
                apu = "D";
                break;
            case "Pata":
                apu = "S";
                break;
            default:
                apu = "C";
                break;
        }
        if (this.luku == 1) {
            return "A" + apu;
        } 
        if (this.luku == 11) {
            return "J" + apu;
        } 
        if (this.luku == 12) {
            return "Q" + apu;
        } 
        if (this.luku == 13) {
            return "K" + apu;
        } 
        return this.luku + apu;
    } 
}

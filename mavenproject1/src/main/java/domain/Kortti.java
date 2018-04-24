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
    
    public String toPicture() {
        switch(this.maa) {
            case "Hertta":
                maa = "H";
                break;
            case "Ruutu":
                maa = "D";
                break;
            case "Pata":
                maa = "S";
                break;
            case "Risti":
                maa = "C";
                break;
        }
        if (this.luku == 1) {
            return "A" + maa;
        } 
        if (this.luku == 11) {
            return "J" + maa;
        } 
        if (this.luku == 12) {
            return "Q" + maa;
        } 
        if (this.luku == 13) {
            return "K" + maa;
        } 
        return this.luku + maa;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

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
}

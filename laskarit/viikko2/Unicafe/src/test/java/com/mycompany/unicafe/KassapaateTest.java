/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 
 * @author henrisuominen
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }    
    
    @Test
    public void luodunKassapaatteenRahamaaraJaMyytyjenLounaidenMaaraOnOikea() {
        assertEquals(100000,this.kassa.kassassaRahaa());
        assertEquals(0,this.kassa.edullisiaLounaitaMyyty()+this.kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiEdullistenOsalta() {
        this.kassa.syoEdullisesti(700);
        assertEquals(100240,this.kassa.kassassaRahaa());
        assertEquals(1,this.kassa.edullisiaLounaitaMyyty());
        this.kassa.syoEdullisesti(50);
        assertEquals(100240,this.kassa.kassassaRahaa());
        assertEquals(1,this.kassa.edullisiaLounaitaMyyty()); 
    }
    
    @Test
    public void kateisostoToimiiMaukkaidenOsalta() {
        this.kassa.syoMaukkaasti(700);
        assertEquals(100400,this.kassa.kassassaRahaa());
        assertEquals(1,this.kassa.maukkaitaLounaitaMyyty());
        this.kassa.syoMaukkaasti(50);
        assertEquals(100400,this.kassa.kassassaRahaa());
        assertEquals(1,this.kassa.maukkaitaLounaitaMyyty()); 
    }    

    @Test
    public void korttiOstoEdullistenLounaidenOsalta() {
        Maksukortti kortti = new Maksukortti(250);
        this.kassa.syoEdullisesti(kortti);
        assertEquals(100000,this.kassa.kassassaRahaa());
        assertEquals(1,this.kassa.edullisiaLounaitaMyyty());
        this.kassa.syoEdullisesti(kortti);
        assertEquals(100000,this.kassa.kassassaRahaa());
        assertEquals(1,this.kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiOstoMaukkaidenLounaidenOsalta() {
        Maksukortti kortti = new Maksukortti(420);
        this.kassa.syoMaukkaasti(kortti);
        assertEquals(100000,this.kassa.kassassaRahaa());
        assertEquals(1,this.kassa.maukkaitaLounaitaMyyty());
        this.kassa.syoMaukkaasti(kortti);
        assertEquals(100000,this.kassa.kassassaRahaa());
        assertEquals(1,this.kassa.maukkaitaLounaitaMyyty());   
    }
    
    @Test
    public void assertTruetJaFalsetToimivat() {
        Maksukortti korttiMaukas1 = new Maksukortti(420);
        Maksukortti korttiEdullinen1 = new Maksukortti(270);
        Maksukortti korttiMaukas2 = new Maksukortti(30);
        Maksukortti korttiEdullinen2 = new Maksukortti(60);
        assertTrue(this.kassa.syoMaukkaasti(korttiMaukas1));
        assertTrue(this.kassa.syoEdullisesti(korttiEdullinen1));
        assertFalse(this.kassa.syoMaukkaasti(korttiMaukas2));
        assertFalse(this.kassa.syoEdullisesti(korttiEdullinen2));

    }
    
    @Test
    public void kortilleRahaaLadattaessaKortinSaldoMuuttuuJaKassassaOlevaRahamaaraKasvaaLadatullaSummalla() {
        Maksukortti kortti = new Maksukortti(0);
        this.kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500,this.kassa.kassassaRahaa());
        assertEquals(500,kortti.saldo());
    }
    
    
}

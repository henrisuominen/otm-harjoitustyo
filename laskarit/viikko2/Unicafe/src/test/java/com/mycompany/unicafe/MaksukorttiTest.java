package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {

        String vastaus = kortti.toString(); 

        assertEquals("saldo: 10.0", vastaus);
    }
    
    @Test
    public void lataaOikein() {
        kortti.lataaRahaa(500);
        
        String vastaus = kortti.toString(); 

        assertEquals("saldo: 15.0", vastaus);
    
    }
    
    public void vaheneeOikein() {
        kortti.otaRahaa(300);
        
        String vastaus = kortti.toString(); 

        assertEquals("saldo: 7.0", vastaus);
    
    }
    
    public void eiPoistaNegatiivisille() {
        kortti.otaRahaa(2000);
        
        String vastaus = kortti.toString(); 

        assertEquals("saldo: 10.0", vastaus);
    
    }
    
    public void palauttaaTrue() {

        assertEquals(true, kortti.otaRahaa(500));
    
    }
    
        public void palauttaaFalse() {

        assertEquals(false, kortti.otaRahaa(2000));
    
    }
    
}






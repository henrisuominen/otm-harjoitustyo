/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henrisuominen
 */
public class BlackJackTest {
    
    public BlackJackTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void korttiTesti() {   
        Kortti kortti = new Kortti(3, "Hertta");
        assertEquals(kortti.getLuku(), 3);
        assertEquals(kortti.getMaa(), "Hertta");
        assertEquals(kortti.toString(), "3:Hertta");
    }
    
    @Test
    public void pakkaTesti() {   
        Pakka pakka = new Pakka(2);
        assertEquals(pakka.getPakka().length, 104);
        assertEquals(pakka.isEmpty(), false);
        pakka.sekoita();
        for (int i = 1; i < pakka.getPakka().length; i++) {
            pakka.getYlin();
        }
        assertEquals(pakka.isEmpty(), false);
        pakka.getYlin();
        assertEquals(pakka.isEmpty(), true);
    }
    
    @Test
    public void pelaajaTesti() {
        Pelaaja kalle = new Pelaaja("Kalle", 500);
        assertEquals(kalle.getNimi(), "Kalle");
        assertEquals(kalle.getRahaa(), 500);
        kalle.lisaaRahaa(200);
        assertEquals(kalle.getRahaa(), 700);
        assertTrue(kalle.annaPanos(500));
        assertFalse(kalle.annaPanos(500));
        assertEquals(kalle.toString(), "Kalle: 200");
    }
}

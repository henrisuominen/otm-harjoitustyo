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
        kalle.getId();
        assertEquals(kalle.getNimi(), "Kalle");
        assertEquals(kalle.getRahaa(), 500);
        kalle.lisaaRahaa(200);
        assertEquals(kalle.getRahaa(), 700);
        assertTrue(kalle.annaPanos(500));
        assertFalse(kalle.annaPanos(500));
        assertEquals(kalle.toString(), "Kalle: 200");
    }
    
    @Test
    public void DealerTesti() {
        Dealer dealer = new Dealer();
        dealer.lisaa(new Kortti(3, "Hertta"));
        assertEquals(dealer.getKortit().get(0).getLuku(),3);
        assertEquals(dealer.getKortit().get(0).getMaa(),"Hertta");
        dealer.tyhjenna();
        assertTrue(dealer.getKortit().isEmpty());
        dealer.lisaa(new Kortti(1, "Hertta"));
        dealer.lisaa(new Kortti(5, "Hertta"));
        assertEquals(dealer.getMinSumma(),6);
        assertEquals(dealer.getSumma(),16);
        dealer.lisaa(new Kortti(6, "Hertta"));
        assertEquals(dealer.getSumma(),12);
        assertEquals(dealer.getMinSumma(),12);
        assertEquals(dealer.getKortit().size(),3);
        dealer.tyhjenna();
        dealer.nostaKortteja(new Pakka(1));
        assertTrue(dealer.getSumma() > 16);
    }
    
    @Test
    public void KasiTesti() {
        Pelaaja pelaaja = new Pelaaja("Kalle",100);
        Kasi kasi = new Kasi(new Kortti(3,"Hertta"),new Kortti(3,"Hertta"),30,pelaaja);
        Pakka pakka = new Pakka(1);
        assertEquals(kasi.getKortit().size(),2);
        assertEquals(kasi.getPanos(),30);
        assertEquals(kasi.getPelaaja().getRahaa(),100);
        kasi.setPanos(40);
        assertEquals(kasi.getPanos(),40);
        assertEquals(kasi.getPelaaja().getRahaa(),60);
        kasi.voitto();
        assertEquals(kasi.getPelaaja().getRahaa(),140);
        assertEquals(kasi.getSumma(),6);
        kasi.lisaa(pakka);
        assertEquals(kasi.getKortit().size(),3);
    }
}

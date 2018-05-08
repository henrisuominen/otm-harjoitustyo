/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.Database;
import Dao.PelaajaDao;
import domain.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        assertEquals(kortti.toPicture(),"3H");
        Kortti kortti2 = new Kortti(11, "Hertta");
        Kortti kortti3 = new Kortti(12, "Pata");
        Kortti kortti4 = new Kortti(13, "Risti");
        Kortti kortti5 = new Kortti(1, "Ruutu");
        assertEquals(kortti2.toPicture(),"JH");
        assertEquals(kortti3.toPicture(),"QS");
        assertEquals(kortti4.toPicture(),"KC");
        assertEquals(kortti5.toPicture(),"AD");
    }
    
    @Test
    public void pakkaTesti() {   
        Pakka pakka = new Pakka(2);
        assertEquals(pakka.getPakka().length, 104);
        assertEquals(pakka.isEmpty(), false);
        pakka.sekoita();
        assertEquals(pakka.paljonkoJaljella(), 100, 0);
        for (int i = 1; i < pakka.getPakka().length; i++) {
            pakka.getYlin();
        }
        assertEquals(pakka.isEmpty(), false);
        pakka.getYlin();
        assertEquals(pakka.paljonkoJaljella(), 0, 0);
        assertEquals(pakka.isEmpty(), true);
        assertNull(pakka.getYlin());
    }
    
    @Test
    public void pelaajaTesti() {
        Pelaaja kalle = new Pelaaja("Kalle", 500);
        Pelaaja kalle2 = new Pelaaja("Kalle", 1000100);
        Pelaaja pelle = new Pelaaja("Pelle", 500);
        assertEquals(kalle.getNimi(), "Kalle");
        assertEquals(kalle.getRahaa(), 500);
        kalle.lisaaRahaa(200);
        assertEquals(kalle.getRahaa(), 700);
        assertTrue(kalle.annaPanos(500));
        assertFalse(kalle.annaPanos(500));
        assertEquals(kalle.toString(), "Kalle: 200");
        assertNotEquals(kalle.getId(), pelle.getId());
        assertTrue(kalle.equals(kalle2));
        assertFalse(kalle.equals(pelle));
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
        dealer.tyhjenna();
        dealer.lisaa(new Kortti(11, "Hertta"));
        dealer.lisaa(new Kortti(13, "Hertta"));
        assertEquals(dealer.getMinSumma(),20);
    }
    
    @Test
    public void KasiTesti() {
        Pelaaja pelaaja = new Pelaaja("Kalle",100);
        Kasi kasi = new Kasi(new Kortti(3,"Pata"),new Kortti(3,"Hertta"),30,pelaaja);
        Pakka pakka = new Pakka(1);
        assertTrue(kasi.onSamat());
        assertEquals(kasi.getKortit().size(),2);
        assertEquals(kasi.getPanos(),30);
        assertEquals(kasi.getPelaaja().getRahaa(),100);
        kasi.setPanos(40);
        assertEquals(kasi.getPanos(),40);
        assertEquals(kasi.getPelaaja().getRahaa(),60);
        kasi.voitto();
        assertEquals(kasi.getPelaaja().getRahaa(),140);
        assertEquals(kasi.getSumma(),6);
        assertEquals(kasi.getMinSumma(),6);
        kasi.lisaa(pakka);
        assertEquals(kasi.getKortit().size(),3);
    }
    
    @Test
    public void KasiTesti2() {
        Pelaaja pelaaja = new Pelaaja("Kalle",100);
        Kasi kasi = new Kasi(new Kortti(4,"Pata"),new Kortti(3,"Hertta"),20,pelaaja);
        Pakka pakka = new Pakka(1);
        assertFalse(kasi.onSamat());
        Dealer dealer = new Dealer();
        dealer.lisaa(new Kortti(4,"Pata"));
        assertEquals(pelaaja.getRahaa(),100);
        assertTrue(kasi.voittojenJako(dealer));
        assertEquals(pelaaja.getRahaa(),140);
        kasi.voitto();
        assertEquals(pelaaja.getRahaa(),180);
        assertEquals(kasi.getKortit().size(),2);
        kasi.lisaa(pakka);
        assertEquals(kasi.getKortit().size(),3);
    }
    
    @Test
    public void KasiTesti3() {
        Pelaaja pelaaja = new Pelaaja("Kalle",100);
        Kasi kasi = new Kasi(new Kortti(1,"Pata"),new Kortti(11,"Hertta"),20,pelaaja);
        Kasi kasi2 = new Kasi(new Kortti(12,"Pata"),new Kortti(13,"Hertta"),20,pelaaja);
        Dealer dealer = new Dealer();
        Pakka pakka = new Pakka(2);
        dealer.lisaa(new Kortti(11,"Ruutu"));
        dealer.lisaa(new Kortti(12,"Ruutu"));
        dealer.lisaa(new Kortti(2,"Ruutu"));
        kasi.voitto();
        assertEquals(pelaaja.getRahaa(),150);
        assertEquals(kasi.getMinSumma(),11);
        assertEquals(kasi.getSumma(),21);
        assertEquals(kasi2.getMinSumma(),20);
        assertTrue(kasi.voittojenJako(dealer));
        kasi2.lisaa(pakka);
        kasi2.lisaa(pakka);
        assertFalse(kasi2.voittojenJako(dealer));
    }
    
    @Test
    public void huijauskoodiTest() {
        Pelaaja kalle = new Pelaaja("kalle",1000);
        kalle.cheatCode(113213);
        assertEquals(kalle.getRahaa(), 2000);
        kalle.cheatCode(123123);
        assertEquals(kalle.getRahaa(), 2000);
    }
    
    
}

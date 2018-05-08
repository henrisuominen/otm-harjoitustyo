## Käyttöohje

Lataa tiedosto [BlackJack.jar](https://github.com/henrisuominen/otm-harjoitustyo/releases)

#### Konfigurointi

Tiedosto olettaa kuvien olevan paikassa src/main/resources/kuvat. Kansion kuvat löytyvät dokumentoinnissa. Pelata voi ilman kuviakin mutta tällöin kuvien tilalla on tekstiä.
Tietokanta tekee itse itsensä, joten siitä ei tarvitse huolehtia.

#### Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

java -jar BlackJack.jar

#### Kirjautuminen

kirjoita nimesi ylös ja sen jälkeen paina lisää pelaaja. Pelaajan nimi tulee nyt esille näytölle. Jos nimeä ei löydy tietokannasta, ohjelma tekee sen automaattisesti ja antaa aluksi 1000 rahaa. Muuten sinulla on rahaa sen verran kuin sinulle viime kerrasta jäi. Tämän jälkeen voit valita pakkojen lukumäärän lisää/vähenää pakka -napeista. Tämän jälkeen peli alkaa aloita-napilla. Jos haluat enemmän rahaa niin voit kokeilla "Henri" käyttäjänimeä.

#### Pelaaminen

Alakulmasta löytyvät pelaamisessa tärkeät toiminnot. Näihin kuuluvat nosta, joka nostaa uuden kortin käteesi. Jää, joka antaa vuoron dealerille ja lopettaa kierroksen. Tuplaus, joka toimii vain, jos et ole nostanut yhtäkään korttia, joka kaksinkertaistaa panoksesi ja nostaa yhden ja vain yhden lisäkortin. 
Kun olet pelannut käden loppuun voit kertoo näyttö että voititko vai hävisitkö. Tällöin voit painaa sivussa olevia näppäimiä ja
esimerkiksi korottaa panostasi tai sekoittaa pakan. Tämän jälkeen voit aloittaa uuden käden painamalla uusi peli -nappia.

#### Talon säännöt

Jos pelaajalla ja dealerilla on sama summa, vaikka se molemmilla olisi 21, niin pelaaja häviää.
Jos pelaajan summa on voittaessa 21, niin saa hän panoksensa 3 kertaisena.
Pelaaja ei voi aloittaa uutta peliä jos sekoittamatonta pakkaa on jäljellä alle 10 %. Tällöin pakka tulee sekoittaa


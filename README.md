# OTM-Harjoitustyö

## BLACKJACK-Peli
Blackjack on jo vuosikymmeniä ollut yksi suosituimmista korttipeleistä. Kyseessä on peli, jossa nostetaan kortteja, kunnes saadaan itselle mieluisa käsi. Paras käsi koostuu korteista, joiden summa on 21. Kasinopelinä Blackjack on tyypillisesti ollut hyvin tyyris. Vaan eipä ole enää, sillä nyt voit pelata sitä omalla koneella! 

## Dokumentaatio

[vaatimusmäärittely](https://github.com/henrisuominen/otm-harjoitustyo/blob/master/dokumentointi/määrittelydokumentti)

[tyoaikakirjanpito](https://github.com/henrisuominen/otm-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito)

[arkkitehtuuri](https://github.com/henrisuominen/otm-harjoitustyo/dokumentointi/arkkitehtuuri.md)

[käyttöohje](https://github.com/henrisuominen/otm-harjoitustyo/blob/master/dokumentointi/Käyttöohje.md)

## Komentorivitoiminnot

###### Testaus

Testit suoritetaan komennolla

mvn test

Testikattavuusraportti luodaan komennolla

mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

###### Suoritettavan jarin generointi

Komento

mvn package

generoi hakemistoon target suoritettavan jar-tiedoston BlackJack.jar

###### JavaDoc

JavaDoc generoidaan komennolla

mvn javadoc:javadoc

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html

###### Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html

## Releaset

Nauti uusimmasta BlackJack pelistä!

[release](https://github.com/henrisuominen/otm-harjoitustyo/releases/tag/viikko5)

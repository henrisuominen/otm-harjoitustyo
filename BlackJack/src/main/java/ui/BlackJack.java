 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Dao.*;
import domain.Dealer;
import domain.Kasi;
import domain.Pelaaja;
import domain.Pakka;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author henrisuominen
 */


public class BlackJack extends Application {

    ArrayList<Pelaaja> pelaajatPelissa;
    Pakka peliPakka;
    Dealer dealer;
    boolean pelattu;
    boolean uusiPoyta;
    int panos;
    PelaajaDao pelaajaDao;
    Database database;

    public BlackJack() throws ClassNotFoundException {
        this.database = new Database("jdbc:sqlite:Pelaaja.db");
        this.pelaajaDao = new PelaajaDao(this.database);
    }

    public void dealerAloitus(HBox naytto) {
        dealer.lisaa(peliPakka.getYlin());
        naytaDealerKasi(naytto);
    }

    public void dealerLopetus(HBox naytto) {
        dealer.nostaKortteja(peliPakka);
        naytaDealerKasi(naytto);
        pelattu = true;
    }

    public void naytaDealerKasi(HBox naytto) {
        naytto.getChildren().clear();
        for (int i = 0; i < dealer.getKortit().size(); i++) {
            Label korttiKuva = new Label("");
            korttiKuva.setPrefSize(90, 130);
            try {
                FileInputStream inputstream = new FileInputStream(
                        Paths.get("src/main/resources/kuvat/" 
                        + dealer.getKortit().get(i).toPicture()
                        + ".jpg").toAbsolutePath().toString());
                Image image = new Image(inputstream);
                BackgroundImage bgImg = new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(BackgroundSize.AUTO, 
                                BackgroundSize.AUTO, false, false,
                                true, false));
                Background b = new Background(bgImg);
                korttiKuva.setBackground(b);
            } catch (FileNotFoundException e) {
                korttiKuva.setText(dealer.getKortit().get(i).toString());
            }
            naytto.getChildren().add(korttiKuva);
        }
        if (dealer.getKortit().size() == 1) {
            Label korttiKuva = new Label("");
            korttiKuva.setPrefSize(90, 130);
            try {
                FileInputStream inputstream = new FileInputStream(
                        Paths.get("src/main/resources/kuvat/Green_back.jpg").toAbsolutePath().toString());
                Image image = new Image(inputstream);
                BackgroundImage bgImg = new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(BackgroundSize.AUTO, 
                                BackgroundSize.AUTO, false, false,
                                true, false));
                Background b = new Background(bgImg);
                korttiKuva.setBackground(b);
            } catch (FileNotFoundException e) {
            }
            naytto.getChildren().add(korttiKuva);
        }
        naytto.getChildren().add(new Label(" Summa: " + dealer.getSumma()));
    }

    public void naytaKasi(Kasi kasi, HBox naytto) {
        naytto.getChildren().clear();
        for (int i = 0; i < kasi.getKortit().size(); i++) {
            Label korttiKuva = new Label("");
            korttiKuva.setPrefSize(90, 130);
            try {
                FileInputStream inputstream = new FileInputStream(
                        Paths.get("src/main/resources/kuvat/" 
                        + kasi.getKortit().get(i).toPicture()
                        + ".jpg").toAbsolutePath().toString());
                Image image = new Image(inputstream);
                BackgroundImage bgImg = new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(BackgroundSize.AUTO, 
                                BackgroundSize.AUTO, false, false,
                                true, false));
                Background b = new Background(bgImg);
                korttiKuva.setBackground(b);
            } catch (FileNotFoundException e) {
                korttiKuva.setText(kasi.getKortit().get(i).toString());
            }
            naytto.getChildren().add(korttiKuva);
        }
        naytto.getChildren().add(new Label(" Summa: " + kasi.getSumma()));
    }

    public void pelinaytto1(Stage primaryStage) {
        
        pelattu = false;
        uusiPoyta = true;
        dealer.tyhjenna();
        pelattu = false;
        try {
            this.pelaajaDao.saveOrUpdate(this.pelaajatPelissa.get(0));
        } catch (SQLException ex) {
        }

        BorderPane root2 = new BorderPane();
        
        //kaunis kuva taakse
        FileInputStream inputstream;
        try {
            inputstream = new FileInputStream(
                    Paths.get("src/main/resources/kuvat/aces.jpg").toAbsolutePath().toString());
            Image image = new Image(inputstream);
                BackgroundImage bgImg = new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(BackgroundSize.AUTO, 
                                BackgroundSize.AUTO, false, false,
                                true, false));
                Background b = new Background(bgImg);
                root2.setBackground(b);
        } catch (FileNotFoundException ex) {
        }

        //dealer
        HBox kortitDealer = new HBox();
        root2.setTop(kortitDealer);
        dealerAloitus(kortitDealer);

        //pelaaja 1
        VBox pelaajaValinnat1 = new VBox();
        HBox kortit1 = new HBox();

        Button nosta1 = new Button("nosta");
        Button jaa1 = new Button("jää");
        Button split1 = new Button("split");
        Button tuplaus1 = new Button("tuplaus");

        pelaajaValinnat1.getChildren().add(kortit1);
        pelaajaValinnat1.getChildren().add(nosta1);
        pelaajaValinnat1.getChildren().add(jaa1);
        pelaajaValinnat1.getChildren().add(tuplaus1);

        root2.setBottom(pelaajaValinnat1);
        Label voittoNaytto = new Label("");
        voittoNaytto.setFont(new Font(40));
        root2.setCenter(voittoNaytto);

        //erikoisnappulat
        VBox erikoisNappulat = new VBox();
        Button uusi = new Button("Uusi peli");
        Button sekoita = new Button("Sekoita Pakka");
        Button panosLisaa = new Button("lisaa panosta");
        Button panosVahenna = new Button("vähennä panosta");
        Label panosNaytto = new Label("panos: " + this.panos);
        Label pakkaaJaljella = new Label("pakkaa jäljellä: " + this.peliPakka.paljonkoJaljella() + "%");
        Label rahaNaytto = new Label("rahaa: " + this.pelaajatPelissa.get(0).getRahaa());
        erikoisNappulat.getChildren().add(uusi);
        erikoisNappulat.getChildren().add(sekoita);
        erikoisNappulat.getChildren().add(pakkaaJaljella);
        erikoisNappulat.getChildren().add(rahaNaytto);
        erikoisNappulat.getChildren().add(panosLisaa);
        erikoisNappulat.getChildren().add(panosVahenna);
        erikoisNappulat.getChildren().add(panosNaytto);

        root2.setRight(erikoisNappulat);

        Scene scene = new Scene(root2, 900, 750);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

        VBox a1 = (VBox) root2.getChildren().get(1);
        HBox b1 = (HBox) a1.getChildren().get(0);
        Kasi pelaajan1Kasi = new Kasi(peliPakka.getYlin(), peliPakka.getYlin(),
                panos, pelaajatPelissa.get(0));
        naytaKasi(pelaajan1Kasi, b1);
        
        nosta1.setOnAction((event) -> {
            if (!pelattu) {
                uusiPoyta = false;
                pelaajan1Kasi.lisaa(peliPakka);
                naytaKasi(pelaajan1Kasi, b1);
                if (pelaajan1Kasi.getSumma() > 20) {
                    dealerLopetus(kortitDealer);
                    if (pelaajan1Kasi.voittojenJako(dealer)) {
                        voittoNaytto.setText("VOITIT");
                    } else {
                        voittoNaytto.setText("HÄVISIT");
                    }  
                }
            }
        });
        jaa1.setOnAction((event) -> {
            uusiPoyta = false;
            dealerLopetus(kortitDealer);
            if (pelaajan1Kasi.voittojenJako(dealer)) {
                voittoNaytto.setText("VOITIT");
                rahaNaytto.setText("rahaa: " + this.pelaajatPelissa.get(0).getRahaa());
            } else {
                voittoNaytto.setText("HÄVISIT");
            }
        });
        tuplaus1.setOnAction((event) -> {
            if (uusiPoyta&&!pelattu) {
                pelaajan1Kasi.lisaa(peliPakka);
                naytaKasi(pelaajan1Kasi, b1);
                this.panos = 2*this.panos;
                pelaajan1Kasi.setPanos(this.panos);
                dealerLopetus(kortitDealer);
                if (pelaajan1Kasi.voittojenJako(dealer)) {
                    voittoNaytto.setText("VOITIT");
                } else {
                    voittoNaytto.setText("HÄVISIT");
                }  
                panosNaytto.setText("panos: " + this.panos);
            }
        });
        uusi.setOnAction((event) -> {
            this.pelaajatPelissa.get(0).cheatCode(0);
            if (this.peliPakka.paljonkoJaljella() >= 10) {
                if (pelaajatPelissa.get(0).annaPanos(panos)) {
                    pelinaytto1(primaryStage);
                } else {
                voittoNaytto.setText("SINULLA EI OLE RAHAA!");
                }
            } else {
                voittoNaytto.setText("SEKOITA PAKKA ENNEN PELIÄ");
            }
        });
        sekoita.setOnAction((event) -> {
            if (this.pelattu == true) {
                this.peliPakka.sekoita();
                pakkaaJaljella.setText("pakkaa jäljellä: " + this.peliPakka.paljonkoJaljella() + "%");
            } else {
                this.pelaajatPelissa.get(0).cheatCode(1);
            }
        });
        panosVahenna.setOnAction((event) -> {
            if (this.pelattu == true && this.panos > 10) {
                panos -= 10;
                panosNaytto.setText("panos: " + this.panos);
            } else {
                this.pelaajatPelissa.get(0).cheatCode(2);
            }
        });
        panosLisaa.setOnAction((event) -> {
            if (this.pelattu == true) {
                panos += 10;
                panosNaytto.setText("panos: " + this.panos);
            } else {
                this.pelaajatPelissa.get(0).cheatCode(3);
            }
        });
    }

    public void alkunaytto(Stage primaryStage) {
        panos = 20;
        Button uusiPelaaja = new Button();
        Button lisaaPakka = new Button("Lisää pakka");
        Button vahennaPakka = new Button("Vähennä pakka");
        Label tekstiPakoille = new Label("Pakkoja: ");
        Label pakkoja = new Label("1");
        Button aloita = new Button();

        VBox pelaajat = new VBox();
        BorderPane root = new BorderPane();

        uusiPelaaja.setText("lisää pelaaja");
        aloita.setText("aloita");
        TextField tekstikentta = new TextField();
        pelaajat.getChildren().add(tekstikentta);

        uusiPelaaja.setOnAction((event) -> {
            if (tekstikentta.getText().length() != 0 && pelaajatPelissa.size() < 1) {
                try {
                    Pelaaja pelaaja = new Pelaaja(tekstikentta.getText(),1000);
                    if (pelaajaDao.findAll().contains(pelaaja)) {
                        pelaajat.getChildren().add(new Label(tekstikentta.getText()));
                        Pelaaja pelaajaPelissa = pelaajaDao.findByNameOne(tekstikentta.getText());
                        pelaajatPelissa.add(pelaajaPelissa);
                    } else {
                        pelaajat.getChildren().add(new Label(tekstikentta.getText()));
                        pelaajaDao.saveOrUpdate(pelaaja);
                        pelaajatPelissa.add(pelaaja);
                    }
                } catch (SQLException ex) {

                }
            }
        });
        
        lisaaPakka.setOnAction((event) -> {
            int p = Integer.parseInt(pakkoja.getText()) + 1;
            pakkoja.setText(String.valueOf(p));
            peliPakka = new Pakka(p);
        });
        
        vahennaPakka.setOnAction((event) -> {
            int p = Integer.parseInt(pakkoja.getText()) - 1;
            if (p > 0) {
                pakkoja.setText(String.valueOf(p));
                peliPakka = new Pakka(p);
            }
        });

        aloita.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (pelaajatPelissa.size() == 1) {
                    pelinaytto1(primaryStage);
                }
            }
        });

        VBox alaNappulat = new VBox();
        alaNappulat.getChildren().add(uusiPelaaja);
        alaNappulat.getChildren().add(lisaaPakka);
        alaNappulat.getChildren().add(vahennaPakka);
        HBox pakkaTeksti = new HBox();
        pakkaTeksti.getChildren().add(tekstiPakoille);
        pakkaTeksti.getChildren().add(pakkoja);
        alaNappulat.getChildren().add(pakkaTeksti);
        root.setBottom(alaNappulat);
        root.setRight(aloita);
        root.setCenter(pelaajat);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("BlackJack");
        primaryStage.show();
    }

    public void start(Stage primaryStage) {
        pelaajatPelissa = new ArrayList<>();
        peliPakka = new Pakka(1);
        dealer = new Dealer();
        alkunaytto(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

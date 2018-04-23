/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import domain.Dealer;
import domain.Kasi;
import domain.Pelaaja;
import domain.Pakka;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author henrisuominen
 */
public class BlackJack extends Application {
    ArrayList<Pelaaja> pelaajatPelissa;
    Pakka peliPakka;
    Dealer dealer;
    
    public void nosta(Kasi kasi, HBox naytto) {
        if (!peliPakka.isEmpty() && kasi.getMinSumma() < 21) {
            kasi.lisaa(peliPakka.getYlin());
            naytaKasi(kasi, naytto);
        }
    }
    
    public void dealerAloitus(HBox naytto) {
        dealer.lisaa(peliPakka.getYlin());
        naytaKasi(naytto);
    }
    
    public void dealerLopetus(HBox naytto) {
        dealer.nostaKortteja(peliPakka);
        naytaKasi(naytto);
    }
    
    public void naytaKasi(HBox naytto) {
        naytto.getChildren().clear();
        for (int i = 0; i < dealer.getKortit().size(); i++) {
            naytto.getChildren().add(new Label(dealer.getKortit().get(i).toString()));
        }
        naytto.getChildren().add(new Label(" Summa: " + dealer.getSumma()));
    }
    
    public void naytaKasi(Kasi kasi, HBox naytto) {
        naytto.getChildren().clear();
        for (int i = 0; i < kasi.getKortit().size(); i++) {
            Image image = new Image("resources/kuvat/2C.jpg");

            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            naytto.getChildren().add(iv1);
            //naytto.getChildren().add(new Label(kasi.getKortit().get(i).toString()));
        }
        naytto.getChildren().add(new Label(" Summa: " + kasi.getSumma()));
    }
    
    public void pelinaytto1(Stage primaryStage) {
        dealer.tyhjenna();
        
        BorderPane root2 = new BorderPane();
        
        //dealer
        HBox kortitDealer = new HBox();
        root2.setCenter(kortitDealer);
        dealerAloitus(kortitDealer);

        //pelaaja 1
        VBox pelaajaValinnat1 = new VBox();
        HBox kortit1 = new HBox();
        
        Button nosta1 = new Button("nosta");
        Button jaa1 = new Button("jaa");
        Button split1 = new Button("split");
        
        pelaajaValinnat1.getChildren().add(kortit1);
        pelaajaValinnat1.getChildren().add(nosta1);
        pelaajaValinnat1.getChildren().add(jaa1);
        pelaajaValinnat1.getChildren().add(split1);
        
        root2.setBottom(pelaajaValinnat1);
        
        //erikoisnappulat
        VBox erikoisNappulat = new VBox();
        Button uusi = new Button("Uusi peli");
        Button sekoita = new Button("Sekoita Pakka");
        erikoisNappulat.getChildren().add(uusi);
        erikoisNappulat.getChildren().add(sekoita);
        
        root2.setRight(erikoisNappulat);

        Scene scene = new Scene(root2, 900, 750);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

        VBox a1 = (VBox) root2.getChildren().get(1);
        HBox b1 = (HBox) a1.getChildren().get(0);
        Kasi pelaajan1Kasi = new Kasi(peliPakka.getYlin(), peliPakka.getYlin(), 50, pelaajatPelissa.get(0));
        naytaKasi(pelaajan1Kasi, b1);

        nosta1.setOnAction((event)-> {
            nosta(pelaajan1Kasi, b1);
        });
        jaa1.setOnAction((event)-> {
            dealerLopetus(kortitDealer);
        });
        uusi.setOnAction((event)-> {
            pelinaytto1(primaryStage);
        });
        sekoita.setOnAction((event)-> {
            this.peliPakka.sekoita();
        });
    }
    
    public void alkunaytto(Stage primaryStage) {
        Button uusiPelaaja = new Button();
        Button aloita = new Button();
        
        VBox pelaajat = new VBox();
        BorderPane root = new BorderPane();
        
        uusiPelaaja.setText("lisää pelaaja");
        aloita.setText("aloita");
        TextField tekstikentta = new TextField();
        pelaajat.getChildren().add(tekstikentta);
        
        uusiPelaaja.setOnAction((event)-> {
            if (tekstikentta.getText().length() != 0 && pelaajatPelissa.size() < 4) {
                pelaajat.getChildren().add(new Label(tekstikentta.getText()));
                pelaajatPelissa.add(new Pelaaja(tekstikentta.getText(), 500));
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
        
        
        root.setBottom(uusiPelaaja);
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

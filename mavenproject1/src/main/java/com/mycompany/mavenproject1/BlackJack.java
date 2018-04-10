/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author henrisuominen
 */
public class BlackJack extends Application {
    
    /**
     *
     * @param primaryStage
     */
    public void start(Stage primaryStage) {

        ArrayList<Pelaaja> pelaajatPelissa = new ArrayList<>();
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
                pelaajatPelissa.add(new Pelaaja(tekstikentta.getText(),500));
            }
        });
        
        
        BorderPane root2 = new BorderPane();
        
        //dealer
        HBox kortitDealer = new HBox();
        root2.setTop(kortitDealer);

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
        
        aloita.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!pelaajatPelissa.isEmpty()) {
                    Scene scene = new Scene(root2, 900, 750);
                    primaryStage.setScene(scene);
                    primaryStage.centerOnScreen();
                    
                    Pakka pakka = new Pakka(1);
                    Pelaaja[] pelaajatPeliin = new Pelaaja[pelaajatPelissa.size()];
                    for (int i = 0; i < pelaajatPelissa.size(); i++) {
                        pelaajatPeliin[i] = pelaajatPelissa.get(i);
                    }
                    Peli peli = new Peli(pakka,pelaajatPeliin);
                    VBox a1 = (VBox) root2.getChildren().get(1);
                    HBox b1 = (HBox) a1.getChildren().get(0);
                    b1.getChildren().add(new Label(pakka.getYlin().toString()));
                    b1.getChildren().add(new Label(pakka.getYlin().toString()));
                    
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

    public static void main(String[] args) {
        launch(args);
    }
}

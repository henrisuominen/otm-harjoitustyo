 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.Dealer;
import domain.Kasi;
import domain.Pelaaja;
import domain.Pakka;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    boolean pelattu;
    int panos;

    public void nosta(Kasi kasi, HBox naytto) {
        if (!peliPakka.isEmpty() && kasi.getMinSumma() < 21) {
            kasi.lisaa(peliPakka.getYlin());
            naytaKasi(kasi, naytto);
        }
    }
    
    public void voittoScreen(Kasi kasi, Label voittoNaytto) {
        if (kasi.getSumma() > dealer.getSumma() && kasi.getSumma() < 22) {
            voittoNaytto.setText("VOITIT");
            pelaajatPelissa.get(0).lisaaRahaa(2*panos);
        } else if (dealer.getSumma() > 21 && kasi.getSumma() < 22) {
            voittoNaytto.setText("VOITIT");
            pelaajatPelissa.get(0).lisaaRahaa(2*panos);
        } else {
            voittoNaytto.setText("HÄVISIT");
        }
    }

    public void dealerAloitus(HBox naytto) {
        dealer.lisaa(peliPakka.getYlin());
        naytaKasi(naytto);
    }

    public void dealerLopetus(HBox naytto) {
        dealer.nostaKortteja(peliPakka);
        naytaKasi(naytto);
        pelattu = true;
    }

    public void naytaKasi(HBox naytto) {
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
                System.out.println("ERROR: " + e);
                korttiKuva.setText(dealer.getKortit().get(i).toString());
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
                System.out.println("ERROR: " + e);
                korttiKuva.setText(kasi.getKortit().get(i).toString());
            }
            naytto.getChildren().add(korttiKuva);
        }
        naytto.getChildren().add(new Label(" Summa: " + kasi.getSumma()));
    }

    public void pelinaytto1(Stage primaryStage) {
        pelattu = false;
        dealer.tyhjenna();
        pelattu = false;
        pelaajatPelissa.get(0).annaPanos(panos);

        BorderPane root2 = new BorderPane();

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
        pelaajaValinnat1.getChildren().add(split1);

        root2.setBottom(pelaajaValinnat1);
        Label voittoNaytto = new Label("");
        root2.setCenter(voittoNaytto);

        //erikoisnappulat
        VBox erikoisNappulat = new VBox();
        Button uusi = new Button("Uusi peli");
        Button sekoita = new Button("Sekoita Pakka");
        Button panosLisaa = new Button("lisaa panosta");
        Button panosVahenna = new Button("vähennä panosta");
        Label panosNaytto = new Label("panos: " + this.panos);
        Label rahaNaytto = new Label("rahaa: " + this.pelaajatPelissa.get(0).getRahaa());
        erikoisNappulat.getChildren().add(uusi);
        erikoisNappulat.getChildren().add(sekoita);
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
                nosta(pelaajan1Kasi, b1);
            }
        });
        jaa1.setOnAction((event) -> {
            dealerLopetus(kortitDealer);
            voittoScreen(pelaajan1Kasi,voittoNaytto);
        });
        tuplaus1.setOnAction((event) -> {

        });
        uusi.setOnAction((event) -> {
            pelinaytto1(primaryStage);
        });
        sekoita.setOnAction((event) -> {
            this.peliPakka.sekoita();
        });
        panosVahenna.setOnAction((event) -> {
            if(this.pelattu == true){
                panos -= 10;
            }
        });
        panosLisaa.setOnAction((event) -> {
            if(this.pelattu == true){
                panos += 10;
            }
        });
    }

    public void alkunaytto(Stage primaryStage) {
        panos = 20;
        Button uusiPelaaja = new Button();
        Button aloita = new Button();

        VBox pelaajat = new VBox();
        BorderPane root = new BorderPane();

        uusiPelaaja.setText("lisää pelaaja");
        aloita.setText("aloita");
        TextField tekstikentta = new TextField();
        pelaajat.getChildren().add(tekstikentta);

        uusiPelaaja.setOnAction((event) -> {
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
        peliPakka = new Pakka(3);
        dealer = new Dealer();
        alkunaytto(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

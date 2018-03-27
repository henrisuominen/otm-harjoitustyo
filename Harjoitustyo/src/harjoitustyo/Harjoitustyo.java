/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author henrisuominen
 */
public class Harjoitustyo extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        Button lisaaPelaaja = new Button();
        lisaaPelaaja .setText("Say 'Hello World'");
        lisaaPelaaja .setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {


            }
        });
        lisaaPelaaja .setLayoutX(20);
        lisaaPelaaja .setLayoutY(20);
        
        
        
        
        Pane root = new Pane();
        Label olenDealer = new Label("Dealer");
        Label kortitDealer = new Label("palleromallero");
        

        
        VBox dealer = new VBox();
        dealer.setLayoutX(250);
        dealer.setLayoutY(50);
        dealer.getChildren().add(olenDealer);
        dealer.getChildren().add(kortitDealer);
        
        
        
        root.getChildren().add(lisaaPelaaja);
        root.getChildren().add(dealer);
 
        Scene scene = new Scene(root, 600, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("hello");
    }
    
}

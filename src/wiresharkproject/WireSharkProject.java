/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jnetpcap.*;

/**
 *
 * @author Kero & kk & Mina & Osama
 */
public class WireSharkProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        System.load("C:\\Users\\Kord\\Documents\\GitHub\\WireSharkProject\\jnetpcap 1.4\\jnetpcap-1.4.r1300\\jnetpcap.dll");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
                System.out.println(System.getProperty("javafx.version"));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

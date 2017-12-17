/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import APIs.ProjectController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            APIs.Constants.pc = new ProjectController();
            
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLDocument.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

}

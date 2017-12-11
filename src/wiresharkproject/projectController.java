/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;   

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
public class projectController extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();


    }
    
    public static List getNics()
    {
        List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs         
        StringBuilder errbuf = new StringBuilder();     // For any error msgs          
        int r = Pcap.findAllDevs(alldevs, errbuf);
        
    return alldevs;
    }


    
}

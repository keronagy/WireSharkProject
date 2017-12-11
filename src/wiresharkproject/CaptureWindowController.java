/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

/**
 * FXML Controller class
 *
 * @author Kero
 */
public class CaptureWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
        void capturePacketsDummy(NetworkInterface [] ni,int InterfaceIndex){
        try {
            System.out.println("Beginning");
            JpcapCaptor captor=JpcapCaptor.openDevice(ni[InterfaceIndex], 65535, false, 20);
            captor.processPacket(10,new PacketReader());
            captor.close();
            System.out.println("Ending");
        } catch (IOException ex) {
            System.out.println("EXCEPTION");
        }
    }
   public void StartBtn()
    {
        
        PacketReader pr = new PacketReader();
        pr.StartCapturing(0);

    }
    
}

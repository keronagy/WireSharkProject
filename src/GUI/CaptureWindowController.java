
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import APIs.Constants;
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
//import jpcap.JpcapCaptor;
//import jpcap.NetworkInterface;

/**
 * FXML Controller class
 *
 * @author Kero
 */
public class CaptureWindowController implements Initializable {
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    //Assuming that this is the Start Button Action method
    public void StartBtn()
    {   
        //DO NOT WRITE ANYTHING NEW HERE
        Constants.pc.startCapturing();
        
    }
    //Assuming that this is the Stop Button Action method
    public void EndBtn()
    {
        //DO NOT WRITE ANYTHING NEW HERE
        Constants.pc.stopCapturing();
    }
    
}

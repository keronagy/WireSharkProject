/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;   

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
public class ProjectController extends Application {
    
    private static PacketCapturer pc;
    private static int NetworkInterfaceIndex; //it's static as when opening the capture window, it will capture only from a pre-specified interface
    private static ArrayList<PcapIf> NetworkDevicesList;
    
    
    public static void startCapturing(){
        pc.StartCapture(NetworkInterfaceIndex);
    }
    
    public static void stopCapturing(){
        pc.stopCapturing();
    }
    
    public static void setNetworkInterfaceIndex(int Index){
       NetworkInterfaceIndex=Index;
    }
    
    public static ArrayList<PcapIf> getNetworkInterfacesList(){
        return NetworkDevicesList;
    }
    
    public static int getNetworkInterfaceIndex(){
        return NetworkInterfaceIndex;
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
//        pc = new PacketCuptorer();
try{
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
}catch(Exception e){
    System.out.println(e.getCause());
}
    
    }
    
    
    public static List formAndReturnNetworkInterfacesList()
    {
        NetworkDevicesList = new ArrayList<PcapIf>(); // Will be filled with NICs   
        StringBuilder ErrorBuffer = new StringBuilder();     // For any error msgs          
       
        int ErrorFlag = Pcap.findAllDevs(NetworkDevicesList, ErrorBuffer);
        
        if(ErrorFlag!=Pcap.OK){
            System.out.println("Error in Network Device List "+ErrorBuffer.toString());
        }
        
        return NetworkDevicesList;
    }
}

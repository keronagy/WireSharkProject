    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jnetpcap.*;
//import jpcap.*;
//import jpcap.packet.Packet;
//import jpcap.packet.TCPPacket;
//import jpcap.packet.UDPPacket;
import APIs.ProjectController;
import APIs.ProjectController;
import GUI.PcapIfRow;
/**
 *
 * @author Kero
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<PcapIf> tableView;
    @FXML
    private TableColumn<PcapIf, String> AdapterName;
    @FXML
    private TableColumn<PcapIf, String> AdapterIP;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AdapterName.setCellValueFactory(new PropertyValueFactory<PcapIf, String>("AdapterName"));
        AdapterIP.setCellValueFactory(new PropertyValueFactory<PcapIf, String>("AdapterIP"));

        try {
            tableView.setItems(ShowInterfaces());

            tableView.setItems(ShowInterfaces());
            
            //capturePacketsDummy(nic, 0);
            
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private ObservableList ShowInterfaces()  {
        ObservableList<PcapIfRow> ni = FXCollections.observableArrayList();   
        List<PcapIf> alldevs = APIs.Constants.pc.getNics();
        //SOME DOCUMENTATION THAT MAY HELP FOR OUR GUI GUY 
        //alldevs.get(INDEX).getName()--> RETURNS A STRING HOLDING THE NAME OF THE NETWORK INTERFACE
        //alldevs.get(INDEX).getDescription()--> RETURNS A STRING HOLDING THE DESCRIPTION OF THE NETWORK INTERFACE
        
        String ip = "";
        for(int i=0; i<alldevs.size();i++){
            for (int j = 0; j < alldevs.get(i).getAddresses().size(); j++) {
            ip = alldevs.get(i).getAddresses().get(0).getAddr().toString().replace("[INET4:", "").replace("]", "");
        
            }
            ni.add(new PcapIfRow(alldevs.get(i).getDescription(), ip));
        }

      return ni;

 }
    
    public void CaptureScreenBtn(ActionEvent event) throws IOException
    {
        try{
        Parent capture = FXMLLoader.load(getClass().getResource("/GUI/CaptureWindow.fxml"));
        Scene CaptureWindow = new Scene(capture);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(CaptureWindow);
        window.show();
        }
        catch(Exception e)
        {
            System.out.println(e.getCause());
        }

    }


}

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;

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
            tableView.setItems(getInterfaces());

            tableView.setItems(getInterfaces());
            //capturePacketsDummy(nic, 0);
            
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ObservableList getInterfaces()  {
        String address = "";

        ObservableList<PcapIfRow> ni = FXCollections.observableArrayList();
       
        List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs         
        StringBuilder errbuf = new StringBuilder();     // For any error msgs  

        
        int r = Pcap.findAllDevs(alldevs, errbuf);

    

        for(int i=0; i<alldevs.size();i++)
            ni.add(new PcapIfRow(alldevs.get(i).getDescription(), ".")); //replace the dot with the ip
 
        

      return ni;

    }
        
    public void CaptureScreenBtn(ActionEvent event) throws IOException
    {
        Parent capture = FXMLLoader.load(getClass().getResource("CaptureWindow.fxml"));
        Scene CaptureWindow = new Scene(capture);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(CaptureWindow);
        window.show();

    }
//    void capturePacketsDummy(NetworkInterface [] ni,int InterfaceIndex){
//        try {
//            System.out.println("Beginning");
//            JpcapCaptor captor=JpcapCaptor.openDevice(ni[InterfaceIndex], 65535, false, 20);
//            captor.processPacket(10,new PacketReader());
//            captor.close();
//            System.out.println("Ending");
//        } catch (IOException ex) {
//            System.out.println("EXCEPTION");
//        }
//    }

}

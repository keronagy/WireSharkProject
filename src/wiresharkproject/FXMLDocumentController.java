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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.Enumeration;
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
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;


/**
 *
 * @author Kero
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<NetworkInterfaceTable> tableView;
    @FXML
    private TableColumn<NetworkInterfaceTable, String> AdapterName;
    @FXML
    private TableColumn<NetworkInterfaceTable, String> AdapterIP;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AdapterName.setCellValueFactory(new PropertyValueFactory<NetworkInterfaceTable, String>("AdapterName"));
        AdapterIP.setCellValueFactory(new PropertyValueFactory<NetworkInterfaceTable, String>("AdapterIP"));

        try {
            tableView.setItems(showInterfaces());
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ObservableList showInterfaces()  {
        ObservableList<NetworkInterfaceTable> ni = FXCollections.observableArrayList();
         NetworkInterface[] nic = JpcapCaptor.getDeviceList();
         for (int i = 0; i < nic.length; i++) {
             
            ni.add(new NetworkInterfaceTable(nic[i].description, nic[i].addresses[1].address.getHostAddress()));
        }
//        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
//        String ip = null;
//        for (NetworkInterface networkinterface : Collections.list(nets)) {
//            if (networkinterface.isUp()) {
//                Enumeration<InetAddress> Addresses = networkinterface.getInetAddresses();
//                for (InetAddress Address : Collections.list(Addresses)) {
//                    ip = Address.getHostAddress();
//                    break;
//                }
//                ni.add(new NetworkInterfaceTable(networkinterface.getName(), ip));
//            }
//        }
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

}

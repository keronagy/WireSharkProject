/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        } catch (SocketException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ObservableList showInterfaces() throws SocketException {
        ObservableList<NetworkInterfaceTable> ni = FXCollections.observableArrayList();
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        String ip = null;
        for (NetworkInterface networkinterface : Collections.list(nets)) {
            if (networkinterface.isUp()) {
                Enumeration<InetAddress> Addresses = networkinterface.getInetAddresses();
                for (InetAddress Address : Collections.list(Addresses)) {
                    ip = Address.getHostAddress();
                    break;
                }
                ni.add(new NetworkInterfaceTable(networkinterface.getName(), ip));
            }
        }
        return ni;
    }

}

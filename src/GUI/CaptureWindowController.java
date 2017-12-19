
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import APIs.Constants;
import APIs.PacketReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.jnetpcap.JBufferHandler;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
//import jpcap.JpcapCaptor;
//import jpcap.NetworkInterface;

/**
 * FXML Controller class
 *
 * @author Kero
 */
public class CaptureWindowController implements Initializable {

    @FXML
    private Button SaveBtn;
    @FXML
    private Button LoadBtn;
    @FXML
    private Button StartBtn;
    @FXML
    private Button StopBtn;
    @FXML
    private TextArea HEXText;
//    @FXML
//    private TextArea InfoText;
    public static ObservableList<RowPacket> Packets;
    //FilteredList<RowPacket> filteredList = new FilteredList<>(Packets);

    @FXML
    private Accordion accordion;
    
    @FXML
    private TitledPane frame;
    @FXML
    private TitledPane ethernet;
    @FXML
    private TitledPane ip;
    @FXML
    private TitledPane protocol;
    @FXML
    private TextArea frameText;
    @FXML
    private TextArea ethernetText;
    @FXML
    private TextArea ipText;
    @FXML
    private TextArea protocolText;
    
    @FXML
    private TextField filterField;

    @FXML
    private TableView<RowPacket> PacketsTable;
    @FXML
    private TableColumn<RowPacket, String> No;
    @FXML
    private TableColumn<RowPacket, String> Time;
    @FXML
    private TableColumn<RowPacket, String> Source;
    @FXML
    private TableColumn<RowPacket, String> Destination;
    @FXML
    private TableColumn<RowPacket, String> Protocol;
    @FXML
    private TableColumn<RowPacket, String> Length;
    @FXML
    private TableColumn<RowPacket, String> Info;
    

    
    public void SaveBtnClicked(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("pccp", "*.pcap"));
        File Filename = fc.showSaveDialog(null);

        if (Filename != null) {
            System.out.println(Filename.getAbsolutePath());
            StringBuilder errbuf = new StringBuilder();
            String fname = Filename.getAbsolutePath();

            Pcap pcap = Pcap.openOffline(fname, errbuf);

            String ofile = "tmp-capture-file.cap";
            PcapDumper dumper = pcap.dumpOpen(ofile); // output file  

            JBufferHandler<PcapDumper> dumpHandler = new JBufferHandler<PcapDumper>() {

                public void nextPacket(PcapHeader header, JBuffer buffer, PcapDumper dumper) {

                    dumper.dump(header, buffer);
                }
            };

            pcap.loop(10, dumpHandler, dumper);

            File file = new File(ofile);
            System.out.printf("%s file has %d bytes in it!\n", ofile, file.length());

            dumper.close(); // Won't be able to delete without explicit close  
            pcap.close();
        }

    }

    public void LoadBtbClicked(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("pccp", "*.pcap"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            String fname = selectedFile.getAbsolutePath();
            StringBuilder errbuf = new StringBuilder();

            Pcap pcap = Pcap.openOffline(fname, errbuf);
            Packets.clear();
            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
                @Override
                public void nextPacket(PcapPacket packet, String user) {

                    PacketReader pr = new PacketReader();
                    pr.ReadPacket(packet);
                    String[] row = {"Time", "source", "Destination", "Protocol", "Length", "Info","Hex view","MoreDetail"};
                    RowPacket rp = new RowPacket(row);

                    Packets.add(rp);
                }
            };

            if (pcap == null) {
                System.err.printf("Error while opening device for capture: "
                        + errbuf.toString());

            }
            try {
                pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, "");
            } catch (Exception exp) {
                  exp.printStackTrace();
            }
            
            //PacketsTable.setItems(Packets);

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        No.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("No"));
        Time.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Time"));
        Source.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Source"));
        Destination.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Destination"));
        Protocol.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Protocol"));
        Length.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Length"));
        Info.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Info"));
        StopBtn.setDisable(true);
        Packets = FXCollections.observableArrayList();
        
        PacketsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
            //Check whether item is selected and set value of selected item to Label
            if(PacketsTable.getSelectionModel().getSelectedItem() != null) 
            {    
               ShowValues(PacketsTable.getSelectionModel().getSelectedItem()) ;
             }
             }
         });
        
            FilteredList<RowPacket> filteredData = new FilteredList<>(Packets, p -> true);
            
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(row -> {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if (row.getNo().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            }
            if (row.getTime().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            } else if (row.getSource().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            
            else if (row.getDestination().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            else if (row.getProtocol().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            else if (row.getLength().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            else if (row.getInfo().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            return false; // Does not match.
        });
    });
            SortedList<RowPacket> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(PacketsTable.comparatorProperty());
            PacketsTable.setItems(sortedData);
            
    }

    //Assuming that this is the Start Button Action method
    public void StartBtn() {
        //DO NOT WRITE ANYTHING NEW HERE

        StartBtn.setDisable(true);
        StopBtn.setDisable(false);
        SaveBtn.setDisable(true);
        Constants.pc.startCapturing();
        //PacketsTable.setItems(filteredList);
            

        //Packets.add(Constants.pc.pcapt.getLastPacket());

        //PacketsTable.setItems(Packets);
    }

    //Assuming that this is the Stop Button Action method
    public void EndBtn() {
        //DO NOT WRITE ANYTHING NEW HERE
        Constants.pc.stopCapturing();
        StartBtn.setDisable(false);
        StopBtn.setDisable(true);
        SaveBtn.setDisable(false);
        
    }

    public void bonusTest() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("txt files", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
        Constants.pc.filename = selectedFile.getAbsolutePath();
        }
        
        
        Constants.pc.loadData(Constants.pc.filename);
        Constants.pc.km = new APIs.Kmeans_Bonus(Constants.pc.loadData(Constants.pc.filename), Constants.k);
        Constants.pc.km.start();
    }
    
    public void getSlectedRowMouseClicked()
    {
        ShowValues(PacketsTable.getSelectionModel().getSelectedItem());
    }
    
    
    
    public void ShowValues(RowPacket r)
    {
        frameText.setText("");
        ethernetText.setText("");
        ipText.setText("");
        protocolText.setText("");
        protocol.setVisible(false);
        frame.setVisible(false);
        ethernet.setVisible(false);
        ip.setVisible(false);
        
        
        HEXText.setText(r.getHexView());
        //InfoText.setText(r.getMoreDetail());
        if(!(r.getFrameCordion().equals("null")))
        {
            frame.setVisible(true);
            frameText.setText(r.getFrameCordion());
        }
       
        if(!(r.getEthernetCordion().equals("null")))
        {
            ethernet.setVisible(true);
            ethernetText.setText(r.getEthernetCordion());
        }
        
        if(!(r.getIpCordion().equals("null")))
        {
            ip.setVisible(true);
            ipText.setText(r.getIpCordion());
        }
        
        if(!(r.getProtocolCordion().equals("null")))
        {
            protocol.setVisible(true);
            protocolText.setText(r.getProtocolCordion());
        }
        
    }
    
    public void reset()
    {
        EndBtn();
        frameText.setText("");
        ethernetText.setText("");
        ipText.setText("");
        protocolText.setText("");
        HEXText.setText("");
        protocol.setVisible(false);
        frame.setVisible(false);
        ethernet.setVisible(false);
        ip.setVisible(false);
        SaveBtn.setDisable(true);
        Packets.clear();
        RowPacket.count=1;
    }
    
    public void filter()
    {
        FilteredList<RowPacket> filteredList = new FilteredList<>(Packets);
        

    }

}

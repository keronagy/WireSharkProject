
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

//    @FXML
//    private TableView<RowPacket> PacketsTable;
////    @FXML
////    private TableColumn<RowPacket, Integer> No;
//    @FXML
//    private TableColumn<RowPacket, String> Time;
//    @FXML
//    private TableColumn<RowPacket, String> Source;
//    @FXML
//    private TableColumn<RowPacket, String> Destination;
//    @FXML
//    private TableColumn<RowPacket, String> Protocol;
//    @FXML
//    private TableColumn<RowPacket, String> Length;
//    @FXML
//    private TableColumn<RowPacket, String> Info;
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

            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
                @Override
                public void nextPacket(PcapPacket packet, String user) {
                    PacketReader pr = new PacketReader();
                    pr.ReadPacket(packet);
                    String[] row = {"Time", "source", "Destination", "Protocol", "Length", "Info", "MoreDetail"};
                    RowPacket rp = new RowPacket(row);
                }
            };

            if (pcap == null) {
                System.err.printf("Error while opening device for capture: "
                        + errbuf.toString());

            }
            try {
                pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, "");
            } catch (Exception exp) {
                System.out.println("file ended");
            }

        }
    }

        @Override
        public void initialize
        (URL location, ResourceBundle resources
        
        ) {
//       // No.setCellValueFactory(new PropertyValueFactory<RowPacket, Integer>("No"));
//        Time.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Time"));
//        Source.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Source"));
//        Destination.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Destination"));
//        Protocol.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Protocol"));
//        Length.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Length"));
//        Info.setCellValueFactory(new PropertyValueFactory<RowPacket, String>("Info"));

    }

    //Assuming that this is the Start Button Action method
    

    public void StartBtn() {
        //DO NOT WRITE ANYTHING NEW HERE
        //ObservableList<RowPacket> Packets = FXCollections.observableArrayList();   
        Constants.pc.startCapturing();
//        while(true)
//        {
//            Packets.add(Constants.pc.pcapt.getLastPacket());
//            //PacketsTable.setItems(Packets);
//        }

    }

    //Assuming that this is the Stop Button Action method
    public void EndBtn() {
        //DO NOT WRITE ANYTHING NEW HERE
        Constants.pc.stopCapturing();
    }

    public void bonusTest() {

        Constants.pc.km.start();
    }

}

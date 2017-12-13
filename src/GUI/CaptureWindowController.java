
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import APIs.Constants;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.jnetpcap.JBufferHandler;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.nio.JBuffer;
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
            if (pcap == null) {
                System.err.printf("Error while opening device for capture: "
                        + errbuf.toString());

            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //Assuming that this is the Start Button Action method
    public void StartBtn() {
        //DO NOT WRITE ANYTHING NEW HERE
        Constants.pc.startCapturing();

    }

    //Assuming that this is the Stop Button Action method
    public void EndBtn() {
        //DO NOT WRITE ANYTHING NEW HERE
        Constants.pc.stopCapturing();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

/**
 *
 * @author Kord
 */
public class ProjectController {

    public PacketCapturer pcapt;
    public int NetworkInterfaceIndex; //it's static as when opening the capture window, it will capture only from a pre-specified interface
    private ArrayList<PcapIf> NetworkDevicesList;
    public Kmeans_Bonus km;
    Thread t;
    public String filename;

    public ProjectController() {
        km = new Kmeans_Bonus(loadData(filename), Constants.k);
        pcapt = new PacketCapturer();
        NetworkDevicesList = new ArrayList();

    }

    class CapturerThread implements Runnable {

        @Override
        public void run() {
            pcapt.StartCapture(NetworkInterfaceIndex);
        }
    }

    public void startCapturing() {

        CapturerThread ct = new CapturerThread();
        Thread t = new Thread(ct);
        t.start();

    }

    public void stopCapturing() {
        pcapt.stopCapturing();

    }

    public void setNetworkInterfaceIndex(int Index) {
        NetworkInterfaceIndex = Index;
    }

    public ArrayList<PcapIf> getNetworkInterfacesList() {
        return NetworkDevicesList;
    }

    public int getNetworkInterfaceIndex() {
        return NetworkInterfaceIndex;
    }

    public List getNics() {
        NetworkDevicesList = new ArrayList<PcapIf>(); // Will be filled with NICs   
        StringBuilder ErrorBuffer = new StringBuilder();     // For any error msgs          

        int ErrorFlag = Pcap.findAllDevs(NetworkDevicesList, ErrorBuffer);

        if (ErrorFlag != Pcap.OK) {
            System.out.println("Error in Network Device List " + ErrorBuffer.toString());
        }

        return NetworkDevicesList;
    }

    private int HexaToDec(String s) {
        int val = 0;
        int tobeadded = 0;
        int power = 0;
        s = s.toLowerCase();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) >= 97) {
                tobeadded = s.charAt(i) - 87;
                val += tobeadded * Math.pow(16, power);
                power++;
            } else {
                tobeadded = s.charAt(i) - '0';
                val += tobeadded * Math.pow(16, power);
                power++;
            }
        }
        return val;
    }

    public int[][] loadData(String fileName) {

        int[][] X = new int[Constants.m][Constants.n];

        char c = '-';
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader
                    = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);
            int k = 0;
            int i = 0;
            int j = 0;
            String elem = "";
            int val;

            while ((c = (char) bufferedReader.read()) != 'x') {
                elem += c;
                if (c == '|') {
                    elem = "";
                    continue;
                } else if (c == '\n') {
                    elem = "";
                    //System.out.println("");
                    i++;
                    j = 0;
                    continue;
                } else {
                    k++;
                    j++;
                    if (k == 2) {
                        val =  HexaToDec(elem);
                        try{
                        X[i][j] = val;
                        }catch(Exception e){
                          e.printStackTrace();
                                }
                        elem = "";
                        k = 0;
                    }
                }

            }
//            for (int i1 = 0; i1 < Constants.m; i1++) {
//                for (int j1 = 0; j1 < Constants.n; j1++) {
//                    System.out.println(X[i1][j1]);
//                }
//                System.out.println(" ");
//            }

            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
        }

        return X;
    }

}

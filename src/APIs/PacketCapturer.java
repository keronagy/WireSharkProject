/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 *
 * @author Kord
 */
public class PacketCapturer {
    Pcap pcap;
    public void StartCapture(int InterfaceIndex){
        OpenNetworkInterface(InterfaceIndex);
        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {  
            @Override
            public void nextPacket(PcapPacket packet, String user) {  
                 PacketReader pr = new PacketReader();
                 pr.ReadPacket(packet);
             }  
        };  
        try{
        pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, "");
        }
        catch(Exception e){System.out.println("Exception");};
    }
    
    public void OpenNetworkInterface(int InterfaceIndex){
        StringBuilder errbuf = new StringBuilder();
        int snalen = 64 * 1024;           // Capture all packets, no truncation 
            int promiscous = Pcap.MODE_PROMISCUOUS;
           // InterfaceIndex = 2; //for kord
        int timeout = 60 * 1000; // In milliseconds
        String device = Constants.pc.getNetworkInterfacesList().get(InterfaceIndex).getName();
        pcap=Pcap.openLive(device,snalen, promiscous, timeout, errbuf);
        System.out.println(device);
        
        if(pcap==null){
            System.out.println("Error when Opening the network interface with index : "+InterfaceIndex+"\nThe Errbuf is "+errbuf.toString());
        }
    }
    
    public void stopCapturing(){
      
        pcap.breakloop();
    
    }
    
    
}

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
        System.out.println("test1");
        OpenNetworkInterface(InterfaceIndex);
        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {  
            @Override
            public void nextPacket(PcapPacket packet, String user) {  
                System.out.println("test3");
                AccessPacket(packet);
             }  
        };  
        
        try{
            System.out.println("test2");
        pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, "");
        System.out.println("test4");
        }
        catch(Exception e){System.out.println("Exception");};
    }
    
    public void OpenNetworkInterface(int InterfaceIndex){
        StringBuilder errbuf = new StringBuilder();
        int snalen = 64 * 1024;           // Capture all packets, no truncation 
            int promiscous = Pcap.MODE_PROMISCUOUS;
        int timeout = 60 * 1000; // In milliseconds
        String device = Constants.pc.getNetworkInterfacesList().get(1).getName();
        pcap=Pcap.openLive(device,snalen, promiscous, timeout, errbuf);
        System.out.println(device);
        if(pcap==null){
            System.out.println("Error when Opening the network interface with index : "+InterfaceIndex+"\nThe Errbuf is "+errbuf.toString());
        }
    }
    
    public void stopCapturing(){
      
        pcap.breakloop();
    
    }
    
    public String AccessPacket(PcapPacket packet){
        byte b[]= new byte[50];
        b=packet.getByteArray(0, b);
        //just trying it :D
        String packetBytes=b.toString();
        if(packet.hasHeader(Tcp.ID)){
            Tcp tcp = packet.getHeader(new Tcp());
            String protocol="TCP";
            String ID = "ID : "+tcp.getId();
            String Name="Name : "+tcp.getName();
            String ack = "Ack : "+tcp.ack();
            String CheckSum="CheckSum : "+tcp.checksum();
            String CheckSumDescription = "CheckSum Description : "+tcp.checksumDescription();
            String srcPort = "Source Port : "+tcp.source();
            String destPort = "Destination Port : "+tcp.destination();
            String hlen = ""+tcp.hlen();
            String sequence = "Sequence : "+tcp.seq();
            String summary = "Summary : "+tcp.toString();
           
            System.out.println(packetBytes+"\n"+protocol+"\n"+ID+
                    "\n"+Name+"\n"+ack+"\n"+CheckSum+"\n"+CheckSumDescription+"\n"+srcPort+"\n"+destPort+"\n"+hlen+"\n"+sequence+"\n"+summary);
            return packetBytes+"\n"+protocol+"\n"+ID+"\n"+Name+"\n"+ack+"\n"+CheckSum+"\n"+CheckSumDescription+"\n"+srcPort+"\n"+destPort+"\n"+hlen+"\n"+sequence+"\n"+summary+"\n";
        }
        else if(packet.hasHeader(Http.ID)){
            Http http= packet.getHeader(new Http());
            String protocol = "HTTP";
            String HeaderLength = ""+http.getHeaderLength();
            String ContentType="Content Type : "+http.contentType();
            String HeaderDescription= "Header Description"+http.getDescription();
            String Name="Name : "+http.getName();
            String ID=""+http.getId();
            String summary = http.toString();
            
            System.out.println(packetBytes+"\n"+protocol+"\n"+HeaderLength+"\n"+ContentType+"\n"+HeaderDescription+"\n"+Name+"\n"+ID+"\n"+summary+"\n");
            return packetBytes+"\n"+protocol+"\n"+HeaderLength+"\n"+ContentType+"\n"+HeaderDescription+"\n"+Name+"\n"+ID+"\n"+summary+"\n";
        }
        
        
        //else if(packet.hasHeader(packet))
       return"";
    }

    
}

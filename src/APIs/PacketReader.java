/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;
import java.io.IOException;
import java.util.Date;
import jpcap.*;

import org.jnetpcap.*;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.JProtocol.*;
import static org.jnetpcap.protocol.JProtocol.UDP;
import org.jnetpcap.protocol.tcpip.*;
import org.jnetpcap.protocol.tcpip.Udp;


//import net.sourceforge.jpcap.net.PacketReader;
/**
 *
 * @author Mina
 */


public class PacketReader {
    
    public static int PacketNum=0;
    private String PacketData;
    private String PacketBytes;
    private int[] featuresList;
    private int packetSize;
    private long FrameNumber;
    private int PacketLength;
    private Date datee;
    private String protocol;
    private int ID;
    private int DesPort;
    int SrcPort;
    int DstPort;
    int HeaderLength;
    long ack;
    long CheckSum;
    long seqNum;
    int Payload;
    
    private int HexaToDec(String s)
    {
        int val=0;        
        return val;
    }

    private void ConstructFeaturesList()
    {
        
        //featuresList = new int[number of properties in this class];
        
        
    }
    
    public int[] getFeaturesList()
    {
        
        return featuresList;
    }
    
    public void ReadPacket(PcapPacket packet){
        Udp udp = new Udp();
        Http http = new Http();
        Tcp tcp = new Tcp();
        
        PacketBytes =packet.toHexdump();      
        packetSize = packet.size();
        FrameNumber = packet.getFrameNumber();
        PacketLength = packet.getPacketWirelen();
        
        if(packet.hasHeader(http)){
            http= packet.getHeader(new Http());
            protocol = "HTTP";
            ID = http.getId();
            //DesPort = http.getDescription();
            //SrcPort = ""+"";
            HeaderLength = http.getHeaderLength();
            //String MessageType="Message Type"+http.getMessageType();
            //String ContentType="Content Type : "+http.contentType();
            //String HeaderDescription= "Header Description"+http.getDescription();
            //String Name="Name : "+http.getName();
        }
        else if(packet.hasHeader(tcp)){
            tcp = packet.getHeader(new Tcp());
            protocol="TCP";
            ID = tcp.getId();
            HeaderLength = tcp.getHeaderLength();
            SrcPort = tcp.source();
            DstPort= tcp.destination();
           // HeaderContentByte = "The content of the Header as a byte array : "+tcp.getHeader();
            //String Name="Name : "+tcp.getName();
            ack = tcp.ack();
            CheckSum = tcp.checksum();
            HeaderLength = tcp.hlen();
            seqNum = tcp.seq();
              
        }
        else if(packet.hasHeader(new Udp())){
            udp = packet.getHeader(new Udp());
            protocol = "UDP";
            ID = udp.getId();
            HeaderLength = udp.getHeaderLength();
            SrcPort = udp.source();
            DesPort= udp.destination();
           // String HeaderContentByte = "The content of the Header as a byte array : "+udp.getHeader();
            CheckSum = udp.checksum();
            //Payload = udp.getPayload().toString();
            
            
   
        }
        
        //else if dns
        //else if arp
        //else if icmp
        
        
    }
    
    public String[] getStringArray()
    {
     //return a string that contains all packets data
        String[] packetString = {""};
        return packetString;
    }
    
}
 

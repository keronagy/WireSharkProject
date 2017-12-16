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
    
       
    //general data
    private Date datee;
    private String PacketBytes;
    private int DesPort;
    int SrcPort;
    private String protocol;
    private int length;
    private String info;
    private int ID;
    
            //more details window
                                            
    //frame
    private int InterfaceId;
    private int EpochTime;
    private long FrameNumber;
    private int FrameLength;
    
    //ethernet
    private String EthernetDest;
    private String EthernetSource;
    
    //internet protocol version
    private int IPversion;
    private int HeaderLeangth;
    private int IPid;
    private boolean fragmentflag;
    private int fragmentOffset;
    private int timeToLive;
    
    
    //tcp and udp
    private int sourcePort;
    private int DistinationPort;
    private int checkSum;
    //tcp only
    private int seqNumber;
    private int ackNumber;
    private int windowSize;
    
    
    
    //http only
    String host;
    String ConnectionType;
    String userAgent;
    String AcceptEncoding;
    
    
    //bonus
    private int[] featuresList;

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
        length = packet.size();
        FrameNumber = packet.getFrameNumber();
        //PacketLength = packet.getPacketWirelen();
        
        if(packet.hasHeader(http)){
            http= packet.getHeader(new Http());
            protocol = "HTTP";
            ID = http.getId();
            //DesPort = http.getDescription();
            //SrcPort = ""+"";
            //HeaderLength = http.getHeaderLength();
            //String MessageType="Message Type"+http.getMessageType();
            //String ContentType="Content Type : "+http.contentType();
            //String HeaderDescription= "Header Description"+http.getDescription();
            //String Name="Name : "+http.getName();
        }
        else if(packet.hasHeader(tcp)){
            tcp = packet.getHeader(new Tcp());
            protocol="TCP";
            ID = tcp.getId();
            //HeaderLength = tcp.getHeaderLength();
            SrcPort = tcp.source();
            //DstPort= tcp.destination();
           // HeaderContentByte = "The content of the Header as a byte array : "+tcp.getHeader();
            //String Name="Name : "+tcp.getName();
//            ack = tcp.ack();
//            CheckSum = tcp.checksum();
//            HeaderLength = tcp.hlen();
//            seqNum = tcp.seq();
//              
        }
        else if(packet.hasHeader(new Udp())){
            udp = packet.getHeader(new Udp());
            protocol = "UDP";
            ID = udp.getId();
//            HeaderLength = udp.getHeaderLength();
            SrcPort = udp.source();
            DesPort= udp.destination();
           // String HeaderContentByte = "The content of the Header as a byte array : "+udp.getHeader();
//            CheckSum = udp.checksum();
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
 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;
import GUI.CaptureWindowController;
import GUI.RowPacket;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.*;

import org.jnetpcap.*;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.JProtocol.*;
import static org.jnetpcap.protocol.JProtocol.UDP;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.*;
import org.jnetpcap.protocol.tcpip.Udp;


//import net.sourceforge.jpcap.net.PacketReader;
/**
 *
 * @author Mina
 */


public class PacketReader {
    
       
    //general data
    private Date Time;//
    private String PacketBytes;//
    private String Source;//
    private String Destination;//
    private String protocol;//
    private int length;//   
    private String info="";
    private String MoreDetails="";
    ArrayList AllInfo=new ArrayList();
    
    
    

    //bonus
    private int[] featuresList;
    private static int counter =0;
   
    private void ConstructFeaturesList(String PacketBytes)
    {
        
        //featuresList = new int[atwal String];
        
        
    }
    
    public int[] getFeaturesList()
    {
        
        return featuresList;
    }
    
    public void ReadPacket(PcapPacket packet) {
        Udp udp = new Udp();
        Http http = new Http();
        Tcp tcp = new Tcp();
        Ip4 ip = new Ip4();
        
        //Time 
        Time = new Date(packet.getCaptureHeader().timestampInMillis());
        
        PacketBytes =packet.toHexdump();        
        ConstructFeaturesList(PacketBytes); 
        System.out.println("PacketBytes"+PacketBytes);
        
        //packet Length 
        length = packet.size();
        
        //Source and Destination IP Address
        if(packet.hasHeader(ip)){
            byte[] b = packet.getHeader(ip).source();
            try {
                Source = Inet4Address.getByAddress(b).getHostAddress();
            } catch (UnknownHostException ex) {
                System.out.println(ex.toString()+"asdasdasda");
            }
            b=packet.getHeader(ip).destination();
            try {
                Destination=Inet4Address.getByAddress(b).getHostAddress();
            } catch (UnknownHostException ex) {
                System.out.println(ex.toString()+"asdasdasda");
            }
        }
        if(packet.hasHeader(http)){
            http= packet.getHeader(new Http());
            protocol = "HTTP";
            info="Header length "+http.getLength();
//              ID = http.getId();
//            DesPort = http.getDescription();
//            SrcPort = ""+"";
//            HeaderLength = http.getHeaderLength();
//            String MessageType="Message Type"+http.getMessageType();
//            String ContentType="Content Type : "+http.contentType();
//            String HeaderDescription= "Header Description"+http.getDescription();
//            String Name="Name : "+http.getName();
              
        }
        else if(packet.hasHeader(tcp)){
            tcp = packet.getHeader(new Tcp());
            protocol="TCP";
            info+="Seq : "+tcp.seq()+"Ack : "+tcp.ack()+"SYN : "+tcp.flags_SYN();
//            ID = tcp.getId();
//            HeaderLength = tcp.getHeaderLength();
//            SrcPort = tcp.source();
//            DstPort= tcp.destination();
//            HeaderContentByte = "The content of the Header as a byte array : "+tcp.getHeader();
//            String Name="Name : "+tcp.getName();
//            ack = tcp.ack();
//            CheckSum = tcp.checksum();
//            HeaderLength = tcp.hlen();
//            seqNum = tcp.seq();
                
//              
        }
        else if(packet.hasHeader(new Udp())){
            udp = packet.getHeader(new Udp());
            protocol = "UDP";
            info+="LEN="+udp.length()+"Check Description"+udp.checksumDescription();
//            ID = udp.getId();
//            HeaderLength = udp.getHeaderLength();
//            SrcPort = udp.source();
//            DesPort= udp.destination();
           // String HeaderContentByte = "The content of the Header as a byte array : "+udp.getHeader();
//            CheckSum = udp.checksum();
            //Payload = udp.getPayload().toString();
            
            
   
        }
        
        //else if dns
        //else if arp
        //else if icmp
        
     
//        AllInfo.add(Time);//0
//        AllInfo.add(PacketBytes);//1
//        AllInfo.add(Source);//2
//        AllInfo.add(Destination);//3
//        AllInfo.add(protocol);//4 
//        AllInfo.add(length);
//        AllInfo.add(info);
//        AllInfo.add(MoreDetails);
        String[] row = {""+Time,Source,Destination,protocol,""+length,info};
        RowPacket packetRow = new RowPacket(row);
        CaptureWindowController.Packets.add(packetRow);
        
    }
    
    public String[] getStringArray()
    {
     //return a string that contains all packets data
        String[] packetString = {""};
        return packetString;
    }
    
    
    
    
}
 

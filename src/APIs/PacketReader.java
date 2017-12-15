/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;
import java.io.IOException;
import jpcap.*;

import org.jnetpcap.*;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.JProtocol.*;
import static org.jnetpcap.protocol.JProtocol.UDP;
import org.jnetpcap.protocol.tcpip.*;
import org.jnetpcap.protocol.tcpip.Udp;


//import net.sourceforge.jpcap.net.Packet;
/**
 *
 * @author Mina
 */


public class PacketReader {
    
    public static int PacketNum=0;
    public String ReadPacket(PcapPacket packet){
        byte b[]= new byte[50];
        b=packet.getByteArray(0, b);
        Udp udp = new Udp();
        Http http = new Http();
        Tcp tcp = new Tcp();
        
        String packetBytes=packet.toHexdump();
        String PacketTotalSize="Number of bytes currently allocated : "+packet.size();
        String FrameNumber="Frame Number : "+packet.getFrameNumber();
        String PacketLength ="Original Packet Length : "+packet.getPacketWirelen();
        String PacketSummary="Packet Summary : "+packet.toString()+"finished";
        String PacketInfo="Packet Info : \n"+packetBytes+"\n"+PacketTotalSize+"\n"+FrameNumber+"\n"+PacketLength+"\n"+PacketSummary+"\n";
        
        String FinalString="Not Initialized";
        if(packet.hasHeader(http)){
            System.out.println("<<<<<<<<<<<<\n<<<<<<<<<<<<<<<<<<<\n<<<<<<<<<<<<<<<<<<<<<<<\n<<<<<<<<<<<<<");
            http= packet.getHeader(new Http());
            String protocol = "HTTP";
            String ID=""+http.getId();
            String DesPort = "Destination Port "+http.getDescription();
            String SrcPort = "Src Port"+"";
            String HeaderLength = "Header Length : "+http.getHeaderLength();
            String MessageType="Message Type"+http.getMessageType();
            String ContentType="Content Type : "+http.contentType();
            String HeaderDescription= "Header Description"+http.getDescription();
            String Name="Name : "+http.getName();
            String summary = http.toString();
            FinalString = SrcPort+"\n"+DesPort+"\n"+protocol+"\n"+HeaderLength+"\n"+MessageType+"\n"+ContentType+"\n"+HeaderDescription+"\n"+Name+"\n"+ID+"\n"+summary+"\n";
        }
        else if(packet.hasHeader(tcp)){
            tcp = packet.getHeader(new Tcp());
            String protocol="TCP";
            String ID = "ID : "+tcp.getId();
            String HeaderLength = "Header Length : "+tcp.getHeaderLength();
            String SrcPort = "Source Port : "+tcp.source();
            String RecPort="Reciever's Port : "+tcp.destination();
            String HeaderContentByte = "The content of the Header as a byte array : "+tcp.getHeader();
            String Name="Name : "+tcp.getName();
            String ack = "Ack : "+tcp.ack();
            String CheckSum="CheckSum : "+tcp.checksum();
            String CheckSumDescription = "CheckSum Description : "+tcp.checksumDescription();
            String hlen = "Header Length"+tcp.hlen();
            String sequence = "Sequence : "+tcp.seq();
            String summary = "Summary : "+tcp.toString();
           
            FinalString=SrcPort+"\n"+RecPort+"\n"+protocol+"\n"+HeaderLength+"\n"+ID+"\n"+HeaderContentByte+"\n"
                    +Name+"\n"+ack+"\n"+CheckSum+"\n"
                    +CheckSumDescription+"\n"+SrcPort+"\n"+RecPort+"\n"+hlen+"\n"+sequence+"\n"+summary+"\n";
            
        }
        else if(packet.hasHeader(udp)){
            System.out.println("UDP");
            udp = packet.getHeader(new Udp());
            String protocol = "UDP";
            String ID = "ID : "+udp.getId();
            String HeaderLength="Header Length : "+udp.getHeaderLength();
            String SrcPort = "Source Port : "+udp.source();
            String RecPort="Reciever's Port : "+udp.destination();
            String HeaderContentByte = "The content of the Header as a byte array : "+udp.getHeader();
            String HeaderDescription = "Header Description : "+udp.getDescription();
            String Name="Name : "+udp.getName();
            String CheckSum="CheckSum : "+udp.checksum();
            String CheckSumDescription = "CheckSum Description : "+udp.checksumDescription();
            String Payload = "The playload data portion of the packet right after the current header : "+udp.getPayload();
            String summary = "Summary : "+udp.toString();
            
            FinalString=SrcPort+"\n"+RecPort+"\n"+protocol+"\n"+HeaderLength+"\n"+HeaderContentByte+"\n"+HeaderDescription+"\n"+
                    Name+"\n"+CheckSum+"\n"+CheckSumDescription+"\n"+Payload+"\n"+summary;
        }
        
        
      System.out.println(FinalString+PacketInfo);
      return FinalString+PacketInfo;
    }


}
 

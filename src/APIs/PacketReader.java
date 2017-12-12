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
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
//import net.sourceforge.jpcap.net.Packet;
/**
 *
 * @author Mina
 */


public class PacketReader {

    public String ReadPacket(PcapPacket packet){
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
 

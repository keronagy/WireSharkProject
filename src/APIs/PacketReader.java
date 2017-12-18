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
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
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
    private String ipCordion;
    private String frameCordion;
    private String EthernetCordion;
    private String protocolCordion;
    ArrayList AllInfo = new ArrayList();

    //bonus
    private int[] featuresList;
    private static int counter = 0;

    private void ConstructFeaturesList(String PacketBytes) {

        //featuresList = new int[atwal String];
    }

    public int[] getFeaturesList() {

        return featuresList;
    }

    public String[] ReadPacket(PcapPacket packet) {
        Udp udp = new Udp();
        Http http = new Http();
        Tcp tcp = new Tcp();
        Ip4 ip = new Ip4();
        Arp arp = new Arp();
        Icmp icmp = new Icmp();
        String tmp = packet.toString();
        int tmpcount = 0;

        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == '*') {
                break;
            }
            tmpcount++;

        }

        frameCordion = tmp.substring(0, tmpcount - 6);
        int tmpcount1 = 0;

        for (int i = frameCordion.length(); i < tmp.length(); i++) {
            if (tmp.charAt(i) == 'I' && tmp.charAt(i + 1) == 'p' && tmp.charAt(i + 2) == '4') {
                break;
            }
            tmpcount1++;
        }
        EthernetCordion = tmp.substring(frameCordion.length(),tmpcount1+140);
        Time = new Date(packet.getCaptureHeader().timestampInMillis());

        PacketBytes = packet.toHexdump();
        ConstructFeaturesList(PacketBytes);
        length = packet.size();

        //Source and Destination IP Address
        if (packet.hasHeader(ip)) {
            byte[] b = packet.getHeader(ip).source();
            try {
                Source = Inet4Address.getByAddress(b).getHostAddress();
                Destination = Inet4Address.getByAddress(b).getHostAddress();
            } catch (UnknownHostException ex) {
            }
            ipCordion = ip.toString();
        }
        if (packet.hasHeader(http)) {
            http = packet.getHeader(new Http());
            protocol = "HTTP";
            info += "Header length " + http.getLength();
            protocolCordion = http.toString();
//              ID = http.getId();
//            DesPort = http.getDescription();
//            SrcPort = ""+"";
//            HeaderLength = http.getHeaderLength();
//            String MessageType="Message Type"+http.getMessageType();
//            String ContentType="Content Type : "+http.contentType();
//            String HeaderDescription= "Header Description"+http.getDescription();
//            String Name="Name : "+http.getName();

        } else if (packet.hasHeader(tcp)) {
            tcp = packet.getHeader(new Tcp());
            protocol = "TCP";
            info += "Seq : " + tcp.seq() + " Ack: " + tcp.ack() + " SYN: " + tcp.flags_SYN();
            protocolCordion = tcp.toString();
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
        } else if (packet.hasHeader(new Udp())) {
            udp = packet.getHeader(new Udp());
            protocol = "UDP";
            info += "LEN=" + udp.length() + "Check Description" + udp.checksumDescription();
            protocolCordion = udp.toString();
//            ID = udp.getId();
//            HeaderLength = udp.getHeaderLength();
//            SrcPort = udp.source();
//            DesPort= udp.destination();
            // String HeaderContentByte = "The content of the Header as a byte array : "+udp.getHeader();
//            CheckSum = udp.checksum();
            //Payload = udp.getPayload().toString();

        } else if (packet.hasHeader((new Arp()))) {
            arp = packet.getHeader(new Arp());
            protocol = "ARP";
            //info = 
            protocolCordion = arp.toString();
        } else if (packet.hasHeader(new Icmp())) {
            icmp = packet.getHeader((new Icmp()));
            protocol = "ICMP";
            //info
            protocolCordion = icmp.toString();
        }

        
        return new String[]{"" + Time, Source, Destination, protocol, "" + length, info, PacketBytes, frameCordion,EthernetCordion,ipCordion,protocolCordion};
    }

}

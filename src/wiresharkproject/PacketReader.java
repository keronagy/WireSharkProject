/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;
import jpcap.*;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;
//import net.sourceforge.jpcap.net.Packet;
/**
 *
 * @author Mina
 */
public class PacketReader implements PacketReceiver{
    
        
    String showTCPDetails(Packet packet){
        TCPPacket tcp=(TCPPacket)packet;
        
        String ACK="\nACK:  "+tcp.ack;
        
        String SrcPort="\nSource Port :  "+tcp.src_port;
        
        String DestPort="\nDestination Port :  "+tcp.dst_port;
        
        String ACKNum="\nACKNum:  "+tcp.ack_num;
        
        String FINFlag="\nFIN Flag :  "+tcp.fin;
        
        String TCPOption="\nTCP Option :  ";
        for(int i =0 ; i<tcp.option.length;i++){
            TCPOption=TCPOption+tcp.option[i]+" ";
        }
        
        String RSTFlag="\nRST Flag :  "+tcp.rst;
        
        String RSV1Flag="\nRSV1 Flag :  "+tcp.rsv1;
        
        String RSV2Flag="\nRSV2 Flag :  "+tcp.rsv2;
        
        String Sequence="\nSequence Number:  "+tcp.sequence;
        
        String SYNFlaf="\n SYN Flag :  "+tcp.syn;
        
        System.out.println(SrcPort+DestPort+ACK+ACKNum+FINFlag+TCPOption+RSTFlag+RSV1Flag+RSV2Flag+Sequence+SYNFlaf);
        return SrcPort+DestPort+ACK+ACKNum+FINFlag+TCPOption+RSTFlag+RSV1Flag+RSV2Flag+Sequence+SYNFlaf;
        
        
        
    }
    
    String showUDPDetails(Packet packet){
        UDPPacket udp=(UDPPacket)packet;
        String UDPPacketInfo=udp.toString();
        String SrcPort = "\nSource Port number :  "+udp.src_port;
        String DestPort = "\nDestination Port Number :  "+udp.dst_port;
        String PacketLength="\nPacket Length :  "+udp.length;
        String d_flag ="\nIP flag bit :  "+udp.d_flag;
        String protocol="\nProtocol (v4/v6) :  "+udp.protocol;
        String priority="\nPriority (class) (v4/v6) :  "+udp.priority;
        
        
        System.out.println("\n\n"+UDPPacketInfo+SrcPort+DestPort+PacketLength+d_flag+protocol+priority);
        return UDPPacketInfo+SrcPort+DestPort+PacketLength+d_flag+protocol+priority;
    }
        
    String showGeneralInformation(Packet packet){
        String Packet="Packet: \n\n"+packet.toString()+"\n\n";
        
        String Length = "Length : "+packet.caplen+"\n\n";
        
        String Data ="Data:\n\n";
        System.out.println("Data");
        for(int i = 0; i<packet.data.length;i++)
            Data+=packet.data[i];
        Data+="\n\n";
        
        
        String Header = "Header:\n\n";
        for(int i = 0; i<packet.header.length;i++)
            Header+=packet.header[i];
        Header+="\n\n";
        
        String DataLink = "Data Link : \n\n"+packet.datalink.toString()+"\n\n";
        
        System.out.println(Packet+Length+Header+Data+DataLink);
        return Packet+Length+Header+Data+DataLink;
        
    }
    //This Dummy
    public void receivePacket(Packet packet) {
        if(packet instanceof jpcap.packet.TCPPacket){
            System.out.println("TCP!!!");
            showTCPDetails(packet);
            
        }
        else if(packet instanceof jpcap.packet.UDPPacket){
           System.out.println("UDP!!!!");
            showUDPDetails(packet);
        }
        else{
            String s=showGeneralInformation(packet);
        }
    }
    
}
 

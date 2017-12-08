/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;
import jpcap.*;
import jpcap.packet.Packet;
//import net.sourceforge.jpcap.net.Packet;
/**
 *
 * @author Mina
 */
public class PacketReader implements PacketReceiver{
    
    
    String showDetailedView(Packet packet){
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
        String s=showDetailedView(packet);
    }
    
  
    
}

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
    
    
    void showDetailedView(Packet packet){
        System.out.println("Length "+packet.caplen);
        
        System.out.println("Data");
        for(int i = 0; i<packet.data.length;i++){
            System.out.print(packet.data[i]);
        }
        System.out.println("");
        System.out.println("Header ");
        for(int i = 0; i<packet.header.length;i++){
            System.out.print(packet.header[i]);
        }
        System.out.println("");
        System.out.println("Data Link \n"+packet.datalink.toString());
        System.out.println("toString \n"+packet.toString());
    }
    //This Dummy
    public void receivePacket(Packet packet) {
        System.out.println(packet);
        showDetailedView(packet);
    }
    
  
    
}

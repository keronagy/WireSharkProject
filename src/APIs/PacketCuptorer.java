/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;

import java.io.IOException;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

/**
 *
 * @author Kord
 */
public class PacketCuptorer {
    NetworkInterface[] jpcapni;
    JpcapCaptor captor;

    public PacketCuptorer() {
        jpcapni = projectController.getNicsJpcap();
    }
    
       public void jPcapStartCapturing(int InterfaceIndex){
        try {
            System.out.println("Beginning");
            JpcapCaptor captor=JpcapCaptor.openDevice(jpcapni[InterfaceIndex], 65535, false, 20);
            captor.processPacket(-1,new PacketReader());
            
           
            
        } catch (IOException ex) {
            System.out.println("EXCEPTION");
        }
        
      
    }
       
     public void jPcapEndCapturing()
        {
            captor.close();
             System.out.println("Ending");
        }
    
}

package wiresharkproject;
import java.io.IOException;
import jpcap.*;



public class PackageCapture {
       public PackageCapture() throws IOException{
         NetworkInterface [] Devices  = JpcapCaptor.getDeviceList();
          JpcapCaptor captor=JpcapCaptor.openDevice(Devices[0], 65535, false, 200000000);
          int numberofpacketscaptured =  captor.processPacket(-1, new PacketPrinter());
           System.out.println(numberofpacketscaptured);
}
}

package wiresharkproject;
import java.io.IOException;
import jpcap.*;



public class PackageCapture {
    JpcapCaptor captor ;
      
void StartCapturing() throws IOException{
      NetworkInterface [] Devices  = JpcapCaptor.getDeviceList();
          captor=JpcapCaptor.openDevice(Devices[0], 65535, false, 100000);
          int numberofpacketscaptured =  captor.processPacket(-1, new PacketPrinter());
          System.out.println(numberofpacketscaptured);
}
void StopCapturing(){
    captor.breakLoop();
}
}

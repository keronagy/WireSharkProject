package wiresharkproject;
import jpcap.*;
import jpcap.packet.Packet;
public class PacketPrinter implements PacketReceiver {

    @Override
    public void receivePacket(Packet packet) {
        System.out.println(packet);
    }
    
}

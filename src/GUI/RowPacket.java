package GUI;

import APIs.PacketReader;
import javafx.beans.property.SimpleStringProperty;
import org.jnetpcap.packet.PcapPacket;

public class RowPacket {
    public static int count = 1;
    private SimpleStringProperty No;   
    private SimpleStringProperty Time;
    private SimpleStringProperty Source;
    private SimpleStringProperty Destination;
    private SimpleStringProperty Protocol;
    private SimpleStringProperty Length;
    private SimpleStringProperty Info;
    private SimpleStringProperty HexView;
    private SimpleStringProperty frameCordion;
    private SimpleStringProperty EthernetCordion;
    private SimpleStringProperty ipCordion;
    private SimpleStringProperty protocolCordion;
    private SimpleStringProperty MoreDetails; //remove it ya kiro no need for it now
    public RowPacket() {
    }
    
    
    
//    public RowPacket(String Time, String Source, String Destination, String Protocol, String Length, String Info) {
//        this.No= new SimpleStringProperty((count++) +"");
//        this.Time = new SimpleStringProperty(Time);
//        this.Source = new SimpleStringProperty(Source);
//        this.Destination = new SimpleStringProperty(Destination);
//        this.Protocol = new SimpleStringProperty(Protocol);
//        this.Length = new SimpleStringProperty(Length);
//        this.Info = new SimpleStringProperty(Info);
//        
//    }

    public RowPacket(String[] row) {
        this.No = new SimpleStringProperty((count++) +"");
        this.Time = new SimpleStringProperty(row[0]);
        this.Source = new SimpleStringProperty(row[1]);
        this.Destination = new SimpleStringProperty(row[2]);
        this.Protocol = new SimpleStringProperty(row[3]);
        this.Length = new SimpleStringProperty(row[4]);
        this.Info = new SimpleStringProperty(row[5]);
        this.HexView = new SimpleStringProperty(row[6]);
        this.frameCordion = new SimpleStringProperty(row[7]);
        this.EthernetCordion = new SimpleStringProperty(row[8]);
        this.ipCordion = new SimpleStringProperty(row[9]);
        this.protocolCordion = new SimpleStringProperty(row[10]);
        this.MoreDetails = new SimpleStringProperty(row[7]+row[8]+row[9]+row[10]);
    }


    public String getNo(){
        return No.get();
    }
    public String getTime(){
        return Time.get();
    }
    public String getSource(){
        return Source.get();
    }
    public String getDestination(){
        return Destination.get();
    }
    public String getProtocol (){
        return Protocol.get();
    }
    public String getLength(){
        return Length.get();
    }
    public String getInfo(){
        return Info.get();
    }

    public String getHexView() {
        return HexView.get();
    }

    public String getMoreDetail() {
        return MoreDetails.get();
    }

    public String getEthernetCordion() {
        return EthernetCordion.get();
    }

    public String getFrameCordion() {
        return frameCordion.get();
    }

    public String getProtocolCordion() {
        return protocolCordion.get();
    }

    public String getIpCordion() {
        return ipCordion.get();
    }
    
    
}

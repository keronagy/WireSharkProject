package GUI;

import APIs.PacketReader;
import javafx.beans.property.SimpleStringProperty;
import org.jnetpcap.packet.PcapPacket;

public class RowPacket {
    private static int count = 0;
    private  Integer No = 0;   
    private SimpleStringProperty Time;
    private SimpleStringProperty Source;
    private SimpleStringProperty Destination;
    private SimpleStringProperty Protocol;
    private SimpleStringProperty Length;
    private SimpleStringProperty Info;
    private SimpleStringProperty HexView;
    private SimpleStringProperty MoreDetail; // Contains the cordions data, it will need to be parsed

    public RowPacket() {
    }
    
    
    
    public RowPacket(String Time, String Source, String Destination, String Protocol, String Length, String Info) {
        this.No= count++;
        this.Time = new SimpleStringProperty(Time);
        this.Source = new SimpleStringProperty(Source);
        this.Destination = new SimpleStringProperty(Destination);
        this.Protocol = new SimpleStringProperty(Protocol);
        this.Length = new SimpleStringProperty(Length);
        this.Info = new SimpleStringProperty(Info);
    }

    public RowPacket(String[] row) {
        this.No++;
        this.Time = new SimpleStringProperty(row[0]);
        this.Source = new SimpleStringProperty(row[1]);
        this.Destination = new SimpleStringProperty(row[2]);
        this.Protocol = new SimpleStringProperty(row[3]);
        this.Length = new SimpleStringProperty(row[4]);
        this.Info = new SimpleStringProperty(row[5]);
        //this.HexView = new SimpleStringProperty(row[6]);
        //this.MoreDetail = new SimpleStringProperty(row[7]);

    }


    public int getNo(){
        return No;
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
}

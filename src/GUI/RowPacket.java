package GUI;

import APIs.PacketReader;
import javafx.beans.property.SimpleStringProperty;

public class RowPacket {
   public static int No;
    private SimpleStringProperty Time;
    private SimpleStringProperty Source;
    private SimpleStringProperty Destination;
    private SimpleStringProperty Protocol;
    private SimpleStringProperty Length;
    private SimpleStringProperty Info;
    public RowPacket (String Time , String Source , String Destination , String Protocol , String Length , String Info){
        this.No++;
        this.Time=new SimpleStringProperty (Time);
        this.Source= new SimpleStringProperty (Source);
        this.Destination = new SimpleStringProperty (Destination);
        this.Protocol = new SimpleStringProperty (Protocol);
        this.Length = new SimpleStringProperty (Length);
        this.Info = new SimpleStringProperty (Info);
    }
    public int GetNo(){
     return No;
    }
    public String GetTime(){
        return Time.get();
    }
    public String GetSource(){
        return Source.get();
    }
    public String GetDestination(){
        return Destination.get();
    }
    public String GetProtcol (){
        return Protocol.get();
    }
    public String GetLength(){
        return Length.get();
    }
    public String GetInfo(){
        return Info.get();
    }
}

package GUI;

import APIs.PacketReader;
import javafx.beans.property.SimpleStringProperty;
import org.jnetpcap.packet.PcapPacket;

public class RowPacket {

    public static int No;
    private SimpleStringProperty Time;
    private SimpleStringProperty Source;
    private SimpleStringProperty Destination;
    private SimpleStringProperty Protocol;
    private SimpleStringProperty Length;
    private SimpleStringProperty Info;
    private SimpleStringProperty MoreDetail; // Contains the cordions data, it will need to be parsed
    
    public RowPacket(String Time, String Source, String Destination, String Protocol, String Length, String Info) {
        this.No++;
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
        this.MoreDetail = new SimpleStringProperty(row[6]);

    }

//    public static void CreateRow (PcapPacket packet){
//        int counter=0;
//        PacketReader pr = new PacketReader();
//        String str = pr.ReadPacket(packet);
//        String Date="";
//        String SrcPort="";
//        String DesPort="";
//        String Protocol ="";
//        String Length ="";
//        String Info ="";
//        for (int i=0;i<str.length();i++){
//           if(str.charAt(i)=='+'){
//               break;
//           }
//           else{
//               Date+=str.charAt(i);
//           }
//        }
//        for (int i=0;i<str.length();i++){
//             if(str.charAt(i)=='+' && counter<4){
//               for (int j=i+1;j<str.length();j++){
//                   if(counter==0){
//                       if(str.charAt(j)!='+'){
//                       SrcPort+=str.charAt(j);
//                       continue;
//                       }
//                       counter++;
//                       break;
//                   }
//                    if(counter==1){
//                       if(str.charAt(j)!='+'){
//                       SrcPort+=str.charAt(j);
//                       continue;
//                       }
//                       counter++;
//                       break;
//                   }
//                     if(counter==2){
//                       if(str.charAt(j)!='+'){
//                       Protocol+=str.charAt(j);
//                       continue;
//                       }
//                       counter++;
//                       break;
//                   }
//                      if(counter==3){
//                       if(str.charAt(j)!='+'){
//                       Length+=str.charAt(j);
//                       continue;
//                       }
//                       counter++;
//                       break;
//                   }
//               }
//           }
//             if(counter>=4){
//                 if (str.charAt(i)!='+'){
//                     Info +=str.charAt(i);
//                 }
//             }
//        }
//        RowPacket row = new RowPacket (Date,SrcPort,DesPort,Protocol,Length,Info);
//   
//        
//      
//    }
    public int GetNo() {
        return No;
    }

    public String GetTime() {
        return Time.get();
    }

    public String GetSource() {
        return Source.get();
    }

    public String GetDestination() {
        return Destination.get();
    }

    public String GetProtcol() {
        return Protocol.get();
    }

    public String GetLength() {
        return Length.get();
    }

    public String GetInfo() {return Info.get();}

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
    public String getProtcol (){
        return Protocol.get();
    }
    public String getLength(){
        return Length.get();
    }
    public String getInfo(){
        return Info.get();
    }
}

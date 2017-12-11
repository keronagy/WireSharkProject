    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Kero
 */
public class PcapIfTable {

    private final SimpleStringProperty AdapterName;
    private final SimpleStringProperty AdapterIP;

    public PcapIfTable(String n, String k) {
        this.AdapterName = new SimpleStringProperty(n);
        this.AdapterIP = new SimpleStringProperty(k);
    }

    public String getAdapterIP() {
        return AdapterIP.get();
    }

    public String getAdapterName() {
        return AdapterName.get();
    }

}

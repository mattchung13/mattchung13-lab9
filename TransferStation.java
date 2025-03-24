import java.util.*;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

import java.util.ArrayList;

public class TransferStation extends Station {
    public ArrayList<Station> otherStations;

    public TransferStation(String line, String name) {
        super(line, name);
        otherStations = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        String base = "TRANSFERSTATION " + name + ": " + line + " line, in service: " + inServiceString() +
                      ", previous station: " + (prev == null ? "none" : prev.name) +
                      ", next station: " + (next == null ? "none" : next.name) + "\n\tTransfers: \n";
        String transfersStr = "";
        for (Station s : otherStations) {
            transfersStr += "\t" + s.toString() + "\n";
        }
        return base + transfersStr;
    }
    
    public void addTransferStationPrev(Station s) {
        s.next = this;
        otherStations.add(s);
    }
    
    public void addTransferStationNext(Station s) {
        s.prev = this;
        otherStations.add(s);
    }
}

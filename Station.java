import java.util.*;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

public class Station {
    protected String line;
    protected String name;
    protected boolean inService;
    public Station prev;
    public Station next;
    
    public Station(String line, String name) {
        this.line = line;
        this.name = name;
        this.inService = true;
        this.prev = null;
        this.next = null;
    }
    
    public void addNext(Station s) {
        this.next = s;
        s.prev = this;
    }
    
    public void addPrev(Station s) {
        this.prev = s;
        s.next = this;
    }
    
    public boolean isAvailable() {
        return inService;
    }
    
    public void switchAvailable() {
        inService = !inService;
    }
    
    public void connect(Station s) {
        addNext(s);
    }
    
    @Override
    public String toString() {
        String type = "STATION";
        return type + " " + name + ": " + line + " line, in service: " + inServiceString() +
               ", previous station: " + (prev == null ? "none" : prev.name) +
               ", next station: " + (next == null ? "none" : next.name);
    }
    
    protected String inServiceString() {
        return inService ? "true" : "false";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Station) {
            Station other = (Station)o;
            return this.name.equals(other.name) && this.line.equals(other.line);
        }
        return false;
    }
    
    public int tripLength(Station dest) {
        return tripLengthHelper(dest, new HashSet<Station>());
    }
    
    private int tripLengthHelper(Station dest, Set<Station> visited) {
        System.out.println("At station: " + this.name + " | Destination: " + dest.name);
        if (this.equals(dest)) {
            return 0;
        }
        visited.add(this);
        int minStops = Integer.MAX_VALUE;
        
        if (this.next != null && !visited.contains(this.next)) {
            int stops = this.next.tripLengthHelper(dest, visited);
            if (stops != -1 && stops + 1 < minStops) {
                minStops = stops + 1;
            }
        }
        
        if (this instanceof TransferStation) {
            TransferStation ts = (TransferStation)this;
            for (Station t : ts.otherStations) {
                if (!visited.contains(t)) {
                    int stops = t.tripLengthHelper(dest, visited);
                    if (stops != -1 && stops + 1 < minStops) {
                        minStops = stops + 1;
                    }
                }
            }
        }
        
        visited.remove(this);
        return minStops == Integer.MAX_VALUE ? -1 : minStops;
    }
}

import java.util.*;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

public class EndStation extends Station {

    public EndStation(String line, String name) {
        super(line, name);
    }
    
    @Override
    public String toString() {
        return "ENDSTATION " + name + ": " + line + " line, in service: " + inServiceString() +
               ", previous station: " + (prev == null ? "none" : prev.name) +
               ", next station: " + (next == null ? "none" : next.name);
    }
    
    public void makeEnd() {
        if (prev == null && next != null) {
            prev = next;
        } else if (next == null && prev != null) {
            next = prev;
        }
    }
}

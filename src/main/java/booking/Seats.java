package booking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seats {

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    private String rowName;
    public String getRowName() { return rowName; }

    private long reserved;
    public long getReserved() {
        return reserved;
    }
    public void setReserved(long reserved) { this.reserved = reserved; }
}

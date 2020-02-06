package booking;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class RowConfig {

    public RowConfig() {
    }

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    private String rowName;
    public String getName() { return rowName; }
    public void setName(String rowName) { this.rowName = rowName; }

    private int capacity;
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}

package booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NamedQuery(name="getPriceForType",
        query="select p " +
                "from Price p " +
                "where p.type=:type")
public class Price {

    public Price() {
    }

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    private String type;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    private float price;
    public float getPrice() { return price; }

}

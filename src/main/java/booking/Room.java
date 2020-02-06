package booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "rows"})
public class Room {

    public Room() {
    }

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    private String title;
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @OneToMany
    private List<RowConfig> rows;
    public List<RowConfig> getRows() { return rows; }

}

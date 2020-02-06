package booking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NamedQueries(
        @NamedQuery(name="getScreeningsInPeriod",
                query="select s " +
                        "from Screening s " +
                        "where s.screeningStart between :start and :end " +
                        "order by s.title, s.screeningStart")
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "room"})
public class Screening {

    public Screening() {
    }

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    private String title;
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    private LocalDateTime screeningStart;
    public LocalDateTime getScreeningStart() { return screeningStart; }
    public void setScreeningStart(LocalDateTime screeningStart) { this.screeningStart = screeningStart; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id", nullable=false)
    private Room room;
    public Room getRoom() { return room; }

    @OneToMany
    @JoinColumn(name="seats_id")
    private List<Seats> reservations;
    public List<Seats> getReservations() {return reservations; }
}

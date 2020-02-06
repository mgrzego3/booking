package booking;

import java.util.List;
import java.util.Map;

public class ScreeningInfo {
    private Room room;
    public Room getRoom() { return room; }

    public void setRoom(Room room) {
        this.room = room;
    }

    private List<Seat> availableSeats;
    public List<Seat> getAvailableSeats() {
        return this.availableSeats;
    }
    public void setAvailableSeats(List<Seat> seats) {
         this.availableSeats = seats;
     };
}

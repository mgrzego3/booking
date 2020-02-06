package booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    ScreeningService screeningService;

    @Autowired
    EntityManager entityManager;

    private boolean at(long reservations, int offset) {
        long mask = ((long)1) << offset;
        long res = reservations & mask;
        if (res != 0)
            return true;
        else
            return false;
    }

    private long set(long reservations, int offset) {
        long mask = ((long)1) << offset;
        long res = reservations | mask;
        return res;
    }

    private boolean isAvailable(int index, long reservations, int capacity) {
        if (at(reservations, index))
            return false;
        if (index > 1) {
            if (at(reservations, index-2) && !at(reservations, index-1))
                return false;
        }

        if (index < capacity - 2) {
            if (!at(reservations, index+1) && at(reservations, index+2))
                return false;
        }

        return true;
    }

    public List<Seat> getAvailableSeats(long screeningId) {
        List<Seat> seats = new LinkedList<>();
        Screening screening = screeningService.getScreening(screeningId);
        Room room = screening.getRoom();
        List<Seats> reservations = screening.getReservations();
        List<RowConfig> rows = room.getRows();

        for (RowConfig r : rows) {
            String name = r.getName();
            int capacity = r.getCapacity();
            for (Seats s : reservations) {
                if (s.getRowName().equals(name)) {
                    long res =  s.getReserved();
                    for (int i = 0; i < capacity; ++i) {
                        if (isAvailable(i, res, capacity)){
                            seats.add(new Seat(name, i+1));
                        }
                    }

                    break;
                }
            }
        }

        return seats;
    }

    @Transactional
    public boolean reserveSeat(long screeningId, String row, int column) {
        Screening screening = screeningService.getScreening(screeningId);
        Room room = screening.getRoom();

        List<RowConfig> rows = room.getRows();

        for (RowConfig r : rows) {
            if (r.getName().equals(row)) {
                int capacity = r.getCapacity();
                List<Seats> reservations = screening.getReservations();

                for (Seats s : reservations) {
                    if (s.getRowName().equals(row)) {
                        long res = s.getReserved();
                        if (at(res, column-1))
                            return false;
                        long res2 = set(res, column-1);
                        s.setReserved(res2);
                        entityManager.persist(s);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}

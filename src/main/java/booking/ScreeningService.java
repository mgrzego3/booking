package booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {
    @Autowired
    EntityManager entityManager;

    public List<Screening> getScreenings(LocalDate date, LocalTime startTime, LocalTime endTime) {
        Query query = entityManager.createNamedQuery("getScreeningsInPeriod", Screening.class)
                .setParameter("start", LocalDateTime.of(date, startTime))
                .setParameter("end", LocalDateTime.of(date, endTime));

        return query.getResultList();
    }

    public Screening getScreening(long id) {
        return entityManager.find(Screening.class, id);
    }
}

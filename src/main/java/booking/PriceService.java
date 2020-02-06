package booking;

import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ScreeningService screeningService;

    public float getPriceForType(long id, String type) {
        Query query = entityManager.createNamedQuery("getPriceForType", Price.class)
                .setParameter("type", type);
        float amount = 0;
        Price price = (Price)query.getSingleResult();
        if (price != null)
            amount = price.getPrice();

        Screening screening = screeningService.getScreening(id);
        LocalDateTime dateTime = screening.getScreeningStart();
        if ((dateTime.getDayOfWeek() == DayOfWeek.FRIDAY && dateTime.getHour() >= 14)
            || dateTime.getDayOfWeek() == DayOfWeek.SATURDAY
            || dateTime.getDayOfWeek() == DayOfWeek.SUNDAY && dateTime.getHour() <= 11)
            amount += 4;
        return amount;
    }

    public boolean checkDiscount(String discountCode) {
        Discount disc = entityManager.find(Discount.class, discountCode);
        if (disc != null)
            return true;
        return false;
    }

}

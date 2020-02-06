package booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class WebController {

    class WrongDataException extends RuntimeException {
        WrongDataException() {
            super("Wrong data exception");
        }
    }

    @Autowired
    ScreeningService screeningService;

    @Autowired
    RoomService roomService;

    @Autowired
    PriceService priceService;

    @GetMapping(path = "/screenings")
    public List<Screening> getScreenings(@RequestParam(name="date", defaultValue = "-1")String dateString,
                                         @RequestParam(name="starttime", defaultValue = "-1")String startTimeString,
                                         @RequestParam(name="endtime", defaultValue = "-1")String endTimeString)
    {
        LocalDate date = null;
        LocalTime startTime = null;
        LocalTime endTime = null;
        try {
            date = LocalDate.parse(dateString);
            startTime = LocalTime.parse(startTimeString);
            endTime = LocalTime.parse(endTimeString);
        } catch (DateTimeParseException ex) {
            System.out.println(ex.getMessage());
            throw new WrongDataException();
        }

        if (!startTime.equals(endTime) && endTime.isBefore(startTime))
            throw new WrongDataException();

        return screeningService.getScreenings(date, startTime, endTime);
    }

    @GetMapping(path = "/screening/{idString}")
    public ScreeningInfo getScreening(@PathVariable String idString)
    {
        long id = -1;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            throw new WrongDataException();
        }
        ScreeningInfo info = new ScreeningInfo();
        Room room = screeningService.getScreening(id).getRoom();
        info.setRoom(room);
        List<Seat> seats = roomService.getAvailableSeats(id);
        info.setAvailableSeats(seats);

        return info;
    }

    @GetMapping(path = "/reserve/{idString}")
    public float getReservation(@PathVariable String idString,
                               @RequestParam(name="name", defaultValue = "")String name,
                               @RequestParam(name="surname", defaultValue = "")String surname,
                               @RequestParam(name="discount", defaultValue = "") String discountCode,
                               @RequestParam List<String> values) {
        long id = -1;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            throw new WrongDataException();
        }

        Pattern namePattern = Pattern.compile("^[A-ZĄŚĆŻŃŹ]{1}[a-ząśćżńż]{2,}");
        Matcher matcher = namePattern.matcher(name);
        if (!matcher.matches())
            throw new WrongDataException();

        Pattern surnamePattern = Pattern.compile("^[A-ZĄŚĆŻŃŹ]{1}[a-ząśćżńż]+(\\-[A-ZĄŚĆŻŃŹ]{1}[a-ząśćżńż]+)?");
        matcher = surnamePattern.matcher(surname);
        if (!matcher.matches())
            throw new WrongDataException();

        if (values.size() % 3 != 0)
            throw new WrongDataException();

        float amount = 0;
        Iterator<String> iter = values.iterator();
        while (iter.hasNext()) {
            String row = iter.next();
            String column = iter.next();
            String type = iter.next();
            int col;
            try {
                col = Integer.parseInt(column);
            } catch (NumberFormatException ex) {
                continue;
            }
            if (roomService.reserveSeat(id, row, col)) {
                amount += priceService.getPriceForType(id, type);
            }
        }

        boolean isDiscount = priceService.checkDiscount(discountCode);
        if (isDiscount)
            amount /= 2;

        return amount;
    }

}

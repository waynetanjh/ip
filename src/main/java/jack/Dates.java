package jack;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Dates {
    private Dates() {

    }

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm");

    private static final DateTimeFormatter DATE_ONLY =
            DateTimeFormatter.ISO_LOCAL_DATE;

    private static final DateTimeFormatter PRETTY_DATE =
            DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm");

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(PRETTY_DATE);
    }

    public static LocalDateTime parseDeadline(String date) {
        String s = date.trim();
        if (s.contains(" ")) {
            // assume dd/MM/uuuu HHmm
            return LocalDateTime.parse(s, TIME_FORMATTER);
        } else {
            // assume uuuu-MM-dd -> midnight
            return LocalDate.parse(s, DATE_ONLY).atStartOfDay();
        }
    }
}

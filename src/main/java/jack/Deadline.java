package jack;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime due;

    /**
     * Creates a new deadline task.
     *
     * @param description The description of deadline task
     * @param dueDay The due date/time of the deadline task
     */
    public Deadline(String description, String dueDay) {
        super(description);
        this.due = Dates.parseDeadline(dueDay);
    }

    public Deadline(String description, LocalDateTime due) {
        super(description);
        this.due = due;
    }

    /**
     * Returns the due date/time of the deadline task in a user-friendly format.
     *
     * @return The formatted due date/time string
     */
    public String getDueDay() {
        return "(by: " + Dates.format(this.due) + ")";
    }

    /**
     * Returns the due date/time of the deadline task in ISO 8601 format.
     *
     * @return The due date/time as an ISO 8601 string
     */
    public String getDueIso() {
        return due.toString(); // ISO_LOCAL_DATE_TIME
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + getDueDay();
    }
}

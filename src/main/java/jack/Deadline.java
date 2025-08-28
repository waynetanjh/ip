package jack;

import java.time.LocalDateTime;

public class Deadline extends Task{
    public LocalDateTime due;

    /**
        * Creates a new deadline task.
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

    public String getDueDay() {
        return "(by: " + this.due + ")";
    }

    public String getDueIso() {
        return due.toString(); // ISO_LOCAL_DATE_TIME
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + Dates.format(due);
    }
}

package jack;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new event task.
     *
     * @param description The description of the event
     * @param from The start time/date of the event
     * @param to The end time/date of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time/date of the event.
     * @return The event's start time/date
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time/date of the event.
     * @return The event's end time/date
     */
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

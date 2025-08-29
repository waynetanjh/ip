package jack;

public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Creates a new event task.
     *
     * @param description The description of the event
     * @param from The start time/date of the event
     * @param to The end time/date of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.startTime = from;
        this.endTime = to;
    }

    /**
     * Gets the start time/date of the event.
     * @return The event's start time/date
     */
    public String getFrom() {
        return startTime;
    }

    /**
     * Gets the end time/date of the event.
     * @return The event's end time/date
     */
    public String getTo() {
        return endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}

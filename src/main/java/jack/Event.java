package jack;

public class Event extends Task {
    private static final String eventMarker = "[E]";
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
        assert !from.trim().isEmpty() && !to.trim().isEmpty() : "Event times cannot be empty";
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
        return eventMarker + "[" + (isDone() ? "X" : " ") + "] " + getDescription() + " (from: " + startTime + " to: " + endTime + ")";
    }
}

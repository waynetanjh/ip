package jack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void createEvent_validParameters_success() {
        Event event = new Event("Team Meeting", "2023-09-16 14:00", "2023-09-16 15:00");
        assertEquals("Team Meeting", event.getDescription());
        assertEquals("2023-09-16 14:00", event.getFrom());
        assertEquals("2023-09-16 15:00", event.getTo());
    }

    @Test
    public void toString_correctFormat() {
        Event event = new Event("Team Meeting", "2023-09-16 14:00", "2023-09-16 15:00");
        assertEquals("[E][✗] Team Meeting (from: 2023-09-16 14:00 to: 2023-09-16 15:00)",
                event.toString());
    }

    @Test
    public void markAsDone_eventMarkedCorrectly() {
        Event event = new Event("Team Meeting", "2023-09-16 14:00", "2023-09-16 15:00");
        event.markAsDone();
        assertTrue(event.isDone());
        assertTrue(event.toString().contains("[✓]"));
    }

    @Test
    void createEvent_emptyTimes_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new Event(
                "Team Meeting", "", "2023-09-16 15:00"));
        assertThrows(AssertionError.class, () -> new Event(
                "Team Meeting", "2023-09-16 14:00", ""));
    }
}

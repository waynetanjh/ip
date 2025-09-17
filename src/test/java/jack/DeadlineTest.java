package jack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    @DisplayName("toString() starts with [D] tag")
    void toString_hasTypePrefixD() {
        Deadline deadlineTask = new Deadline("read book", "2/12/2019 1800") ;
        String s = deadlineTask.toString();
        assertNotNull(s, "toString() should not return null");
        assertTrue(s.startsWith("[D]"), "toString() should start with [D]");
    }

    @Test
    @DisplayName("check date format parsing")
    void checkDateFormat() {
        Deadline d = new Deadline("read book", "2/12/2019 1800");

        assertEquals("2019-12-02T18:00", d.getDueIso(),
                "Expected parsed ISO time to be 2019-12-02T18:00");
    }
}



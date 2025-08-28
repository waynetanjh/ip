package jack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    @Test
    @DisplayName("toString() starts with [T] tag")
    void toString_hasTypePrefixT() {
        Todo todo = new Todo("read book");
        String s = todo.toString();
        assertNotNull(s, "toString() should not return null");
        assertTrue(s.startsWith("[T]"), "toString() should start with [T]");
    }


}

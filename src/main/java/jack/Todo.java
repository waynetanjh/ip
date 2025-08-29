package jack;

public class Todo extends Task {
    /**
     * Create a new Todo task
     *
     * @param description The description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

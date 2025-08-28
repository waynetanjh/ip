package jack;

public class Todo extends Task {
    private String description;

    /**
     * Create a new Todo task
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}

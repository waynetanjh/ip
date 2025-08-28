package jack;

public class Todo extends Task {
    private String description;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}

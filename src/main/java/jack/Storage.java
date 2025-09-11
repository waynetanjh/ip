package jack;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class handles loading and saving of tasks to a file.
 */
public class Storage {
    private final Path file;

    /**
     * Creates a new Storage instance that manages the specified file.
     *
     * @param relativePath The relative path to the storage file
     */
    public Storage(String relativePath) {
        this.file = Paths.get(relativePath);
    }

    private void ensureFileExists() throws IOException {
        Path parent = file.getParent();
        if (parent != null && Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        if (Files.notExists(file)) {
            Files.createFile(file);
        }
    }

    /**
     * Saves the current list of tasks to the storage file.
     * Each task is encoded before saving.
     *
     * @param tasks The list of tasks to save
     */
    public void saveFile(List<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null";
        assert tasks.stream().allMatch(t -> t != null) : "Tasks list cannot contain null tasks";
        try {
            ensureFileExists();
            try (FileWriter fw = new FileWriter(file.toFile())) {
                for (Task t : tasks) {
                    fw.write(encode(t) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void loadFile(List<Task> tasks) throws IOException {
        ensureFileExists();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = decode(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Encode a Task object into a string for storage.
     * @param task
     * @return
     */
    public String encode(Task task) {
        String done = String.valueOf(task.isDone() ? 1 : 0);
        if (task instanceof Todo) {
            return String.join("|", "T", done, task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return String.join(" | ", "D", done, d.getDescription(), d.getDueIso());
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return String.join(" | ", "E", done, e.getDescription(), e.getFrom(), e.getTo());
        } else {
            // Fallback for any other jack.model.Task type
            return String.join(" | ", "X", done, task.toString());
        }
    }

    /**
     * Decodes a line from the storage file into a Task object.
     * @param line
     * @return
     */
    public Task decode(String line) {
        if (line == null) {
            return null;
        }
        String[] p = line.split("\\s*\\|\\s*");
        if (p.length < 3) {
            return null;
        }

        String type = p[0];
        String doneFlag = p[1];

        try {
            switch (type) {
            case "T": {
                // T | done | desc
                String desc = p[2];
                Todo t = new Todo(desc);
                if ("1".equals(doneFlag)) {
                    t.completed();
                }
                return t;
            }
            case "D": {
                // D | done | desc | by
                if (p.length < 4) {
                    return null;
                }
                String desc = p[2];
                String by = p[3];
                Deadline d = new Deadline(desc, by);
                if ("1".equals(doneFlag)) {
                    d.completed();
                }
                return d;
            }
            case "E": {
                // E | done | desc | from | to
                if (p.length < 5) {
                    return null;
                }
                String desc = p[2];
                String from = p[3];
                String to = p[4];
                Event e = new Event(desc, from, to);
                if ("1".equals(doneFlag)) {
                    e.completed();
                }
                return e;
            }
            default: {
                return null;
            }
            }
        } catch (Exception ex) {
            // Any parsing/constructor error => corrupted
            return null;
        }
    }
}

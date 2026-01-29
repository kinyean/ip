import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Returns a List of Tasks from stored data file.
     * If no stored data file, create new data file and return an Empty List
     * */
    public List<Task> load() throws IOException, KingException {
        Files.createDirectories(path.getParent()); //create directories if does not already exist

        //create file if it does not exist
        if (Files.notExists(path)) {
            Files.createFile(path);
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(path);
        List<Task> tasks = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            //tasks.add(Parser.parseTaskFromLine(line));
        }
        return tasks;
    }

    /**
     * Saves List of Tasks into Stored data file
     * */
    public void save(List<Task> tasks) throws IOException {
        Files.createDirectories(path.getParent());

        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toFileString());
        }

        Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}

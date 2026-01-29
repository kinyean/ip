package king.storage;

import king.exception.KingException;
import king.task.Deadline;
import king.task.Event;
import king.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    public void load_missingFile_createsAndReturnsEmptyList() throws Exception {
        Path saveFile = tempDir.resolve("data").resolve("king.txt");
        Storage storage = new Storage(saveFile.toString());

        List<Task> loaded = storage.load();
        assertNotNull(loaded);
        assertEquals(0, loaded.size());
        assertTrue(saveFile.toFile().exists());
    }

    @Test
    public void saveThenLoad_roundTrip_preservesTasksAndDoneStatus() throws Exception {
        Path saveFile = tempDir.resolve("data").resolve("king.txt");
        Storage storage = new Storage(saveFile.toString());

        Task t1 = new Task("read book");
        Deadline t2 = new Deadline("return book", LocalDateTime.of(2000, 1, 1, 18, 0));
        Event t3 = new Event("meeting",
                LocalDateTime.of(2000, 1, 2, 18, 0),
                LocalDateTime.of(2000, 1, 2, 20, 0));

        t2.mark(); // done
        // leave others undone

        List<Task> toSave = List.of(t1, t2, t3);
        storage.save(toSave);

        List<Task> loaded = storage.load();
        assertEquals(3, loaded.size());

        // Instead of equals(), compare stable serialization output:
        assertEquals(toSave.get(0).toFileString(), loaded.get(0).toFileString());
        assertEquals(toSave.get(1).toFileString(), loaded.get(1).toFileString());
        assertEquals(toSave.get(2).toFileString(), loaded.get(2).toFileString());
    }
}

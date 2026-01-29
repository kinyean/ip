package king.parser;

import king.command.Command;
import king.exception.KingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_deadlineValidFormat_success() throws Exception {
        Command c = Parser.parse("deadline dl /by 01/01/2000 1800");
        assertNotNull(c);
        // If you have concrete class names:
        assertEquals("DeadlineCommand", c.getClass().getSimpleName());
    }

    @Test
    public void parse_deadlineMissingBy_throws() {
        KingException e = assertThrows(KingException.class,
                () -> Parser.parse("deadline dl 01/01/2000 1800"));
        assertTrue(e.getMessage().toLowerCase().contains("deadline format"));
    }

    @Test
    public void parse_deadlineBadDate_throws() {
        KingException e = assertThrows(KingException.class,
                () -> Parser.parse("deadline dl /by 2000-01-01 1800")); // wrong format
        assertTrue(e.getMessage().toLowerCase().contains("invalid date"));
    }

    @Test
    public void parse_eventEndBeforeStart_throws() {
        KingException e = assertThrows(KingException.class, () ->
                Parser.parse("event meet /from 02/01/2000 1800 /to 01/01/2000 1800"));
        assertTrue(e.getMessage().toLowerCase().contains("end time"));
    }

    @Test
    public void parse_markMissingIndex_throws() {
        KingException e = assertThrows(KingException.class,
                () -> Parser.parse("mark"));
        assertTrue(e.getMessage().toLowerCase().contains("missing"));
    }

    @Test
    public void parse_unknownCommand_throws() {
        KingException e = assertThrows(KingException.class,
                () -> Parser.parse("hello"));
        assertTrue(e.getMessage().toLowerCase().contains("no such"));
    }
}

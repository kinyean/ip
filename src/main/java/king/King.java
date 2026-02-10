package king;

import java.io.IOException;

import king.command.Command;
import king.command.CommandResult;
import king.exception.KingException;
import king.parser.Parser;
import king.storage.Storage;
import king.task.TaskList;
import king.ui.Ui;

/**
 * A Class that creates a chatbot in the terminal
 */
public class King {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public King() {
        this("data/king.txt");
    }
    /**
     * Constructor that takes in a filePath to store data
     */
    public King(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    /**
     * Processes a user input string and returns the result of executing the corresponding command.
     *
     * @param input User input string
     * @return Result of command execution
     */
    public CommandResult getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (Exception e) {
            return new CommandResult(e.getMessage(), false);
        }
    }


    /**
     * Runs the chatbot
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String input = ui.readCommand();
                CommandResult result = getResponse(input);
                ui.showMessage(result.feedback);
                if (result.isExit) {
                    ui.showBye();
                    break;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new King("data/king.txt").run();
    }
}


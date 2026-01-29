import java.io.IOException;

public class King {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input); // parse USER command
                command.execute(tasks, ui, storage);

                if (command.isExit()) {
                    ui.showBye();
                    break;
                }
            } catch (IOException | KingException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new King("data/king.txt").run();
    }
}


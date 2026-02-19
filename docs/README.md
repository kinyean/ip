# 📘 King Task Manager

**King** is a lightweight task management chatbot with a clean JavaFX GUI.  
It helps you keep track of your tasks quickly using simple text commands.

---

## 🚀 Getting Started

### Requirements
- Java **17** installed

You can check your Java version by running:

```bash
java -version
```

Make sure it shows version 17.

---

### Running the Application

1. Download `King.jar`
2. Open a terminal in the same folder as the JAR file
3. Run:

```bash
java -jar King.jar
```

The King GUI window will launch.

---

## ✨ Features

King supports three types of tasks:

| Type | Description |
|------|-------------|
| **Todo** | A task without a date |
| **Deadline** | A task with a due date/time |
| **Event** | A task with a start and end time |

---

## 📌 Commands

### ➤ Add a Todo

```
todo <description>
```

**Example**
```
todo read book
```

---

### ➤ Add a Deadline

```
deadline <description> /by <yyyy-mm-dd HH:mm>
```

**Example**
```
deadline submit report /by 2026-03-01 2359
```

---

### ➤ Add an Event

```
event <description> /from <yyyy-mm-dd HH:mm> /to <yyyy-mm-dd HH:mm>
```

**Example**
```
event project meeting /from 2026-03-01 1000 /to 2026-03-01 1200
```

---

### ➤ View all tasks

```
list
```

Displays all tasks currently stored.

---

### ➤ Mark a task as done

```
mark <task number>
```

---

### ➤ Unmark a task

```
unmark <task number>
```

---

### ➤ Delete a task

```
delete <task number>
```

---

### ➤ Find tasks

```
find <keyword>
```

**Example**
```
find report
```

Displays all tasks containing the keyword.

---

### ➤ Exit the application

```
bye
```

Closes the application window.

---

## 🔍 Duplicate Detection (Extension)

King prevents adding duplicate tasks.

A task is considered a duplicate if:

- It has the same description (case-insensitive)
- And the same date/time (for deadlines/events)

**Example**

```
todo read book
todo read book
```

**Result**

```
Duplicate task detected:
[T][ ] read book
```

---

## 💾 Data Storage

All tasks are stored locally in:

```
data/king.txt
```

- Data is automatically saved after every command
- Tasks are loaded automatically when the app starts
- If the file does not exist, it will be created automatically

---

## 📋 Command Summary

| Action | Command |
|--------|--------|
| Add Todo | `todo <description>` |
| Add Deadline | `deadline <desc> /by <time>` |
| Add Event | `event <desc> /from <time> /to <time>` |
| List tasks | `list` |
| Mark task | `mark <index>` |
| Unmark task | `unmark <index>` |
| Delete task | `delete <index>` |
| Find tasks | `find <keyword>` |
| Exit | `bye` |

---

## ❓ FAQ

### Q: My command does not work

Check:
- Command spelling
- Command format
- Spacing between keywords

---

### Q: Where is my data saved?

In the file:

```
data/king.txt
```

---

### Q: Can I edit the data file manually?

Yes, but it is recommended to use the application to avoid formatting errors.

---

## ⚠️ Known Issues

- Date/time must follow the format `yyyy-mm-dd HH:mm`
- Incorrect formats will produce an error message
- Task numbers must refer to an existing task in the list

---

## 🛠 Built With

- Java 17
- JavaFX
- Gradle

---

## 🙌 Acknowledgements

- Developed as part of the **CS2103T Individual Project**

---

## 👤 Author

**Kingsley Yong**

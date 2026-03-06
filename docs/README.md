# Neeraj Bot User Guide

Neeraj Bot is a simple task management chatbot that helps you keep track of your todos, deadlines, and events.

## Quick Start

1. Download `ip.jar` from the latest release
2. Open terminal and run: `java -jar ip.jar`
3. Start adding tasks!

## Features

### Adding a Todo
Add a simple task without any date/time.

Format: `todo DESCRIPTION`

Example: `todo read book`

Expected output:
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

### Adding a Deadline
Add a task with a deadline.

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline return book /by Friday`

Expected output:
```
Got it. I've added this task:
  [D][ ] return book (by: Friday)
Now you have 2 tasks in the list.
```

### Adding an Event
Add a task with start and end times.

Format: `event DESCRIPTION /from START /to END`

Example: `event project meeting /from 2pm /to 4pm`

Expected output:
```
Got it. I've added this task:
  [E][ ] project meeting (from: 2pm to: 4pm)
Now you have 3 tasks in the list.
```

### Listing All Tasks
View all your tasks.

Format: `list`

Expected output:
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Friday)
3.[E][ ] project meeting (from: 2pm to: 4pm)
```

### Marking a Task as Done
Mark a task as completed.

Format: `mark TASK_NUMBER`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
  [T][X] read book
```

### Unmarking a Task
Mark a task as not done.

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

### Deleting a Task
Remove a task from your list.

Format: `delete TASK_NUMBER`

Example: `delete 2`

Expected output:
```
Noted. I've removed this task:
  [D][ ] return book (by: Friday)
Now you have 2 tasks in the list.
```

### Finding Tasks
Search for tasks containing a keyword.

Format: `find KEYWORD`

Example: `find book`

Expected output:
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Friday)
```

### Exiting the Program
Exit the chatbot.

Format: `bye`

Expected output:
```
Bye. Hope to see you again soon!
```

## Notes

- Tasks are automatically saved to `./data/neeraj.txt`
- Your tasks will be loaded automatically when you restart the program
- Task numbers in your list may change after deleting tasks
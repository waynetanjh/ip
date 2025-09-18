# Jack User Guide

![Jack](../src/main/resources/images/Ui.png)

Jack Sparrow is a helpful assistant that can keep track of all your tasks and deadlines.
It is designed to help you stay organized and manage your time effectively.

## Features

1. Task Management
   - Add different types of tasks (todo, deadline, event)
   - Mark tasks as done/undone
   - List all tasks
   - Delete tasks

## Commands

### 1. Adding Tasks

#### Adding a todo
Adds a simple todo task to your list.

Format: `todo DESCRIPTION`

Example: `todo read book`

Output:
```
Got it. I've added this task.
Here are the tasks in your list:
1.[T][ ] read book
```

#### Adding a deadline
Adds a task with a specific deadline.

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline return book /by 2/12/2019 1800`

Output:
```
Got it. I've added this task.
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 2 2019 18:00)
```

#### Adding an event
Adds an event with start and end times.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example: `event team meeting /from 2023-09-21 14:00 /to 2023-09-21 15:00`

Output:
```
Got it. I've added this task.
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 2 2019 18:00)
3.[E][ ] team meeting (from: 2023-09-21 14:00 to: 2023-09-21 15:00)
```

### 2. Managing Tasks

#### Listing all tasks
Shows all tasks in your list.

Format: `list`

Output:
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 2 2019 18:00)
3.[E][ ] team meeting (from: 2023-09-21 14:00 to: 2023-09-21 15:00)
```

#### Marking a task as done
Marks a task as completed.

Format: `mark INDEX`

Example: `mark 1`

Output:
```
Nice! I've marked this task as done.
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: Dec 2 2019 18:00)
3.[E][ ] team meeting (from: 2023-09-21 14:00 to: 2023-09-21 15:00)
```

#### Unmarking a task
Marks a completed task as not done.

Format: `unmark INDEX`

Example: `unmark 1`

Output:
```
OK, I've marked this task as not done yet.
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 2 2019 18:00)
3.[E][ ] team meeting (from: 2023-09-21 14:00 to: 2023-09-21 15:00)
```

#### Deleting a task
Removes a task from your list.

Format: `delete INDEX`

Example: `delete 1`

Output:
```
Noted. I've removed this task.
Here are the tasks in your list:
1.[D][ ] return book (by: Dec 2 2019 18:00)
2.[E][ ] team meeting (from: 2023-09-21 14:00 to: 2023-09-21 15:00)
```

### Finding tasks 
Searches for tasks containing a specific keyword.

Format: `find KEYWORD`

Example: `find book`

Output:
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 2 2019 18:00)
```

### Getting Help

#### Showing available commands
Displays a list of all available commands.

Format: `help`

Output:
```
Commands available:
  list - show all tasks
  todo <description> - add a todo
  deadline <description> /by <date> - add a deadline
  event <description> /from <start-date> /to <end-date> - add an event
  mark <task number> - mark task as done
  unmark <task number> - mark task as not done
  delete <task number> - delete a task
  find <keyword> - find tasks containing keyword
  help - show this help message
  bye - exit the program
```

### Saving and Exiting

#### Exiting the program
Saves all tasks and exits the program.

Format: `bye`

Output:
```
Bye. Hope to see you again soon!
```

## Data Storage
- Your tasks are automatically saved after each command
- Tasks are stored in the `data/jack.txt` file
- The data will be loaded automatically when you restart the program

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
Got it. I've added this task:
  [T][X] read book
Now you have 1 task in the list.
```

#### Adding a deadline
Adds a task with a specific deadline.

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline return book /by 2/12/2019 1800`

Output:
```
Got it. I've added this task:
  [D][ ] return book (by: Dec 2 2019 18:00)
Now you have 2 tasks in the list.
```

#### Adding an event
Adds an event with start and end times.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example: `event team meeting /from 2023-09-21 14:00 /to 2023-09-21 15:00`

Output:
```
Got it. I've added this task:
  [E][✗] team meeting (from: 2023-09-21 14:00 to: 2023-09-21 15:00)
Now you have 3 tasks in the list.
```

### 2. Managing Tasks

#### Listing all tasks
Shows all tasks in your list.

Format: `list`

Output:
```
Here are the tasks in your list:
1. [T][X] read book
2. [D][X] submit report (by: 2023-09-20 23:59)
3. [E][X] team meeting (from: 2023-09-21 14:00 to: 2023-09-21 15:00)
```

#### Marking a task as done
Marks a task as completed.

Format: `mark INDEX`

Example: `mark 1`

Output:
```
Nice! I've marked this task as done:
 [T][X] read book
```

#### Unmarking a task
Marks a completed task as not done.

Format: `unmark INDEX`

Example: `unmark 1`

Output:
```
OK, I've marked this task as not done yet:
 [T][ ] read book
```

#### Deleting a task
Removes a task from your list.

Format: `delete INDEX`

Example: `delete 1`

Output:
```
Noted. I've removed this task:
  [T][ ] read book
Now you have 2 tasks in the list.
```

### 3. Saving and Exiting

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

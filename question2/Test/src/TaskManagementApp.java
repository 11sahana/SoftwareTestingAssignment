import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private boolean completed;

    public Task(String title, String description, String dueDate, String priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false; // Default status
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n"
                + "Description: " + description + "\n"
                + "Due Date: " + dueDate + "\n"
                + "Priority: " + priority + "\n"
                + "Status: " + (completed ? "Completed" : "Not Completed") + "\n";
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void editTask(int index, Task updatedTask) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, updatedTask);
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskAsComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
            tasks.set(index, task);
            System.out.println("Task marked as complete.");
        } else {
            System.out.println("Invalid task index.");
        }
    }
}

public class TaskManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nTask Management Menu:");
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Edit task");
            System.out.println("4. Delete task");
            System.out.println("5. Mark task as complete");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    taskManager.displayTasks();
                    break;
                case 2:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date: ");
                    String dueDate = scanner.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    Task newTask = new Task(title, description, dueDate, priority);
                    taskManager.addTask(newTask);
                    System.out.println("Task added successfully.");
                    break;
                case 3:
                    System.out.print("Enter index of task to edit: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new due date: ");
                    String newDueDate = scanner.nextLine();
                    System.out.print("Enter new priority (High/Medium/Low): ");
                    String newPriority = scanner.nextLine();
                    Task updatedTask = new Task(newTitle, newDescription, newDueDate, newPriority);
                    taskManager.editTask(editIndex, updatedTask);
                    break;
                case 4:
                    System.out.print("Enter index of task to delete: ");
                    int deleteIndex = scanner.nextInt();
                    taskManager.deleteTask(deleteIndex);
                    break;
                case 5:
                    System.out.print("Enter index of task to mark as complete: ");
                    int completeIndex = scanner.nextInt();
                    taskManager.markTaskAsComplete(completeIndex);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("Exiting Task Management App. Goodbye!");
        scanner.close();
    }
}
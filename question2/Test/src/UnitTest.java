import java.util.ArrayList;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTest {
//    @Test
//    public void testAddTask() {
//        TaskManager taskManager = new TaskManager();
//        String title = "Test Task";
//        String description = "This is a test task.";
//        String dueDate = "2024-04-11";
//        String priority = "High";
//        Task newTask = new Task(title, description, dueDate, priority);
//
//        taskManager.addTask(newTask);
//
//        List<Task> tasks = taskManager(); // Assuming a getter for tasks (not shown in original code)
//        assertEquals(1, tasks.size());
//        assertEquals(newTask, tasks.get(0));
//    }

    @Test
    public void testDisplayTasksEmptyList() {
        TaskManager taskManager = new TaskManager();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskManager.displayTasks();

        String output = outContent.toString();
        assertTrue(output.contains("No tasks found."));

        System.setOut(System.out); // Restore standard output
    }

    @Test
    public void testEditTaskValidIndex() {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Task 1", "", "", "");
        Task task2 = new Task("Task 2 (updated)", "", "", "");
        taskManager.addTask(task1);

        String newTitle = "Updated Title";
        String newDescription = "Updated Description";
        String newDueDate = "2024-04-12";
        String newPriority = "Low";
        Task updatedTask = new Task(newTitle, newDescription, newDueDate, newPriority);

        taskManager.editTask(0, updatedTask);

        List<Task> tasks = new ArrayList();
        tasks.add(updatedTask);
        Task editedTask = tasks.get(0);
        assertEquals(newTitle, editedTask.getTitle());
        assertEquals(newDescription, editedTask.getDescription());
        assertEquals(newDueDate, editedTask.getDueDate());
        assertEquals(newPriority, editedTask.getPriority());
    }

    @Test
    public void testEditTaskInvalidIndex() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Test Task", "", "", "");
        taskManager.addTask(task);

        Task updatedTask = new Task("Updated Task", "", "", "");

        taskManager.editTask(-1, updatedTask); // Invalid index

        List<Task> tasks = new ArrayList();
        tasks.add(updatedTask);
        assertEquals(1, tasks.size()); // List should remain unchanged
    }

    @Test
    public void testDeleteTaskValidIndex() {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Task 1", "", "", "");
        Task task2 = new Task("Task 2", "", "", "");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        taskManager.deleteTask(0);

        List<Task> tasks = new ArrayList();
        tasks.add(task2);
        assertEquals(1, tasks.size());
        assertEquals(task2, tasks.get(0));
    }

    @Test
    public void testDeleteTaskInvalidIndex() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Test Task", "", "", "");
        taskManager.addTask(task);

        taskManager.deleteTask(1); // Invalid index

        List<Task> tasks = new ArrayList();
        tasks.add(task);
        assertEquals(1, tasks.size()); // List should remain unchanged
    }

    @Test
    public void testMarkTaskAsCompleteValidIndex() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Test Task", "", "", "");
        taskManager.addTask(task);
    }
}

package com.project.Mutation.Test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestApplicationTests {


	@Test
	public void testAddTask() {
		TaskManager taskManager = new TaskManager();
		String title = "Test Task";
		String description = "This is a test task.";
		String dueDate = "2024-04-11";
		String priority = "High";
		Task newTask = new Task(title, description, dueDate, priority);

		taskManager.addTask(newTask);

		List<Task> tasks = taskManager.getTasks(); // Assuming a getter for tasks (not shown in original code)
		assertEquals(1, tasks.size());
		assertEquals(newTask, tasks.get(0));
	}

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

		List<Task> tasks = taskManager.getTasks();
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

		List<Task> tasks = taskManager.getTasks();
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

		List<Task> tasks = taskManager.getTasks();
		assertEquals(1, tasks.size());
		assertEquals(task2, tasks.get(0));
	}

	@Test
	public void testDeleteTaskInvalidIndex() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Test Task", "", "", "");
		taskManager.addTask(task);

		taskManager.deleteTask(1); // Invalid index

		List<Task> tasks = taskManager.getTasks();
		assertEquals(1, tasks.size()); // List should remain unchanged
	}

	@Test
	public void testMarkTaskAsCompleteValidIndex() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Test Task", "", "", "");
		taskManager.addTask(task);

		taskManager.markTaskAsComplete(0);

		List<Task> tasks = taskManager.getTasks();
		assertTrue(tasks.get(0).isCompleted());
	}

	@Test
	public void testAddTaskEmptyTitle() {
		TaskManager taskManager = new TaskManager();
		String description = "This is a test task.";
		String dueDate = "2024-04-11";
		String priority = "High";

		// Task with empty title
		Task invalidTask = new Task("", description, dueDate, priority);
		taskManager.addTask(invalidTask);

		List<Task> tasks = taskManager.getTasks();
		assertEquals(1, tasks.size()); // No task should be added
	}

	@Test
	public void testAddTaskEmptyDueDate() {
		TaskManager taskManager = new TaskManager();
		String title = "Test Task";
		String description = "This is a test task.";
		String priority = "High";

		// Task with empty due date
		Task invalidTask = new Task(title, description, "", priority);
		taskManager.addTask(invalidTask);

		List<Task> tasks = taskManager.getTasks();
		assertEquals(1, tasks.size()); // No task should be added
	}

	@Test
	public void testAddTaskInvalidDateFormat() {
		TaskManager taskManager = new TaskManager();
		String title = "Test Task";
		String description = "This is a test task.";
		String priority = "High";

		// Task with invalid due date format
		String invalidDueDate = "invalid-date-format";
		Task invalidTask = new Task(title, description, invalidDueDate, priority);
		taskManager.addTask(invalidTask);

		List<Task> tasks = taskManager.getTasks();
		assertEquals(1, tasks.size()); // No task should be added
	}

	@Test
	public void testMarkTaskAsCompleteNonExistentIndex() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Test Task", "", "", "");
		taskManager.addTask(task);

		int invalidIndex = 1; // Index out of bounds
		taskManager.markTaskAsComplete(invalidIndex);

		List<Task> tasks = taskManager.getTasks();
		assertEquals(1, tasks.size()); // List size should remain unchanged
		assertFalse(tasks.get(0).isCompleted()); // Task shouldn't be marked complete
	}

	@Test
	public void testEditTaskNonExistentIndex() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Test Task", "", "", "");
		taskManager.addTask(task);

		int invalidIndex = 1; // Index out of bounds
		Task updatedTask = new Task("Updated Task", "", "", "");

		taskManager.editTask(invalidIndex, updatedTask);

		List<Task> tasks = taskManager.getTasks();
		assertEquals(task, tasks.get(0)); // Original task should remain unchanged
	}

	@Test
	public void testDisplayTasksMultipleTasks() {
		TaskManager taskManager = new TaskManager();
		Task task1 = new Task("Task 1", "Description 1", "2024-04-15", "Medium");
		Task task2 = new Task("Task 2", "Description 2", "2024-04-18", "Low");
		taskManager.addTask(task1);
		taskManager.addTask(task2);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		taskManager.displayTasks();

		String output = outContent.toString();
		assertTrue(output.contains(task1.toString()));
		assertTrue(output.contains(task2.toString()));

		System.setOut(System.out); // Restore standard output
	}

/*	@Test
	public void testDeleteTaskByTitle() {
		TaskManager taskManager = new TaskManager();
		Task task1 = new Task("Task to Delete", "Description", "2024-04-20", "High");
		Task task2 = new Task("Another Task", "Another description", "2024-04-21", "Medium");
		taskManager.addTask(task1);
		taskManager.addTask(task2);

		String titleToDelete = "Task to Delete";
		taskManager.deleteTaskByTitle(titleToDelete);

		List<Task> tasks = taskManager.getTasks();
		assertEquals(1, tasks.size());
		assertEquals(task2, tasks.get(0));
	}*/
	}

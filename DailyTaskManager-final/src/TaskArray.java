import java.util.Scanner;
import java.util.Stack;

public class TaskArray {
    // Predefined daily tasks
    private String[] tasks = {
        "Check email",
        "Attend lecture", 
        "Exercise", 
        "Read book", 
        "Write report"
    };

    // Display all tasks in the array
    public void displayTasks() {
        System.out.println(ANSIColors.PURPLE_BOLD_BRIGHT + "\nTask List (Array):" + ANSIColors.RESET);
        for (int i = 0; i < tasks.length; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    // Update a task in the array
    public void updateTask(Scanner scanner) {
        displayTasks();

        int index = -1;
        while (true) {
            System.out.print("Enter task number to update: ");
            if (scanner.hasNextInt()) {
                index = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline
                if (index >= 0 && index < tasks.length) break; // Valid input
            } else {
                scanner.nextLine(); // Clear invalid input
            }
            System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Error: Please enter a valid task number." + ANSIColors.RESET);
        }

        // Ensure user enters a valid task / getting the new task description
        String newTask;
        do {
            System.out.print("Enter new task: ");
            newTask = scanner.nextLine().trim();
            if (newTask.isEmpty()) {
                System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Error: Task description cannot be empty." + ANSIColors.RESET);
            }
        } while (newTask.isEmpty());

        tasks[index] = newTask;
        System.out.println(ANSIColors.GREEN_BOLD_BRIGHT + "Task updated successfully." + ANSIColors.RESET);
    }

    // Mark a task as completed in the array
    public void completeTask(Stack<String> undoStack, Scanner scanner) {
        displayTasks();

        int index = -1;
        while (true) {
            System.out.print("Enter task number to mark as completed: ");
            if (scanner.hasNextInt()) {
                index = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline
                if (index >= 0 && index < tasks.length) break; // Valid input
            } else {
                scanner.nextLine(); // Clear invalid input
            }
            System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Error: Please enter a valid task number." + ANSIColors.RESET);
        }

        // Checking if the task is already marked as completed
        if (tasks[index].contains("(Completed)")) {
            System.out.println(ANSIColors.YELLOW_BOLD + "Warning: Task is already marked as completed." + ANSIColors.RESET);
            return;
        }

        // Marking the task as completed
        undoStack.push(tasks[index]); // Save original task for undo
        tasks[index] = ANSIColors.GREEN_BOLD_BRIGHT + tasks[index] + " (Completed)" + ANSIColors.RESET;
        System.out.println(ANSIColors.GREEN_BOLD_BRIGHT + "Task marked as completed." + ANSIColors.RESET);
    }

    // Undoing a completed task in the array
    public void undoTask(Stack<String> undoStack) {
        if (undoStack.isEmpty()) {
            System.out.println(ANSIColors.YELLOW_BOLD + "No completed tasks to undo." + ANSIColors.RESET);
            return;
        }

        String undoneTask = undoStack.pop(); // Get the most recent completed task

        // Find and restore only the last completed task
        for (int i = tasks.length - 1; i >= 0; i--) { // Iterate in reverse
            if (tasks[i].contains("(Completed)")) {
                tasks[i] = undoneTask; // Restore original task name
                System.out.println(ANSIColors.GREEN_BOLD_BRIGHT + "Undo successful! Task restored." + ANSIColors.RESET);
                return;
            }
        }

        // If no completed task found (shouldn't happen but added for safety)
        System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Error: No tasks found to undo." + ANSIColors.RESET);
    }
}

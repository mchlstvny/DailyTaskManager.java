import java.util.Scanner;
import java.util.Stack;

public class TaskLinkedList {
    private Node head;

    // Node class (Inner Class)
    private static class Node {
        String task;
        Node next;

        Node(String task) {
            this.task = task;
            this.next = null;
        }
    }

    // Add a new task to the linked list
    public void addTask(Scanner scanner) {
        System.out.print("Enter new task: ");
        String newTask = scanner.nextLine();
        Node newNode = new Node(newTask);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println(ANSIColors.GREEN_BOLD_BRIGHT + "Task added successfully." + ANSIColors.RESET);
    }

    // Remove a task from the linked list
    public void removeTask(Scanner scanner) {
        System.out.print("Enter task to remove: ");
        String taskToRemove = scanner.nextLine();
        if (head == null) {
            System.out.println(ANSIColors.RED_BOLD_BRIGHT + "No tasks to remove." + ANSIColors.RESET);
            return;
        }
        if (head.task.equals(taskToRemove)) {
            head = head.next;
            System.out.println(ANSIColors.GREEN_BOLD_BRIGHT + "Task removed." + ANSIColors.RESET);
            return;
        }
        Node current = head;
        while (current.next != null && !current.next.task.equals(taskToRemove)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            System.out.println(ANSIColors.GREEN_BOLD_BRIGHT + "Task removed." + ANSIColors.RESET);
        } else {
            System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Task not found." + ANSIColors.RESET);
        }
    }

    // Display all tasks in the linked list
    public void displayTasks() {
        if (head == null) {
            System.out.println(ANSIColors.YELLOW_BOLD_BRIGHT + "No tasks available." + ANSIColors.RESET);
            return;
        }
        System.out.println(ANSIColors.CYAN_BOLD_BRIGHT + "\nTask List (Linked List):" + ANSIColors.RESET);
        Node temp = head;
        while (temp != null) {
            System.out.println("- " + temp.task);
            temp = temp.next;
        }
    }

    // Mark a task as completed in the linked list
    public void completeTask(Stack<String> undoStack, Scanner scanner) {
        System.out.print("Enter task to mark as completed: ");
        String completedTask = scanner.nextLine();
        Node temp = head;
        while (temp != null) {
            if (temp.task.equals(completedTask)) {
                undoStack.push(temp.task);
                temp.task = ANSIColors.GREEN_BOLD_BRIGHT + temp.task + " (Completed)" + ANSIColors.RESET;
                System.out.println(ANSIColors.GREEN_BOLD_BRIGHT + "Task marked as completed." + ANSIColors.RESET);
                return;
            }
            temp = temp.next;
        }
        System.out.println(ANSIColors.YELLOW_BOLD_BRIGHT + "Task not found." + ANSIColors.RESET);
    }

    // Undo the most recent completed task in the linked list
    public void undoTask(Stack<String> undoStack) {
        if (!undoStack.isEmpty()) {
            String undoneTask = undoStack.pop(); // Get the most recent completed task
    
            // Reverse search to restore the most recently modified task
            Node temp = head;
            Node lastCompleted = null;
    
            while (temp != null) {
                if (temp.task.contains("(Completed)")) {
                    lastCompleted = temp; // Keep track of the last completed task
                }
                temp = temp.next;
            }
    
            if (lastCompleted != null) {
                lastCompleted.task = undoneTask; // Restore the last completed task
                System.out.println(ANSIColors.GREEN_BOLD_BRIGHT + "Undo successful! Task restored." + ANSIColors.RESET);
            } else {
                System.out.println(ANSIColors.YELLOW_BOLD_BRIGHT + "No completed tasks to undo." + ANSIColors.RESET);
            }
        }
    }   
}

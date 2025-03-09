import java.util.Scanner;
import java.util.Stack;

// Task Manager Class
public class DailyTaskManager {

    // Global Variables
    private static Scanner scanner = new Scanner(System.in);
    private static TaskArray taskArray = new TaskArray();
    private static TaskLinkedList taskLinkedList = new TaskLinkedList();
    private static Stack<String> undoStack = new Stack<>();

    // Welcome Message and Main Menu
    public static void main(String[] args) {
        System.out.println(ANSIColors.YELLOW_BOLD_BRIGHT + "-----------------------------");
        System.out.println("WELCOME TO DAILY TASK MANAGER");
        System.out.println("-----------------------------" + ANSIColors.RESET);
        
        // Main Menu
        while (true) {
            try {
                System.out.println(ANSIColors.PURPLE_BOLD_BRIGHT + "\nChoose Task Management Method:" + ANSIColors.RESET);
                System.out.println("1. Use Array");
                System.out.println("2. Use Linked List");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                int choice = getValidIntInput();
                if (choice == 1) manageArrayTasks();
                else if (choice == 2) manageLinkedListTasks();
                else if (choice == 3) {
                    Exit.exitProgram(); // Call exit function from separate file
                    break;
                } else {
                    System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Invalid choice! Try again." + ANSIColors.RESET);
                }
            } catch (Exception e) {
                System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Error: Something went wrong. Please try again." + ANSIColors.RESET);
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Task Array Menu
    private static void manageArrayTasks() {
        while (true) {
            try {
                System.out.println(ANSIColors.BLUE_BOLD_BRIGHT + "\nTask Array Menu:" + ANSIColors.RESET);
                System.out.println("1. View Tasks");
                System.out.println("2. Update Task");
                System.out.println("3. Mark Task as Completed");
                System.out.println("4. Undo Completed Task");
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter choice: ");

                int choice = getValidIntInput();
                switch (choice) {
                    case 1: taskArray.displayTasks(); break;
                    case 2: taskArray.updateTask(scanner); break;
                    case 3: taskArray.completeTask(undoStack, scanner); break;
                    case 4: taskArray.undoTask(undoStack); break;
                    case 5: return;
                    default: System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Invalid choice! Try again." + ANSIColors.RESET);
                }
            } catch (Exception e) {
                System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Error: Something went wrong. Please try again." + ANSIColors.RESET);
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Task Linked List Menu
    private static void manageLinkedListTasks() {
        while (true) {
            try {
                System.out.println(ANSIColors.BLUE_BOLD_BRIGHT + "\nTask Linked List Menu:" + ANSIColors.RESET);
                System.out.println("1. View Task");
                System.out.println("2. Add Task");
                System.out.println("3. Remove Task");
                System.out.println("4. Mark Task as Completed");
                System.out.println("5. Undo Completed Task");
                System.out.println("6. Back to Main Menu");
                System.out.print("Enter choice: ");

                int choice = getValidIntInput();
                switch (choice) {
                    case 1: taskLinkedList.displayTasks(); break;
                    case 2: taskLinkedList.addTask(scanner); break;
                    case 3: taskLinkedList.removeTask(scanner); break;
                    case 4: taskLinkedList.completeTask(undoStack, scanner); break;
                    case 5: taskLinkedList.undoTask(undoStack); break;
                    case 6: return;
                    default: System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Invalid choice! Try again." + ANSIColors.RESET);
                }
            } catch (Exception e) {
                System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Error: Something went wrong. Please try again." + ANSIColors.RESET);
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Validate Integer Input
    private static int getValidIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Invalid input! Please enter a number." + ANSIColors.RESET);
            scanner.nextLine(); // Clear the invalid input
            System.out.print("Enter choice: ");
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return input;
    }
}

public class Exit {
    public static void exitProgram() {
        try {
            System.out.print(ANSIColors.RED_BOLD_BRIGHT + "\nExiting Daily Task Manager" + ANSIColors.RESET);
            
            // Loading effect with moving dots
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000); // Pause for 1000ms
                System.out.print(ANSIColors.RED_BOLD_BRIGHT + "." + ANSIColors.RESET);
            }

            } catch (InterruptedException e) {
            System.out.println(ANSIColors.RED_BOLD_BRIGHT + "Error while exiting." + ANSIColors.RESET);
        }
    }
}

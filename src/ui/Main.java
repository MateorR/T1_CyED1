package ui;



import model.Controller;

import java.util.Scanner;

public class Main {

    public static final Controller controller = new Controller();
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int option;
        do {
            menu();
            System.out.println("Enter an option: ");
            option = input.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Enter the title: ");
                    String title = input.next();
                    System.out.println("Enter the description: ");
                    String description = input.next();
                    System.out.println("Enter the due date: ");
                    String dueDate = input.next();
                    System.out.println("Enter the priority: ");
                    boolean priority = input.nextBoolean();
                    System.out.println("Enter the type (0: task, 1: reminder): ");
                    boolean type = input.nextBoolean();
                    System.out.println(controller.addAssignment(title, description, dueDate, priority, type));
                }
                case 2 -> System.out.println(controller.showTasks());
                case 3 -> System.out.println(controller.showReminders());
                case 4 -> System.out.println(controller.showAll());
                case 5 -> System.out.println("Goodbye! ^^");
                default -> System.out.println("Invalid option");
            }
        } while (option != 5);
    }

    public static void menu() {
        System.out.println("1. Add a assignment");
        System.out.println("2. List all tasks");
        System.out.println("3. List all reminders");
        System.out.println("4. Show all assignments");
        System.out.println("5. Exit");
    }
}

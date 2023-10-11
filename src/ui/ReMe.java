package ui;


import model.ReMeManager;

import java.util.Scanner;

public class ReMe {

    public static final ReMeManager controller = new ReMeManager();
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
                    String title = input.nextLine();
                    System.out.println("Enter the description: ");
                    String description = input.nextLine();
                    System.out.println("Enter the due date: ");
                    String dueDate = input.nextLine();
                    System.out.println("Enter the priority, in a scale from 1 to 5, and 0 if it has no priority: ");
                    int priority = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the type (0: task, 1: reminder): ");
                    int type = input.nextInt();
                    input.nextLine();
                    System.out.println(controller.addAssignment(title, description, dueDate, priority, type));
                }
                case 2 -> System.out.println(controller.showTasks());
                case 3 -> System.out.println(controller.showReminders());
                case 4 -> System.out.println(controller.showAll());
                case 5 -> {
                    System.out.println("Enter the title of the assignment you want to remove: ");
                    String title = input.nextLine();
                    System.out.println(controller.removeAssignment(title));
                }
                case 6 -> {
                    System.out.println("Enter the title of the assignment you want to modify: ");
                    String title = input.nextLine();
                    if (controller.getAssignment(title) == null) {
                        System.out.println("There is no assignment with that title");
                        break;
                    }
                    System.out.println("Enter the new title: ");
                    String newTitle = input.nextLine();
                    System.out.println("Enter the new description: ");
                    String description = input.nextLine();
                    System.out.println("Enter the new due date: ");
                    String dueDate = input.nextLine();
                    System.out.println("Enter the new priority, in a scale from 1 to 5, and 0 if it has no priority: ");
                    int priority = input.nextInt();
                    input.nextLine();
                    input.nextLine();
                    System.out.println(controller.modifyAssignment(title, newTitle, description, dueDate, priority));
                }
                case 7 -> System.out.println("Goodbye! ^^");
                default -> System.out.println("Invalid option");
            }
        } while (option != 7);
    }

    public static void menu() {
        System.out.println("1. Add a assignment");
        System.out.println("2. List all tasks");
        System.out.println("3. List all reminders");
        System.out.println("4. Show all assignments");
        System.out.println("5. Remove a assignment");
        System.out.println("6. Modify a assignment");
        System.out.println("7. Exit");
    }
}

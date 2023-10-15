package ui;


import model.ReMeManager;

import java.util.Scanner;

public class ReMe {

    public static final ReMeManager controller = new ReMeManager();
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        String title;
        String description;
        String dueDate;
        int priority;
        int type;
        boolean continuity = true;
        int mod;
        System.out.println("***************************************");
        System.out.println("*           Welcome to ReMe           *");
        System.out.println("***************************************"+ "\n");

        while (continuity){
            menu();
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 0:
                    System.out.println("Goodbye! ^^");
                    continuity = false;
                    break;
                case 1 :
                    System.out.println("Enter the title: ");
                    title = input.nextLine();
                    System.out.println("Enter the description: ");
                    description = input.nextLine();
                    System.out.println("Enter the due date in the format yyyy-[m]m-[d]d hh:mm:ss: ");
                    dueDate = input.nextLine();
                    System.out.println("Enter the priority, in a scale from 1 to 5, and 0 if it has no priority: ");
                    priority = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the type (0: task, 1: reminder): ");
                    type = input.nextInt();
                    input.nextLine();
                    System.out.println(controller.addAssignment(title, description, dueDate, priority, type));
                    break;
                case 2 :
                    System.out.println("Enter the title of the assignment you want to remove: ");
                    title = input.nextLine();
                    System.out.println(controller.removeAssignment(title));
                    break;
                case 3 :
                    System.out.println("Enter the title of the assignment you want to modify: ");
                    title = input.nextLine();
                    System.out.println("What do you want to modify?");
                    mod= modifyMenu();
                    System.out.println("Enter the new value: ");
                    String newValue = input.nextLine();
                    System.out.println(controller.modifyAssignment(title, mod, newValue));
                    break;
                case 4 :
                    System.out.println(controller.undo());
                    break;
                case 5 :
                    System.out.println(controller.showTasks());
                    break;
                case 6 :
                    System.out.println(controller.showReminders());
                    break;
                case 7 :
                    System.out.println(controller.showAll());
                    break;
                case 8 :
                    System.out.println(controller.showPriorityAssignments());
                    break;
                case 9 :
                    System.out.println(controller.showPriorityAssignmentsByDate());
                    break;
                case 10 :
                    System.out.println(controller.showNonPriorityAssignments());
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println("\n");
        }
    }

    public static void menu() {
        System.out.println("1. Add a assignment");
        System.out.println("2. Remove a assignment");
        System.out.println("3. Modify a assignment");
        System.out.println("4. Undo last action");
        System.out.println("5. Show all tasks");
        System.out.println("6. Show all reminders");
        System.out.println("7. Show all assignments");
        System.out.println("8. Show priority assignments");
        System.out.println("9. Show priority assignments by date");
        System.out.println("10. Show non priority assignments ");
        System.out.println("0. Exit");
    }

    public static int modifyMenu(){
        System.out.println("1. Modify description");
        System.out.println("2. Modify due date, remember the format yyyy-[m]m-[d]d hh:mm:ss");
        System.out.println("3. Modify priority");
        System.out.println("4. Modify type");
        int opt = input.nextInt();
        input.nextLine();
        if (opt < 1 || opt > 4){
            System.out.println("Invalid option");
            modifyMenu();
        }
        return opt;
    }
}

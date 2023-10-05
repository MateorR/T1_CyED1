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
                case 1 -> System.out.println("Add a task");
                case 2 -> System.out.println("Add a reminder");
                case 3 -> System.out.println("List all tasks");
                case 4 -> System.out.println("List all reminders");
                case 5 -> System.out.println("Exit");
                default -> System.out.println("Invalid option");
            }
        } while (option != 5);
    }

    public static void menu() {
        System.out.println("1. Add a task");
        System.out.println("2. Add a reminder");
        System.out.println("3. List all tasks");
        System.out.println("4. List all reminders");
        System.out.println("5. Exit");
    }
}

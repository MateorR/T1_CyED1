package model;

import implementations.HashTable;

public class Controller {

    private final HashTable<String, Assignment> tasks = new HashTable<>();
    public String addTask(String title, String description, String dueDate, boolean priority, boolean isComplete) {
        Assignment assignment = new Assignment(title, description, dueDate, priority, isComplete, true);
        tasks.add(title, assignment);
        return "Tasks added successful";
    }

    public String addReminder(String title, String description, String dueDate, boolean priority, boolean isComplete) {
        Assignment assignment = new Assignment(title, description, dueDate, priority, isComplete, false);
        tasks.add(title, assignment);
        return "Reminder added successful";
    }

    public String showTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : tasks.values()) {
            if (assignment.isTasks()) {
                stringBuilder.append(assignment);
            }
        }
        return stringBuilder.toString();
    }

    public String showReminders() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : tasks.values()) {
            if (assignment.isReminder()) {
                stringBuilder.append(assignment);
            }
        }
        return stringBuilder.toString();
    }

    public String showAll() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : tasks.values()) {
            stringBuilder.append(assignment);
        }
        return stringBuilder.toString();
    }
}

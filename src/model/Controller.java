package model;

import implementations.HashTable;

public class Controller {

    private final HashTable<String, Assignment> tasks = new HashTable<>();
    public String addAssignment(String title, String description, String dueDate, boolean priority, boolean isComplete, boolean type) {
        Assignment assignment = new Assignment(title, description, dueDate, priority, isComplete, true);
        tasks.add(title, assignment);
        return "Tasks added successful";
    }

    public String showTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : tasks.values()) {
            if (assignment.isTasks()) {
                stringBuilder.append(assignment);
            }
        }
        return stringBuilder.toString();
    }

    public String showReminders() {
        if (tasks.isEmpty()) {
            return "There are no reminders";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : tasks.values()) {
            if (assignment.isReminder()) {
                stringBuilder.append(assignment);
            }
        }
        return stringBuilder.toString();
    }

    public String showAll() {
        if (tasks.isEmpty()) {
            return "There are no assignments";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : tasks.values()) {
            stringBuilder.append(assignment);
        }
        return stringBuilder.toString();
    }
}

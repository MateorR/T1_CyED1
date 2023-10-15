package model;

import util.*;

import java.util.ArrayList;

public class ReMeManager {

    private final HashTable<String, Assignment> tasks = new HashTable<>();
    private PriorityQueue<Assignment> priorityAssignments = new PriorityQueue<>();
    private Queue<Assignment> nonPriorityAssignments = new Queue<>();
    private final Stack<Action> undoStack = new Stack<>();

    public ReMeManager() {
    }

    public String addAssignment(String title, String description, String dueDate, int priority, int type) {
        if (tasks.containsKey(title)) {
            return "There is already an assignment with that title";
        }
        Assignment assignment = new Assignment(title, description, dueDate, priority, false, type);
        tasks.add(title, assignment);
        undoStack.push(new Action(0, assignment));
        return AssignmentType.values()[type] + " added successful";
    }

    public String showTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : tasks.values()) {
            if (assignment.getTypeInt() == 0) {
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
            if (assignment.getTypeInt() == 1) {
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

    public void buildPriorityAssignments() {
        if (!tasks.isEmpty()) {
            for (Assignment assignment : tasks.values()) {
                if (assignment.getPriority() != 0) {
                    priorityAssignments.insert(assignment);
                }
            }
        }
    }

    public String showPriorityAssignments() {
        buildPriorityAssignments();
        priorityAssignments.heapSort();
        if (!priorityAssignments.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = priorityAssignments.getHeapSize(); i >= 0; i--) {
                stringBuilder.append(priorityAssignments.getElem(i));
            }
            priorityAssignments = new PriorityQueue<>();
            return stringBuilder.toString();
        }
        return "There are no priority assignments";
    }

    public String showPriorityAssignmentsByDate() {
        buildPriorityAssignments();
        if (!priorityAssignments.isEmpty()) {
            ArrayList<Assignment> aux;
            aux = priorityAssignments.getElements();
            StringBuilder stringBuilder = new StringBuilder();
            for (Assignment assignment : sortByDate(aux)) {
                stringBuilder.append(assignment);
            }
            priorityAssignments = new PriorityQueue<>();
            return stringBuilder.toString();
        }
        priorityAssignments = new PriorityQueue<>();
        return "There are no priority assignments";
    }

    private ArrayList<Assignment> sortByDate(ArrayList<Assignment> assignment) {
        int n = assignment.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (assignment.get(i).compareDate(assignment.get(j)) < 0) {
                    Assignment prev = assignment.get(i);
                    Assignment current = assignment.get(j);
                    assignment.set(i, current);
                    assignment.set(j, prev);
                }
            }
        }
        return assignment;
    }

    private void buildNonPriorityAssignments() {
        if (!tasks.isEmpty()) {
            for (Assignment assignment : tasks.values()) {
                if (assignment.getPriority() == 0) {
                    nonPriorityAssignments.enqueue(assignment);
                }
            }
        }
    }

    public String showNonPriorityAssignments() {
        buildNonPriorityAssignments();
        if (!nonPriorityAssignments.isEmpty()) {
            String msg=String.valueOf(showNonPriorityAssignments(nonPriorityAssignments));
            nonPriorityAssignments = new Queue<>();
            return msg;
        }
        return "There are no non priority assignments";
    }

    private StringBuilder showNonPriorityAssignments(Queue<Assignment> queue) {
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            stringBuilder.append(queue.dequeue());
        }
        return stringBuilder;
    }

    public String undo() {
        String msg = "There are no actions to undo";
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            msg = "Undo successful";
            int process = action.getType();
            if (process == 0) {
                tasks.remove(action.getTitleAssignment());
            } else if (process == 1) {
                tasks.remove(action.getTitleAssignment());
                tasks.add(action.getTitleAssignment(), action.getAssignment());
            } else if (process == 2) {
                tasks.add(action.getTitleAssignment(), action.getAssignment());
            }
        }
        return msg;
    }

    public String removeAssignment(String title) {
        if (tasks.isEmpty()) {
            return "There are no assignment with that title";
        }
        for (Assignment assignment : tasks.values()) {
            if (assignment.getTitle().equals(title)) {
                tasks.remove(title);
            }
        }
        undoStack.push(new Action(2, tasks.get(title)));
        return "Assignment removed successful";
    }



    public String modifyAssignment(String title, int mod, String newValue) {
        if (tasks.isEmpty()) {
            return "There are no assignments";
        }
        Assignment assignment = tasks.get(title);
        if (assignment == null) {
            return "There are no assignment with that title";
        }
        if (mod == 1) {
            assignment.setDescription(newValue);
        } else if (mod == 2) {
            assignment.setDueDate(newValue);
        } else if (mod == 3) {
            assignment.setPriority(Integer.parseInt(newValue));
        } else if (mod == 4) {
            assignment.setType(Integer.parseInt(newValue));
        }
        undoStack.push(new Action(1, tasks.get(title)));
        return "Assignment modified successful";
    }

    public Assignment findAssignment(String title) {
        if (tasks.isEmpty()) {
            return null;
        }
        return tasks.get(title);
    }
}

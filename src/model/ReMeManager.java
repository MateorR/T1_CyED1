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
        Assignment assignment = new Assignment(title, description, dueDate, priority, false, type);
        tasks.add(title, assignment);
        if (priority == 0) {
            nonPriorityAssignments.enqueue(assignment);
        }
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
                priorityAssignments.insert(assignment);
            }
        }
    }

    public String showPriorityAssignments() {
        buildPriorityAssignments();
        priorityAssignments.heapSort();
        if (priorityAssignments.isEmpty()) {
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
        if (priorityAssignments.isEmpty()) {
            ArrayList<Assignment> aux = new ArrayList<>();
            aux = priorityAssignments.getElements();
            StringBuilder stringBuilder = new StringBuilder();
            for (Assignment assignment : sortByDate(aux)) {
                stringBuilder.append(assignment);
            }
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
                nonPriorityAssignments.enqueue(assignment);
            }
        }
    }

    public String showNonPriorityAssignments() {
        if (!nonPriorityAssignments.isEmpty()) {
            return String.valueOf(showNonPriorityAssignments(nonPriorityAssignments));
        }
        return "There are no non priority assignments";
    }

    private StringBuilder showNonPriorityAssignments(Queue<Assignment> queue) {
        Queue<Assignment> aux = new Queue<>();
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            stringBuilder.append(queue.peek());
            aux.enqueue(queue.dequeue());
        }
        this.nonPriorityAssignments = aux;
        return stringBuilder;
    }

    public String completeAssignment(String title) {
        if (tasks.isEmpty()) {
            return "There are no assignments";
        }

        undoStack.push(new Action(2, tasks.get(title)));
        return ":)";
    }

    public String modifyAssignment(String title) {
        if (tasks.isEmpty()) {
            return "There are no assignments";
        }

        undoStack.push(new Action(1, tasks.get(title)));
        return ";)";
    }

    public String undo() {
        String msg = "There are no actions to undo";
        if (undoStack.isEmpty()) {
            Action action = undoStack.pop();
            msg = "Undo successful";
            switch (action.getType()) {
                case 0:
                    tasks.remove(action.getTitleAssignment());
                    if (action.getPriorityAssignment() == 0) {
                        nonPriorityAssignments.dequeue();
                    }
                    break;
                case 1:
                    tasks.remove(action.getTitleAssignment());
                    tasks.add(action.getTitleAssignment(), action.getAssignment());
                    if (action.getPriorityAssignment() == 0) {
                        buildNonPriorityAssignments();
                    }
                    ;
                case 2:
                    tasks.add(action.getTitleAssignment(), action.getAssignment());
                    if (action.getPriorityAssignment() == 0) {
                        nonPriorityAssignments.enqueueTop(action.getAssignment());
                    }
                    break;
            }
        }
        return msg;
    }
}

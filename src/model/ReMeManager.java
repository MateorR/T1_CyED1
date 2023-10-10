package model;

import util.*;

import java.util.ArrayList;

public class ReMeManager {

    private final HashTable<String, Assignment> tasks = new HashTable<>();
    private PriorityQueue<Assignment> prioAssignments  = new PriorityQueue<>();

    private Queue<Assignment> nonPriorityAssg = new Queue<>();

    public String addAssignment(String title, String description, String dueDate, int priority, int type) {
        Assignment assignment = new Assignment(title, description, dueDate, priority, false, type);
        tasks.add(title, assignment);
        if (priority == 0) {
            nonPriorityAssg.enqueue(assignment);
        } else {
            prioAssignments.insert(assignment);
        }
        return AssignmentType.values()[type] + " added successful";
    }

    public String showTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : tasks.values()) {
            if (assignment.getTypeInt()==0) {
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
            if (assignment.getTypeInt()==1) {
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

    public String showPriorityAssignments() {
        if (prioAssignments.isEmpty()) {
            return "There are no priority assignments";
        }
        prioAssignments.heapSort();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=prioAssignments.getHeapSize(); i>=0; i--) {
            stringBuilder.append(prioAssignments.getElem(i));
        }
        return stringBuilder.toString();
    }

    public String showPriorityAssignemntsByDate() {
        if (prioAssignments.isEmpty()) {
            return "There are no priority assignments";
        }
        ArrayList<Assignment> aux = new ArrayList<>();
        aux= prioAssignments.getElements();
        StringBuilder stringBuilder = new StringBuilder();
        for (Assignment assignment : sortByDate(aux)) {
            stringBuilder.append(assignment);
        }
        return stringBuilder.toString();
    }

    private ArrayList<Assignment> sortByDate(ArrayList<Assignment> assg) {
        int n = assg.size();
        for (int i = 0; i < n - 1 ; i++) {
            for (int j = i+1; j < n; j++) {
                if(assg.get(i).compareDate(assg.get(j)) > 0){
                    Assignment prev = assg.get(i);
                    Assignment current = assg.get(j);
                    assg.set(i , current);
                    assg.set(j, prev);
                }
            }
        }
        return assg;
    }

    public String showNonPriorityAssignments() {
        if (nonPriorityAssg.isEmpty()) {
            return "There are no non priority assignments";
        }
        return String.valueOf(showNonPriorityAssignments(nonPriorityAssg));
    }

    private StringBuilder showNonPriorityAssignments(Queue<Assignment> queue) {
        Queue<Assignment> aux = new Queue<>();
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            stringBuilder.append(queue.peek());
            aux.enqueue(queue.dequeue());
        }
        this.nonPriorityAssg = aux;
        return stringBuilder;
    }

    public String completeAssignment(String title) {
        if (tasks.isEmpty()) {
            return "There are no assignments";
        }
        return ":)";
    }

    public String modifyAssignment(String title) {
        if (tasks.isEmpty()) {
            return "There are no assignments";
        }
        return ";)";
    }



}

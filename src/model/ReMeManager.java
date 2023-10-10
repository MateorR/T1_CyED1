package model;

import util.*;

import java.util.ArrayList;

public class ReMeManager {

    private final HashTable<String, Assignment> tasks = new HashTable<>();
    private PriorityQueue<Assignment> prioAssignments  = new PriorityQueue<>();
    private Queue<Assignment> nonPriorityAssg = new Queue<>();
    private Stack<Action> undoStack = new Stack<>();

    public ReMeManager() {
    }

    public String addAssignment(String title, String description, String dueDate, int priority, int type) {
        Assignment assignment = new Assignment(title, description, dueDate, priority, false, type);
        tasks.add(title, assignment);
        if (priority == 0) {
            nonPriorityAssg.enqueue(assignment);
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
    public void buildPriorityAssignments() {
        if (!tasks.isEmpty()) {
            for (Assignment assignment : tasks.values()) {
                prioAssignments.insert(assignment);
            }
        }
    }
    public String showPriorityAssignments() {
        buildPriorityAssignments();
        prioAssignments.heapSort();
        if (!prioAssignments.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=prioAssignments.getHeapSize(); i>=0; i--) {
                stringBuilder.append(prioAssignments.getElem(i));
            }
            prioAssignments = new PriorityQueue<>();
            return stringBuilder.toString();
        }
        return "There are no priority assignments";
    }

    public String showPriorityAssignemntsByDate() {
        buildPriorityAssignments();
        if (!prioAssignments.isEmpty()) {
            ArrayList<Assignment> aux = new ArrayList<>();
            aux= prioAssignments.getElements();
            StringBuilder stringBuilder = new StringBuilder();
            for (Assignment assignment : sortByDate(aux)) {
                stringBuilder.append(assignment);
            }
            return stringBuilder.toString();
        }
        prioAssignments = new PriorityQueue<>();
        return "There are no priority assignments";
    }

    private ArrayList<Assignment> sortByDate(ArrayList<Assignment> assg) {
        int n = assg.size();
        for (int i = 0; i < n - 1 ; i++) {
            for (int j = i+1; j < n; j++) {
                if(assg.get(i).compareDate(assg.get(j)) < 0){
                    Assignment prev = assg.get(i);
                    Assignment current = assg.get(j);
                    assg.set(i , current);
                    assg.set(j, prev);
                }
            }
        }
        return assg;
    }

    private void buildNonPriorityAssignments() {
        if (!tasks.isEmpty()) {
            for (Assignment assignment : tasks.values()) {
                nonPriorityAssg.enqueue(assignment);
            }
        }
    }
    public String showNonPriorityAssignments() {
        if (!nonPriorityAssg.isEmpty()) {
            return String.valueOf(showNonPriorityAssignments(nonPriorityAssg));
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
        this.nonPriorityAssg = aux;
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

    public String undo(){
        String msg = "There are no actions to undo";
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            msg = "Undo successful";
            switch (action.getType()) {
                case 0:
                    tasks.remove(action.getTitleAssg());
                    if (action.getPriorityAssg() == 0){
                        nonPriorityAssg.dequeue();
                    }
                    break;
                case 1:
                    tasks.remove(action.getTitleAssg());
                    tasks.add(action.getTitleAssg(), action.getAssignment());
                    if (action.getPriorityAssg() == 0) {
                        buildNonPriorityAssignments();
                    };
                case 2:
                    tasks.add(action.getTitleAssg(), action.getAssignment());
                    if (action.getPriorityAssg() == 0) {
                        nonPriorityAssg.enqueueTop(action.getAssignment());
                    }
                    break;
            }
        }
        return msg;
    }
}

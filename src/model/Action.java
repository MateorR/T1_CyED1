package model;

public class Action {
    private int type; // 0 add, 1 modify, 2 delete/complete
    private Assignment assignment;

    public Action(int type, Assignment assignment) {
        this.type = type;
        this.assignment = assignment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public String getTitleAssignment() {
        return assignment.getTitle();
    }

    public int getPriorityAssignment() {
        return assignment.getPriority();
    }
}

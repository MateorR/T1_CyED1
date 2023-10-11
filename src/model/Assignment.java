package model;

import java.sql.Timestamp;

public class Assignment implements Comparable<Assignment> {
    private String title;
    private String description;
    private Timestamp dueDate;
    private int priority;
    private boolean isComplete;
    private AssignmentType type;

    public Assignment(String title, String description, String dueDate, int priority, boolean isComplete, int type) {
        this.title = title;
        this.description = description;
        this.dueDate = Timestamp.valueOf(dueDate);
        this.priority = priority;
        this.isComplete = isComplete;
        this.type = AssignmentType.values()[type];
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public AssignmentType getType() {
        return type;
    }

    public int getTypeInt() {
        return type.ordinal();
    }

    public void setType(AssignmentType type) {
        this.type = type;
    }

    public void setType(int type) {
        this.type = AssignmentType.values()[type];
    }

    @Override
    public int compareTo(Assignment o) {
        int result;
        if (this.priority > o.priority) {
            result = 1;
        } else if (this.priority < o.priority) {
            result = -1;
        } else {
            if (this.dueDate.after(o.dueDate)) {
                result = -1;
            } else if (this.dueDate.before(o.dueDate)) {
                result = 1;
            } else {
                result = 0;
            }
        }
        return result;
    }

    public int compareDate(Assignment o) {
        int result;
        if (this.dueDate.after(o.dueDate)) {
            result = -1;
        } else if (this.dueDate.before(o.dueDate)) {
            result = 1;
        } else {
            result = Integer.compare(this.priority, o.priority);
        }
        return result;
    }


}
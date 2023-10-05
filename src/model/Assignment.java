package model;

import java.sql.Timestamp;

public class Assignment {
    private String title;
    private String description;
    private Timestamp dueDate;
    private boolean priority;
    private boolean isComplete;
    private final AssignmentType type;
    public Assignment(String title, String description, String dueDate, boolean priority, boolean isComplete, boolean type) {
        this.title = title;
        this.description = description;
        this.dueDate = Timestamp.valueOf(dueDate);
        this.priority = priority;
        this.isComplete = isComplete;
        if (type) {
            this.type = AssignmentType.TASK;
        } else {
            this.type = AssignmentType.REMINDER;
        }
    }
    public boolean isTasks(){
        return type.equals(AssignmentType.TASK);
    }
    public boolean isReminder(){
        return type.equals(AssignmentType.REMINDER);
    }
}
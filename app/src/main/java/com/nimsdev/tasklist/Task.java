package com.nimsdev.tasklist;

public class Task {

    private int taskId;
    private int listId;
    private String name;
    private String notes;
    private String completedDate;
    private String hidden;

    public static final String TRUE = "1";
    public static final String FALSE = "0";

    public Task() {
        name = "";
        notes = "";
        completedDate = FALSE;
        hidden = FALSE;

    }

    public Task(int listId, String name, String notes, String completedDate, String hidden) {
        this.listId = listId;
        this.name = name;
        this.notes = notes;
        this.completedDate = completedDate;
        this.hidden = hidden;
    }

    public Task(int taskId, int listId, String name, String notes, String completed, String hidden) {
        this.taskId = taskId;
        this.listId = listId;
        this.name = name;
        this.notes = notes;
        this.completedDate = completed;
        this.hidden = hidden;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public long getCompletedDateMills() {return Long.parseLong(completedDate)}
    public void setCompletedDateMills(long mills) {this.completedDate = Long.toString(mills);}

}

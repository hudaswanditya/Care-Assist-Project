package de.careassist.app.Todo;

import de.careassist.app.GeneralTask;

public class ToDo {

    private GeneralTask task;
    private boolean done;
    private int shiftedDays;

    public ToDo(GeneralTask task) {
        this.task = task;
//        this.shiftedDays = 0;
        this.done = false;
    }

    public GeneralTask getTask() {
        return task;
    }

    public void setTask(GeneralTask task) {
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getShiftedDays() {
        return shiftedDays;
    }

    public void setShiftedDays(int shiftet) {
        shiftedDays = shiftet;
    }
}
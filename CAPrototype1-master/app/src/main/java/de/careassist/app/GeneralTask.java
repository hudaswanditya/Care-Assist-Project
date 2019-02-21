package de.careassist.app;

import java.util.Calendar;
import java.util.Date;

import de.careassist.app.Todo.Note;

public class GeneralTask {

    private String name;
    private String[] description;
    private String tag;
    private String shiftedNote;
    private Note note;
    private int frequencyInDays;
    private Date lastDueDate;
    private int approximateDurationInMinutes;

    public GeneralTask(String name, String[] description, String tag) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        lastDueDate = Calendar.getInstance().getTime();
    }

    public GeneralTask(String name, String[] description, String tag, int frequencyInDays, int approximateDurationInMinutes) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.frequencyInDays = frequencyInDays;
        lastDueDate = Calendar.getInstance().getTime();
        this.approximateDurationInMinutes = approximateDurationInMinutes;
    }

    /**
     * Getter // Setter
     */

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getShiftedNote() {
        return shiftedNote;
    }

    public void setShiftedNote(String shiftedNote) {
        this.shiftedNote = shiftedNote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {this.description = description;}

    public String getTag() {return tag;}

    public void setTag(String tag) {this.tag = tag;}

    public int getFrequencyInDays() {
        return frequencyInDays;
    }

    public void setFrequencyInDays(int frequencyInDays) {
        this.frequencyInDays = frequencyInDays;
    }

    public Date getLastDueDate() {
        return lastDueDate;
    }

    public void setLastDueDate(Date lastDueDate) {
        this.lastDueDate = lastDueDate;
    }

    public int getApproximateDurationInMinutes() {
        return approximateDurationInMinutes;
    }

    public void setApproximateDurationInMinutes(int approximateDurationInMinutes) {
        this.approximateDurationInMinutes = approximateDurationInMinutes;
    }
}
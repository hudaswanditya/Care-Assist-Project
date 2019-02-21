package de.careassist.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import de.careassist.app.Todo.Note;
import de.careassist.app.Todo.ToDo;
import de.careassist.app.Vital.VitalValues;
import de.careassist.app.dummy.DummyClientMedicine;

public class Client {
    private int id;
    private int imagePath;
    private String firstName;
    private String lastName;
    private String address;
    private String birthDate;
    private int careLevel;
    private DummyClientMedicine medicineList;
    private List<Note> notes;
    private List<Note> fixedNotes;
    private String concerns;
    private List<GeneralTask> generalTasks;
    private TreeMap<Date, List<ToDo>> toDos;
    private VitalValues vital;
    private List<PhoneNumber> phoneNumber;
    private List<File> documentation;
    private List<Integer> pictograms;
    private List<String> hints;


    private boolean doneForToday;

    public Client(int id, int imagePath, String firstName, String lastName, String dateString,
                  DummyClientMedicine medicineList, List<Note> notes, List<Note> fixed,
                  TreeMap<Date, List<ToDo>> toDoList, VitalValues vital, String infodump,
                  int careLevel, String address, ArrayList<PhoneNumber> phoneNumber,
                  ArrayList<Integer> pictograms, List<GeneralTask> generalTasks,
                  List<String> hints) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.imagePath = imagePath;
        this.birthDate = dateString;
        this.medicineList = medicineList;
        this.notes = notes;
        this.fixedNotes = fixed;
        this.toDos = toDoList;
        this.vital = vital;
        this.concerns = infodump;
        this.careLevel = careLevel;
        this.address = address;
        this.pictograms = pictograms;
        this.phoneNumber = new ArrayList<>();
        this.phoneNumber = phoneNumber;
        this.generalTasks = generalTasks;
        this.hints = hints;
    }

    public String generateFullName() {
        return (firstName + " " + lastName);
    }

    public String extractStreetFromAddress() {
        String[] street = address.split(", ");
        return street[0];
    }

    public String extractCityFromAddress() {
        String[] street = address.split(", ");
        return street[1];
    }

    /**
     * Getter // Setter
     *
     * Only flields that needs to be in the db are allowed to have getter and setter methods!
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(int careLevel) {
        this.careLevel = careLevel;
    }

    public DummyClientMedicine getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(DummyClientMedicine medicineList) {
        this.medicineList = medicineList;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getFixedNotes() {
        return fixedNotes;
    }

    public void setFixedNotes(List<Note> fixedNotes) {
        this.fixedNotes = fixedNotes;
    }

    public String getConcerns() {
        return concerns;
    }

    public void setConcerns(String concerns) {
        this.concerns = concerns;
    }

    public List<GeneralTask> getGeneralTasks() {
        return generalTasks;
    }

    public void setGeneralTasks(List<GeneralTask> generalTasks) {
        this.generalTasks = generalTasks;
    }

    public TreeMap<Date, List<ToDo>> getToDos() {
        return toDos;
    }

    public void setToDos(TreeMap<Date, List<ToDo>> toDos) {
        this.toDos = toDos;
    }

    public VitalValues getVital() {
        return vital;
    }

    public void setVital(VitalValues vital) {
        this.vital = vital;
    }

    public List<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<File> extractDocumentation() {
        return documentation;
    }

    public void setDocumentation(ArrayList<File> documentation) {
        this.documentation = documentation;
    }

    public List<Integer> getPictograms() {
        return pictograms;
    }

    public void setPictograms(List<Integer> pictograms) {
        this.pictograms = pictograms;
    }

    public boolean isDoneForToday() {
        return doneForToday;
    }

    public void setDoneForToday(boolean doneForToday) {
        this.doneForToday = doneForToday;
    }

    public List<String> getHints() {
        return hints;
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }
}
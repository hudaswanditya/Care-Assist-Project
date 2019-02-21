package de.careassist.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class Carer implements Actor {

    private static Carer instance;

    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String role;
    private int imagePath;

    // list of clients for the day with date and id
    private TreeMap<Date, List<Appointment>> appointments;

    public Carer(int id, String lastName, String firstName, String email,
                 String password, String role, int imagePath, TreeMap<Date, List<Appointment>> appointments) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.imagePath = imagePath;
        this.appointments = appointments;
    }

    public static Carer getInstance() {
        return instance;
    }

    public static void setInstance(Carer carer) {
        instance = carer;
    }

    public String generateFullName() {
        return firstName + " " + lastName;
    }

    public List<Appointment> extractAppointmentListOfToday() {
        List<Appointment> appointmentsOfToday = new ArrayList<>();

        // look for the appointment list for today in appointment map
        Date today = new Date();
        final int DAY=1000*60*60*24;
        for (Date date : appointments.keySet()) {
            if(today.getTime()/DAY == date.getTime()/DAY) {
                appointmentsOfToday = appointments.get(date);
            }
        }

        return appointmentsOfToday;
    }

    /**
     * Getter // Setter
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TreeMap<Date, List<Appointment>> getAppointments() {
        return appointments;
    }

    public void setAppointments(TreeMap<Date, List<Appointment>> appointments) {
        this.appointments = appointments;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }
}
package de.careassist.app;

/**
 * Created by Max on 23.06.17.
 * An Appointment is one element of the route of today
 */

public class Appointment {
    private int clientID;
    private String start;
    private String end;

    public Appointment(int clientID, String start, String end){
        this.clientID = clientID;
        this.start = start;
        this.end = end;
    }

    public Appointment(int clientID){
        this.clientID = clientID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

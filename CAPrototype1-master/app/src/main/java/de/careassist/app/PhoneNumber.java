package de.careassist.app;

/**
 * Created by Eko on 22.11.2016.
 */

public class PhoneNumber {

    private String name;
    private String number;
    private String relation;



    public PhoneNumber(String name, String number, String relation){
        this.name= name;
        this.number = number;
        this.relation = relation;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}

package de.careassist.app.Vital;

import java.util.Date;
import java.util.TreeMap;

/**
 * Created by dsfd on 19.01.2017.
 * Edit by iamMM on 30.06.17
 */

public class VitalValues {

    private TreeMap<Date, Integer> diastolicValues;
    private TreeMap<Date, Integer> systolicValues;
    private TreeMap<Date, Double> tempValues;
    private TreeMap<Date, Integer> bloodSugarValues;

    public VitalValues(TreeMap<Date, Integer> diastolicValues, TreeMap<Date, Integer> systolicValues,
                       TreeMap<Date, Double> tempValues, TreeMap<Date, Integer> bloodSugarValues) {

        this.diastolicValues = diastolicValues;
        this.systolicValues = systolicValues;
        this.tempValues = tempValues;
        this.bloodSugarValues = bloodSugarValues;
    }

    TreeMap<Date, Integer> getDiastolicValues() {
        return diastolicValues;
    }

    public void setDiastolicValues(TreeMap<Date, Integer> diastolicValues) {
        this.diastolicValues = diastolicValues;
    }

    TreeMap<Date, Integer> getSystolicValues() {
        return systolicValues;
    }

    public void setSystolicValues(TreeMap<Date, Integer> systolicValues) {
        this.systolicValues = systolicValues;
    }

    TreeMap<Date, Double> getTempValues() {
        return tempValues;
    }

    public void setTempValues(TreeMap<Date, Double> tempValues) {
        this.tempValues = tempValues;
    }

    TreeMap<Date, Integer> getBloodSugarValues() {
        return bloodSugarValues;
    }

    public void setBloodSugarValues(TreeMap<Date, Integer> bloodSugarValues) {
       this.bloodSugarValues = bloodSugarValues;
    }
}

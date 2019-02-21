package de.careassist.app.Medication;

/**
 * Created by dsfd on 29.12.2016.
 */

public class Medicine {

    String substance;
    String intensity;
    String form;
    int mornings;
    int noon;
    int afternoon;
    int night;
    String unit;
    String informatio;
    String reason;

    public Medicine(String substance, String intensity, String form, int mornings, int noon, int afternoon, int night, String unit, String informatio, String reason) {
        this.substance = substance;
        this.intensity = intensity;
        this.form = form;
        this.mornings = mornings;
        this.noon = noon;
        this.afternoon = afternoon;
        this.night = night;
        this.unit = unit;
        this.informatio = informatio;
        this.reason = reason;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getMornings() {
        return mornings;
    }

    public void setMornings(int mornings) {
        this.mornings = mornings;
    }

    public int getNoon() {
        return noon;
    }

    public void setNoon(int noon) {
        this.noon = noon;
    }

    public int getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(int afternoon) {
        this.afternoon = afternoon;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getInformatio() {
        return informatio;
    }

    public void setInformatio(String informatio) {
        this.informatio = informatio;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}

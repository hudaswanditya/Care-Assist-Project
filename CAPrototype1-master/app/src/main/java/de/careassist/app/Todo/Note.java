package de.careassist.app.Todo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Note {

    private String tag;
    private String content;
    private Timestamp timestamp;
    private String carerName;

    public Note(String tag, String content, String carerName, Timestamp timestamp) {
        this.tag = tag;
        this.content = content;
        this.carerName = carerName;
        this.timestamp = timestamp;
    }

    public String getInfoFromPosition(int position) {
        if (position == 0) {
            return tag;
        } else if (position == 1) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy, HH:mm", Locale.GERMAN);
            String dateString = format.format(timestamp);
            return dateString;
        } else if (position == 2) {
            return content;
        } else if (position == 3) {
            return carerName; //TODO: return carerID.getLastName();
        }
        return "";
    }

    public String getCarerName() {
        return carerName;
    }

    public void setCarerName(String carerName) {
        this.carerName = carerName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

}
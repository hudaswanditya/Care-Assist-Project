package de.careassist.app.Gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Max on 29.06.17.
 */

public class DateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Date d = new Date();
        if(json.getAsString().contains("-")) {
            // Example Date: 2017-06-23T15:03:07.905+0000
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.GERMAN);
            try {
                d = simpleDateFormat.parse(json.getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (json.getAsString().contains("E")) {
            long date = (long) (Double.parseDouble(json.getAsString()));
            d = new Date(date);
        } else {
            d = new Date(Long.parseLong(json.getAsString()));
        }
    return d;
    }
}

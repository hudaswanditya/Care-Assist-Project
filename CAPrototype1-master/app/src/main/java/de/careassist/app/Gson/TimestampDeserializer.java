package de.careassist.app.Gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Timestamp;

/**
 * Created by Max on 29.06.17.
 */

public class TimestampDeserializer implements JsonDeserializer<Timestamp> {
    @Override
    public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        double d = Double.parseDouble(json.getAsString());
        long time = (long) d;
//        long time = Long.parseLong(json.getAsString());
        return new Timestamp(time);
    }
}

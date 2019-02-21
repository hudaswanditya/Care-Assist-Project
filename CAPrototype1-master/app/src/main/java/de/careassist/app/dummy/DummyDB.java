package de.careassist.app.dummy;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;

import java.util.HashMap;
import java.util.Map;

import de.careassist.app.Carer;
import de.careassist.app.Client;
import de.careassist.app.DataManager;

/**
 * Created by Max on 22.06.17.
 */

public class DummyDB {

    public static void updateCarer(DataManager dataManager) {
        for (Carer carer : DummyCarer.ITEMS) {
            Map<String, Object> properties = new HashMap<>();
            properties.put("type", "carer");
            properties.put("properties", carer);

            // Save the document to the database
            Document document = dataManager.database.getDocument("carer_" + carer.getId());

            try {
                document.delete();
            } catch (CouchbaseLiteException e) {
                e.printStackTrace();
            }

            try {
                document.putProperties(properties);
            } catch (CouchbaseLiteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateClients(DataManager dataManager) {
        for (Client client : DummyClient.ITEMS) {
            Map<String, Object> properties = new HashMap<>();
            properties.put("type", "client");
            properties.put("properties", client);

            // Save the document to the database
            Document document = dataManager.database.getDocument("client_" + client.getId());

            try {
                document.delete();
            } catch (CouchbaseLiteException e) {
                e.printStackTrace();
            }

            try {
                document.putProperties(properties);
            } catch (CouchbaseLiteException e) {
                e.printStackTrace();
            }
        }
    }
}

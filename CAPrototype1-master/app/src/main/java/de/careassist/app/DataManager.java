package de.careassist.app;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.View;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.javascript.JavaScriptReplicationFilterCompiler;
import com.couchbase.lite.javascript.JavaScriptViewCompiler;
import com.couchbase.lite.listener.Credentials;
import com.couchbase.lite.listener.LiteListener;
import com.couchbase.lite.replicator.Replication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

    public Database database;

    private static DataManager instance;

    protected DataManager(Context context) {
        // Create a dataManager
        Manager manager = null;
        try {
            manager = new Manager(new AndroidContext(context), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Create or open the database named "care_assist"
        String db_name = "care_assist";
        database = null;
        try {
            database = manager != null ? manager.getDatabase(db_name) : null;
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        // DataListener for debugging couchbase lite DB
        View.setCompiler(new JavaScriptViewCompiler());
        Database.setFilterCompiler(new JavaScriptReplicationFilterCompiler());

        Credentials credentials = new Credentials(null, null);
        LiteListener liteListener = new LiteListener(manager, 5984, credentials);

        Thread thread = new Thread(liteListener);
        thread.start();

        /**
         * Create replicators to push & pull changes to & from Sync Gateway
         * and use the folloing URLS:
         *  - 10.0.2.2:4984/ for Android stock emulators
         *  - 10.0.3.2:4984/ for Genymotion emulator
         *  - http://94.177.230.91:4984/ for sync-gateway on our server
         */
        URL url = null;
        try {
            url = new URL("http://www.project-careassist.de:4984/" + db_name);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Replication push = database.createPushReplication(url);
        Replication pull = database.createPullReplication(url);
        push.setContinuous(true);
        pull.setContinuous(true);

        // Start replicators
        push.start();
        pull.start();
    }

    public static DataManager getSharedInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    /**
     * kill the db instance when switching user accounts
     */
    public void killInstance() {
        instance = null;
    }

    public void updateClient(final Client client){
        Document document = database.getDocument("client_" + client.getId());

        Map<String, Object> profile = new HashMap<>();
        profile.putAll(document.getProperties());
        profile.put("properties", client);
        try {
            document.putProperties(profile);
        } catch (CouchbaseLiteException ex) {
            Log.e("update", "CBL operation failed");
        }
    }

    public void updateCarer(final Carer carer){
        Document document = database.getDocument("carer_" + carer.getId());

        Map<String, Object> profile = new HashMap<>();
        profile.putAll(document.getProperties());
        profile.put("properties", carer);
        try {
            document.putProperties(profile);
        } catch (CouchbaseLiteException ex) {
            Log.e("update", "CBL operation failed");
        }
    }
}

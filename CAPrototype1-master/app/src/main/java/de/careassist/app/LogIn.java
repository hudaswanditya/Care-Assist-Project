package de.careassist.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.support.LazyJsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.careassist.app.Gson.DateDeserializer;
import de.careassist.app.Gson.TimestampDeserializer;

/**
 * A login screen that offers login via email/password.
 */
public class LogIn extends AppCompatActivity {

    private HashMap<Integer, String> carerCredentials;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private TextView description;
    private EditText userName;
    private EditText password;
    private View mProgressView;
    private View mLoginFormView;
    private boolean login;

    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        // Set up the login form.
        description = (TextView) findViewById(R.id.logInDescription);
        description.setText(R.string.logInDescription);

        userName = (EditText) findViewById(R.id.userNameField);

        password = (EditText) findViewById(R.id.passwordField);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        final Activity activity = this;

        Button logInButton = (Button) findViewById(R.id.submitButton);
        logInButton.setText(R.string.weiter);
        logInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        activity.getCurrentFocus().getWindowToken(), 0);
                attemptLogin();

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        // Set up data manager for couchbase
        dataManager = DataManager.getSharedInstance(getApplicationContext());

        // to overwrite entire database with dummy data
//        DummyDB.updateClients(dataManager);
//        DummyDB.updateCarer(dataManager);

        // Get carer login data here
        QueryEnumerator carerQuery = null;
        try {
            carerQuery = createCredentialsView(dataManager.database).run();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        carerCredentials = new HashMap<>();
        for (QueryRow credential: carerQuery) {
            LazyJsonObject jObject = (LazyJsonObject) credential.getValue();
            try {
                int id = Integer.parseInt(jObject.get("id").toString());
                String email = jObject.get("email").toString();
                String password = jObject.get("password").toString();
                carerCredentials.put(id, email + ":" + password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Query createCredentialsView(Database database) {
        // 1
        com.couchbase.lite.View view = database.getView("care_assist/carer");
        if (view.getMap() == null) {
            // 2
            view.setMap(new Mapper() {
                @Override
                // 3
                public void map(Map<String, Object> document, Emitter emitter) {
                    try {
                        if(document.get("type")!=null) {
                            if (document.get("type").equals("carer")) {
                                emitter.emit("properties", document.get("properties"));
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "4"); // second parameter to setMap is a version number
        }
        Query query = view.createQuery();
        return query;
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        userName.setError(null);
        password.setError(null);

        // Store values at the time of the login attempt.
        String email = userName.getText().toString();
        String inputPassword = password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(inputPassword) && !isPasswordValid(inputPassword)) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            userName.setError(getString(R.string.error_field_required));
            focusView = userName;
            cancel = true;
        } else if (!isEmailValid(email)) {
            userName.setError(getString(R.string.error_invalid_email));
            focusView = userName;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, inputPassword);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >3;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (Integer id : carerCredentials.keySet()) {
                String[] credentials = carerCredentials.get(id).split(":");
                String[] email = credentials[0].split("@");
                if ((credentials[0].equals(mEmail) && credentials[1].equals(mPassword)) ||
                        (email[0].equals(mEmail) && credentials[1].equals(mPassword))) {
                    // Account exists, return true if the password matches.
                    // TODO create carer object
                    retrieveCarerObjectFromDB(id);
                    retrieveAllClientsForToday();
                    return true;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Intent intent = new Intent(getApplicationContext(), Splash.class);
                startActivity(intent);
            } else {
                password.setError(getString(R.string.error_incorrect_password));
                password.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    private void retrieveCarerObjectFromDB(int id) {
        Document document = dataManager.database.getDocument("carer_" + id);

        Object carerObject = document.getProperty("properties");
        Gson gson = new Gson();
        String jsonString = gson.toJson(carerObject, Map.class); // convert the object to json string using Gson
        Carer carer = gson.fromJson(jsonString, Carer.class); // convert the json string to Carer object using Gson

        Carer.setInstance(carer);
    }

    private void retrieveAllClientsForToday() {
        ArrayList<Client> clientList = new ArrayList<>();
        List<Appointment> appointments = Carer.getInstance().extractAppointmentListOfToday();
        for (Appointment appointment : appointments) {
            Document document = dataManager.database.getDocument("client_" +
                    appointment.getClientID());

            Object carerObject = document.getProperty("properties");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampDeserializer());
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Gson gson = gsonBuilder.create();
            try {
                String jsonString = gson.toJson(carerObject, Map.class); // convert the object to json string using Gson
                Client client = gson.fromJson(jsonString, Client.class); // convert the json string to Client object using Gson
                clientList.add(client);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ClientListHandler.setClientList(clientList);
    }
}


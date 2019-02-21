package de.careassist.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import de.careassist.app.Route.RouteActivity;

/**
 * Created by V2 on 24.01.2017.
 */

public class Splash extends Activity {

    /** Duration of wait in milliseconds **/
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.welcome_screen);

        TextView myTextView = (TextView) findViewById(R.id.splash_text);
        myTextView.setText("Willkommen zurück " + Carer.getInstance().generateFullName());

        /* New Handler to start the Menu-Activity
         * and close this de.careassist.app.Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this,RouteActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}

package de.careassist.app.Route;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import de.careassist.app.Profile.ProfileActivity;
import de.careassist.app.R;


public class RouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        //setup the app bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Deine Route");


        if(findViewById(R.id.client_list)!=null){
            ClientListFragment listFragment = new ClientListFragment();

            //TODO funktioniert nur mit dem alten Code (warum?)
            getSupportFragmentManager().beginTransaction().add(R.id.client_list, listFragment).commit();
            //getSupportFragmentManager().beginTransaction().add(R.id.route_list, listFragment).commit();
        }
    }
    public void toProfile(View view) {
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }


    // ----- MENU -----

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.appbar, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.profile:
//
//                //go to profile
//                Intent intent = new Intent(this, ProfileActivity.class);
//                startActivity(intent);
//                return true;
//
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
}

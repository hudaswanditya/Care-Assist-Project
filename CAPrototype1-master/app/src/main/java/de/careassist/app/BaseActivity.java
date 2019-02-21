package de.careassist.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by knuthen on 15.07.2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MaterialView myMaterialView = new MaterialView(this);

//        //setup the app bar
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);
//        getSupportActionBar().setTitle("Deine Route");

        MaterialDrawer materialDrawer = new MaterialDrawer(this); // Instanciate Drawer
        materialDrawer.setMenu(R.menu.activity_main_drawer); // set menu
//        materialDrawer.setNavigationItemSelectedListener(myNavigationListener);// set global   listener
        materialDrawer.setToolbarTitle("Test", "App");// Set Title and Subtitle
//        Tools.setDrawerHeaderData(materialDrawer); // set Header data like Icon and color
    }
}

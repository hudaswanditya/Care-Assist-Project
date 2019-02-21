package de.careassist.app.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import de.careassist.app.Carer;
import de.careassist.app.DataManager;
import de.careassist.app.R;



/**
 * Created by linakriebel on 25.06.17.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //setup the app bar
        // TODO wieso funktioniert das nicht? irgendwie wird die toolbar nicht erkannt
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Dein Profil");

        ImageView photo = (ImageView) findViewById(R.id.carerImage);
        TextView carerName = (TextView) findViewById(R.id.carerName);
        TextView carerRole = (TextView) findViewById(R.id.carerRole);
        TextView carerEmail = (TextView) findViewById(R.id.carerEmail);

        Carer carer = Carer.getInstance();
        photo.setImageResource(carer.getImagePath());
        carerName.setText(carer.generateFullName());
        carerRole.setText(carer.getRole());
        carerEmail.setText(carer.getEmail());

        Button changePasswordBtn = (Button) findViewById(R.id.change_password);
        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogChangePassword dialog = new DialogChangePassword(ProfileActivity.this);
                dialog.show();
            }
        });

        Button logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getSharedInstance(getApplicationContext()).killInstance();

                Intent i = getBaseContext().getPackageManager().
                        getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        //TODO save preferences in carer profile

        final SwitchCompat fontSize = (SwitchCompat) findViewById(R.id.fontsizeSwitch);
        fontSize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    //TODO Switch is on, set bigger text size
                    System.out.println("on");
                } else {
                    //TODO Switch is off, set normal text size
                    System.out.println("off");
                }
            }
        });


        RadioGroup colorSettings = (RadioGroup) findViewById(R.id.colorSettings);
        int checkedRadioButtonID = colorSettings.getCheckedRadioButtonId(); //brauchen wir das?

        colorSettings.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {

                switch (id) {
                    case R.id.theme1:
                        //TODO set colors to selected theme
                        break;
                    case R.id.theme2:
                        //TODO set colors to selected theme
                        break;
                    case R.id.theme3:
                        //TODO set colors to selected theme
                        break;
                }
            }
        });


    }
    public void toProfile(View view) {
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }
    public void goBack (View view) {
        finish();
    }

    // ----- APPBAR -----

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
//                //show toast notification
//                LayoutInflater inflater = getLayoutInflater();
//                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
//
//                TextView text = (TextView) layout.findViewById(R.id.text);
//                text.setText(R.string.profileToast);
//
//                Toast toast = new Toast(getApplicationContext());
//                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 10);
//                toast.setDuration(Toast.LENGTH_SHORT);
//                toast.setView(layout);
//                toast.show();
//                return true;
////            case R.id.back:
////                finish();
////                return true;
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
}


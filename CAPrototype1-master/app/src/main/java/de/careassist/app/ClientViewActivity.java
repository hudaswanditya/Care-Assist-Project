package de.careassist.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeMap;

import de.careassist.app.Documents.DoctorialPrescription1;
import de.careassist.app.Documents.DoctorialPrescription2;
import de.careassist.app.Documents.DoctorialPrescription3;
import de.careassist.app.Documents.MobilisationBeddingFragment;
import de.careassist.app.Documents.WoundDocumentationFragment;
import de.careassist.app.LogBook.LogBookFragment;
import de.careassist.app.Medication.MedicineOverview;
import de.careassist.app.Profile.ProfileActivity;
import de.careassist.app.Todo.ClientView;
import de.careassist.app.Todo.Note;
import de.careassist.app.Todo.ToDo;
import de.careassist.app.Todo.TodoFragment;
import de.careassist.app.Vital.VitalFragment;

public class ClientViewActivity extends AppCompatActivity implements VitalFragment.OnFragmentInteractionListener ,BasicDataBaseFragment.OnDocumentSelectedListener, TodoFragment.OnListFragmentInteractionListener, BasicDataBaseFragment.OnClickCall {

    private Client client;
    private Carer carer;
    private ImageView navImg;
    private TextView firstName;
    private TextView lastName;
    
    // DataManager
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        // get the current client data from the client list
        Intent intent = getIntent();
        client = ClientListHandler.getClientList().get(intent.getIntExtra("index", 0));
        carer = Carer.getInstance();

        // Menu
        final MaterialDrawer materialDrawer = new MaterialDrawer(this); // Instanciate Drawer
        materialDrawer.setMenu(R.menu.activity_main_drawer); // set menu
        materialDrawer.setToolbarTitle("Aufgaben", client.generateFullName()); // Set Title and Subtitle

        materialDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                materialDrawer.closeDrawerIfOpen();
                return true;
            }
        });// set global listener

        // TODO File in die DB speichern
        File f = new File(getFilesDir(), this.getString(R.string.wounddocname)+ " "  + client.generateFullName());
        File f1 = new File(getFilesDir(), this.getString(R.string.mobdocname) + " " + client.generateFullName());

        File f2 = new File(getFilesDir(), this.getString(R.string.doctorialprescription1) + " " +client.generateFullName());
        File f3 = new File(getFilesDir(), this.getString(R.string.doctorialprescription2) + " " + client.generateFullName());
        File f4 = new File(getFilesDir(), this.getString(R.string.doctorialprescription3) + " " + client.generateFullName());

        ArrayList<File> b = new ArrayList<>();
        b.add(f);
        b.add(f1);
        b.add(f2);
        b.add(f3);
        b.add(f4);

        try{
            for(int i =0; i < b.size(); i++){
                if(!b.get(i).exists()){
                    b.get(i).createNewFile();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        client.setDocumentation(b);

        if (findViewById(R.id.fragment_container) != null) {
            ClientView secondFragment = new ClientView();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, secondFragment).commit();

        }

        //DATABASE
        dataManager = DataManager.getSharedInstance(getApplicationContext());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        navImg = (ImageView) header.findViewById(R.id.navImage);
        navImg.setImageResource(client.getImagePath());

        firstName = (TextView) header.findViewById(R.id.clientFirstName);
        firstName.setText(client.getFirstName());

        lastName = (TextView) header.findViewById(R.id.clientLastName);
        lastName.setText(client.getLastName());

        LinearLayout backToRoute = (LinearLayout) header.findViewById(R.id.backToRoute);
        backToRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    public void toProfile(View view) {
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }


    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = ClientView.class;

        switch(menuItem.getItemId()) {
            case R.id.client_view:
                ClientView clientFragment = new ClientView();

                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, clientFragment);
                setActionBarTitle("Aufgaben");

                trans.commit();
                break;
            case R.id.basic_data:
                BasicDataBaseFragment basicFragment = new BasicDataBaseFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, basicFragment);

                trans.commit();
                break;
            case R.id.log:


                LogBookFragment logFragment = new LogBookFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, logFragment);

                trans.commit();
                break;
            case R.id.vital_values:
                VitalFragment vF = new VitalFragment();

                trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, vF);

                trans.commit();
                break;
            case R.id.medication:
                MedicineOverview medicineFrag = new MedicineOverview();

                trans = getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragment_container, medicineFrag);

                trans.commit();

                break;
            case R.id.profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            default:
                fragmentClass = ClientView.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
//        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
//        MaterialDrawer.closeDrawers();
    }



    public void updateView(){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(fragment instanceof ClientView) {
            ClientView newFrag = (ClientView) fragment;
            newFrag.updateClientView(-2, false);
        }
    }

    public void updateRightView(){
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(frag instanceof ClientView) {
            ClientView newFrag = (ClientView) frag;
            newFrag.updateClientView(-1, false);

        }else if(frag instanceof LogBookFragment){
            LogBookFragment logBookFragment = (LogBookFragment) frag;
            logBookFragment.update();

        }

    }

    @Override
    public void onListFragmentInteraction(int position, boolean done) {
        if (position != -1) {
            ClientView newFrag = (ClientView) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (newFrag != null) {
                newFrag.updateClientView(position, done);
            } else {
                newFrag = new ClientView();
                Bundle args = new Bundle();
                args.putInt(ClientView.ARG_Position, position);
                args.putBoolean("done", done);
                newFrag.setArguments(args);

                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                trans.replace(R.id.fragment_container, newFrag);


                trans.commit();
            }
        } else {
            ClientView newFrag = new ClientView();
            Bundle args = new Bundle();
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);

            trans.commit();
        }
    }

    public Client getClient() {
        return client;
    }
    public Carer getCarer() {
        return carer;
    }

    public void onDocumentSelected(String position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article

        Fragment wFrag = new Fragment();

        ArrayList<String> doclist= new ArrayList<>();

        doclist.add(getString(R.string.wounddocname));
        doclist.add(getString(R.string.mobdocname));
        doclist.add(getString(R.string.doctorialprescription1));
        doclist.add(getString(R.string.doctorialprescription2));
        doclist.add(getString(R.string.doctorialprescription3));

        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(new WoundDocumentationFragment());
        fragList.add(new MobilisationBeddingFragment());
        fragList.add(new DoctorialPrescription1());
        fragList.add(new DoctorialPrescription2());
        fragList.add(new DoctorialPrescription3());
        for(int i =0; i < doclist.size(); i ++){
            if (position.equals(doclist.get(i))){
                wFrag = fragList.get(i);
                break;
            }
        }

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.fragment_container, wFrag);
        trans.addToBackStack(null);
        trans.commit();
    }

    public boolean addToDo(int numberOfDaysToNextAppointment, ToDo toDo) {
        // save new note to note array from client
        TreeMap<Date, List<ToDo>> toDos = client.getToDos();
        Date today = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();
        final int DAY=1000*60*60*24;
        for (Date d : toDos.keySet()) {
            if(today.getTime()/DAY == d.getTime()/DAY) {
                Date nextDate = d;
                int i = 0;
                while (i<numberOfDaysToNextAppointment) {
                    if(nextDate != toDos.lastKey()) {
                        nextDate = toDos.higherKey(nextDate);
                    }
                    else return false;
                    i++;
                }
                toDos.get(nextDate).add(toDo);
                break;
            }
        }

        dataManager.updateClient(client);

        // update fragment container
        Fragment newFrag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag instanceof LogBookFragment) {
            ((LogBookFragment) newFrag).update();
        }
        updateRightView();
        return  true;
    }


    public void addToDo(Date date, ToDo toDo) {
        // save new note to note array from client
        TreeMap<Date, List<ToDo>> toDos = client.getToDos();
        final int DAY=1000*60*60*24;
        boolean found = false;
        for (Date d : toDos.keySet()) {
            if(date.getTime()/DAY == d.getTime()/DAY) {
                toDos.get(d).add(toDo);
                found = true;
            }
        }

        if(!found) {
            List<ToDo> newTodoList = new ArrayList<>();
            newTodoList.add(toDo);
            toDos.put(date, newTodoList);
        }

        dataManager.updateClient(client);

        // update fragment container
        Fragment newFrag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag instanceof LogBookFragment) {
            ((LogBookFragment) newFrag).update();
        }
        updateRightView();
    }

    public void addNote(String content, String tag) {
        Note newNote = new Note(tag, content, carer.generateFullName(), new Timestamp(System.currentTimeMillis()));

        // save new note to note array from client
        client.getNotes().add(newNote);

        dataManager.updateClient(client);

        // update fragment container
        Fragment newFrag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag instanceof LogBookFragment) {
            ((LogBookFragment) newFrag).update();
        }
        updateRightView();
    }

    public void addNote (Note note){
        client.getNotes().add(note);
        dataManager.updateClient(client);
        updateRightView();

    }

    public void addFixedNote(String content, String tag ) {
        Note fixedNote = new Note(tag, content, carer.generateFullName(), new Timestamp(System.currentTimeMillis()));
        // save new note to note array from client
        client.getFixedNotes().add(fixedNote);

        dataManager.updateClient(client);

        // update fragment container
        Fragment newFrag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag instanceof LogBookFragment) {
            ((LogBookFragment) newFrag).update();
        }
        updateRightView();
    }

    public void addFixedNote(Note fixedNote) {
        // save new note to note array from client
        client.getFixedNotes().add(fixedNote);

        dataManager.updateClient(client);

        // update fragment container
        Fragment newFrag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag instanceof LogBookFragment) {
            ((LogBookFragment) newFrag).update();
        }
        updateRightView();
    }

    @Override
    public void onClickCall(String number) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + number));

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    @Override
    public void onDatePickerInteraction(TodoFragment frag, ToDo toDo) {
        DialogShiftTask dialogShiftTask = new DialogShiftTask(frag, this, toDo);
        dialogShiftTask.show();
    }

    @Override
    public void newValueAdded(int value, int second, int id) throws InterruptedException {

        VitalFragment newFrag = (VitalFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (newFrag != null) {
            newFrag.addValue(value, second, id);
        } else {
            newFrag = new VitalFragment();
            Bundle args = new Bundle();
            newFrag.setArguments(args);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag);

            trans.commit();
        }

        dataManager.updateClient(client);

    }


}

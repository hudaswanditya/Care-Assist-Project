package de.careassist.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static de.careassist.app.R.id.document_list;

/**
 * Created by Eko on 01.12.2016.
 */

public class BasicDataBaseFragment extends Fragment {

    private View rootView;
    private PhonenumberListAdapter adapter;

    private ListView numbers;
    private ImageView img;
    private TextView firstName;
    private TextView lastName;
    private TextView street;
    private TextView city;
    private TextView gebdate;
    private TextView careLevel;
    private TextView diagnosis;
    private ListView documents;
    private ListView generalTasks;
    private ListView hint;
    private Button addHint;

    //private static final String CLIENTKEY = "client_key";

    private Client client;
    private Context con;

    private OnDocumentSelectedListener mCallback;
    private OnClickCall call;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_basic_data_base, container, false);
        ((ClientViewActivity) getActivity())
                .setActionBarTitle("Stammdaten");

        this.con = getContext();
        if (savedInstanceState == null) {
            this.client = ((ClientViewActivity)getActivity()).getClient();
            if(client !=null) {

                img = (ImageView) rootView.findViewById(R.id.image);
                img.setImageResource(client.getImagePath());

                firstName = (TextView) rootView.findViewById(R.id.clientFirstName);
                firstName.setText(client.getFirstName());

                lastName = (TextView) rootView.findViewById(R.id.clientLastName);
                lastName.setText(client.getLastName());

                street = (TextView) rootView.findViewById(R.id.street);
                street.setText(client.extractStreetFromAddress());

                city = (TextView) rootView.findViewById(R.id.city);
                city.setText(client.extractCityFromAddress());

                gebdate = (TextView) rootView.findViewById(R.id.gebdat);
                gebdate.setText(client.getBirthDate().toString());

                careLevel = (TextView) rootView.findViewById(R.id.care_level);
                careLevel.setText(""+ client.getCareLevel());

                diagnosis = (TextView) rootView.findViewById(R.id.info);
                diagnosis.setText(client.getConcerns());

                numbers = (ListView) rootView.findViewById(R.id.telnumbers);
                adapter = new PhonenumberListAdapter(rootView.getContext(), client, call);
                numbers.setAdapter(adapter);

                //setup hintView list
                ArrayList<String> hintList = (ArrayList<String>) client.getHints();
                hint = (ListView) rootView.findViewById(R.id.hint);
                final ArrayAdapter<String> hintAdapter = new ArrayAdapter<String>(rootView.getContext(), R.layout.task_list, R.id.task, hintList);
                hint.setAdapter(hintAdapter);

                addHint = (Button) rootView.findViewById(R.id.addHint);
                addHint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogAddHint dialogAddHint = new DialogAddHint(getActivity(), client, hint);
                        dialogAddHint.show();
                    }
                });

                //setup documents
                ArrayList<String> documentList = new ArrayList<>();
                for(int i = 0; i<client.extractDocumentation().size(); i++){
                    String s = client.extractDocumentation().get(i).getName().replace(" " + client.generateFullName(), "");
                    documentList.add(s);
                }
                documents = (ListView) rootView.findViewById(document_list);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(rootView.getContext(), R.layout.simple_list_item, R.id.document, documentList);
                documents.setAdapter(arrayAdapter);

                documents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object o = parent.getItemAtPosition(position);
                        String p = (String)o;
                        mCallback.onDocumentSelected(p);
                    }
                });

                //setup general tasks
                ArrayList<String> taskList = new ArrayList<>();
                for(int i = 0; i<client.getGeneralTasks().size(); i++){

                    String name = client.getGeneralTasks().get(i).getName();
                    int frequency = client.getGeneralTasks().get(i).getFrequencyInDays();

                    String general = "";
                    if(frequency > 1){
                        general = name + ": alle " + frequency + " Tage";
                    } else {
                        general = name + ": täglich";
                    }

                    taskList.add(general);
                }

                generalTasks = (ListView) rootView.findViewById(R.id.generalTasks);
                ArrayAdapter<String> taskAdapter = new ArrayAdapter<String>(rootView.getContext(), R.layout.task_list, R.id.task, taskList);
                generalTasks.setAdapter(taskAdapter);

            }
        }  else{
        }

        return rootView;
    }


    public interface OnDocumentSelectedListener {
            void onDocumentSelected(String position);
    }

    public interface OnClickCall{
        void onClickCall(String number);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnDocumentSelectedListener) context;
            call = (OnClickCall) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


}

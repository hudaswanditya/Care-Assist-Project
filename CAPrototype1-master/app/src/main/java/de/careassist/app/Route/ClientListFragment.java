package de.careassist.app.Route;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import de.careassist.app.ClientViewActivity;
import de.careassist.app.ClientListHandler;
import de.careassist.app.R;


public class ClientListFragment extends Fragment {

    public static final int REQUEST_CODE = 0;

    public ClientListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.route_list, container, false);
        ListView myListView = (ListView) view.findViewById(R.id.route_list);

        myListView.setAdapter(new ClientListViewAdapter(getContext(), R.layout.route_element, ClientListHandler.getClientList()));
        //myListView.setAdapter(new ClientListViewAdapter(getContext(), R.layout.route_element, DummyClient.ITEMS)); //only for testing

        myListView.setDivider(ContextCompat.getDrawable(getContext(), R.drawable.divider_transparent));

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(), ClientViewActivity.class);
                intent.putExtra("index", i); //i is position of client in list
                startActivityForResult(intent, REQUEST_CODE);


            }
        });

        return view;
    }
}

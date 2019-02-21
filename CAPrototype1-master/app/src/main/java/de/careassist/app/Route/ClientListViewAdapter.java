package de.careassist.app.Route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.careassist.app.Client;
import de.careassist.app.R;
import de.careassist.app.Appointment;
import de.careassist.app.Carer;
import de.careassist.app.dummy.DummyPictograms;

/**
 * Created by linakriebel on 25.06.17.
 */

public class ClientListViewAdapter extends ArrayAdapter {

    private Context context;

    public ClientListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Client> clients) {
        super(context, resource, clients);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

         // Get the data item for this position
        final Client client = (Client) getItem(position);


        // Check if an existing view is being reused, else inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.route_element, parent, false);

        }

        // Lookup view for data population
        TextView startTime = (TextView) convertView.findViewById(R.id.startTime);
        TextView endTime = (TextView) convertView.findViewById(R.id.endTime);
        ImageView photo = (ImageView) convertView.findViewById(R.id.image);
        TextView firstName = (TextView) convertView.findViewById(R.id.firstName);
        TextView lastName = (TextView) convertView.findViewById(R.id.lastName);
        TextView street = (TextView) convertView.findViewById(R.id.street);
        TextView city = (TextView) convertView.findViewById(R.id.city);
        ImageView checkmark = (ImageView) convertView.findViewById(R.id.checkmark);
        if(client.isDoneForToday()) checkmark.setVisibility(View.VISIBLE);
        else checkmark.setVisibility(View.INVISIBLE);

        // Populate the data into the template view using the data object
        Appointment appointment = Carer.getInstance().extractAppointmentListOfToday().get(position);
        startTime.setText(appointment.getStart());
        endTime.setText(appointment.getEnd());
        photo.setImageResource(client.getImagePath());
        firstName.setText(client.getFirstName());
        lastName.setText(client.getLastName());
        street.setText(client.extractStreetFromAddress());
        city.setText(client.extractCityFromAddress());


        ImageButton route = (ImageButton) convertView.findViewById(R.id.routeIcon);
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri mapsLink = Uri.parse("google.navigation:q="+client.getAddress());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapsLink);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });
        route.setFocusable(false);

        LinearLayout pictogramContainer = (LinearLayout)  convertView.findViewById(R.id.pictrogram_container);
        pictogramContainer.removeAllViews();
        if(pictogramContainer.getChildCount() == 0) {
            ViewGroup.LayoutParams pictogramParams = new ViewGroup.LayoutParams(50, 50);
            for (int i = 0; i < client.getPictograms().size(); i++) {
                final int myI = i;
                ImageView pictogram = new ImageView(getContext());
                pictogram.setLayoutParams(pictogramParams);
                pictogram.setImageResource(client.getPictograms().get(i));
                pictogram.setColorFilter(getContext().getResources().getColor(R.color.colorIcon));
                pictogramContainer.addView(pictogram);

                pictogram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String description = DummyPictograms.pictogramDescriptions.get(client.getPictograms().get(myI));
                        Toast toast = Toast.makeText(getContext(), description, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        }



        // Return the completed view to render on screen
        return convertView;



    }


}


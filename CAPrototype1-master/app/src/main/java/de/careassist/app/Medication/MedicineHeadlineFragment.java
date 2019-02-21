package de.careassist.app.Medication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.careassist.app.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class MedicineHeadlineFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private TextView substance;
    private TextView tradeName;
    private TextView intensity;
    private TextView form;
    private TextView morning;
    private TextView noon;
    private TextView afternoon;
    private TextView night;
    private TextView unit;
    private TextView information;
    private TextView reason;
    //private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MedicineHeadlineFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MedicineListFragment newInstance(int columnCount) {
        MedicineListFragment fragment = new MedicineListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medication, container, false);

        substance = (TextView) view.findViewById(R.id.substance);
        intensity = (TextView) view.findViewById(R.id.intensity);
        form = (TextView) view.findViewById(R.id.form);
        morning = (TextView) view.findViewById(R.id.morning);
        noon = (TextView) view.findViewById(R.id.noon);
        afternoon = (TextView) view.findViewById(R.id.afternoon);
        night = (TextView) view.findViewById(R.id.night);
        unit = (TextView) view.findViewById(R.id.unit);
        information = (TextView) view.findViewById(R.id.information);
        reason = (TextView) view.findViewById(R.id.reason);

        substance.setText("Wirkstoff");
        intensity.setText("Menge");
        form.setText("Form");
        morning.setText("Morgens");
        morning.setTextSize(8);
        noon.setText("Mittags");
        noon.setTextSize(8);
        afternoon.setText("Abends");
        afternoon.setTextSize(8);
        night.setText("Zur Nacht");
        night.setTextSize(8);
        unit.setText("Häufigkeit");
        information.setText("Hinweise");
        reason.setText("Grund");

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Medicine item);
    }*/
}

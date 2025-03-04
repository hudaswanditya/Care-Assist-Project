package de.careassist.app.Medication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.careassist.app.ClientViewActivity;
import de.careassist.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MedicineOverview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicineOverview extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    //private OnFragmentInteractionListener mListener;

    public MedicineOverview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicineOverview.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicineOverview newInstance(String param1, String param2) {
        MedicineOverview fragment = new MedicineOverview();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ClientViewActivity) getActivity())
                .setActionBarTitle("Medikation");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medicine_overview, container, false);

        if(view.findViewById(R.id.headline)!=null){
            MedicineHeadlineFragment mFrag = new MedicineHeadlineFragment();
            getChildFragmentManager().beginTransaction().add(R.id.headline, mFrag).commit();
        }
        if(view.findViewById(R.id.prediscribed_medication)!=null){
            MedicineListFragment mFrag = MedicineListFragment.newInstance("prediscribed");
            getChildFragmentManager().beginTransaction().add(R.id.prediscribed_medication, mFrag).commit();
        }

        /*
        if(view.findViewById(R.id.temporary_medicine)!=null){
            MedicineListFragment mFrag = MedicineListFragment.newInstance("temporary");
            getChildFragmentManager().beginTransaction().add(R.id.temporary_medicine, mFrag).commit();
        }*/
        if(view.findViewById(R.id.self_medication)!=null){
            MedicineListFragment mFrag = MedicineListFragment.newInstance("self");
            getChildFragmentManager().beginTransaction().add(R.id.self_medication, mFrag).commit();
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
       /* if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}

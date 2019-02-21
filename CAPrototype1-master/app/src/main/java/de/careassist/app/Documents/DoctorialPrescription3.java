package de.careassist.app.Documents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import java.io.File;

import de.careassist.app.Client;
import de.careassist.app.ClientViewActivity;
import de.careassist.app.DocumentTools.DocumentsTableTemplateFragment;
import de.careassist.app.DocumentTools.ImageDisplayFragment;
import de.careassist.app.DocumentTools.TableGenerator;
import de.careassist.app.R;

public class DoctorialPrescription3 extends DocumentsTableTemplateFragment {

    private ImageDisplayFragment.OnFragmentInteractionListener mListener;

    public DoctorialPrescription3() {
        // Required empty public constructor
    }
    private ScrollView layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private File overwrite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view= inflater.inflate(R.layout.fragment_document_wound, container, false);
        final Client c = ((ClientViewActivity)getActivity()).getClient();
        String[] firstRow = {view.getContext().getString(R.string.wounddate), view.getContext().getString(R.string.doctpre1hdz),
                view.getContext().getString(R.string.doctorname), view.getContext().getString(R.string.specialdiet),
                view.getContext().getString(R.string.frequency), view.getContext().getString(R.string.dochdz),
                view.getContext().getString(R.string.endon), view.getContext().getString(R.string.dochdz)};
        showTable(firstRow,getString(R.string.doctorialprescription2), view,c);

        return  view;
    }

}

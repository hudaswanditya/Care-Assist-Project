package de.careassist.app.Documents;

import android.content.Context;
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
import de.careassist.app.DocumentTools.TableGenerator;
import de.careassist.app.R;

public class DoctorialPrescription2 extends DocumentsTableTemplateFragment {

    public DoctorialPrescription2() {
        // Required empty public constructor
    }
    private ScrollView layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private Client c;
    private File overwrite;
    private Context con;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view= inflater.inflate(R.layout.fragment_document_wound, container, false);
        this.c = ((ClientViewActivity)getActivity()).getClient();
        String[] firstRow = {getString(R.string.wounddate), getString(R.string.doctpre1hdz),
                getString(R.string.doctorname), getString(R.string.injec_infu),
                getString(R.string.frequency), getString(R.string.dochdz),
                getString(R.string.endon), getString(R.string.dochdz)};
        con = getContext();
        showTable(firstRow, getString(R.string.doctorialprescription2),view, c);


        return  view;
    }

}

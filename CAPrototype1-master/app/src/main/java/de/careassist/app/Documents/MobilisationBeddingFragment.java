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
import de.careassist.app.DocumentTools.TableGenerator;
import de.careassist.app.R;

public class MobilisationBeddingFragment extends DocumentsTableTemplateFragment {

    public MobilisationBeddingFragment() {
        // Required empty public constructor
    }
    private ScrollView layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private File overwrite;
    private int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view= inflater.inflate(R.layout.fragment_document_wound, container, false);
        final Client c = ((ClientViewActivity)getActivity()).getClient();
        String[] firstRow = {view.getContext().getString(R.string.wounddate), view.getContext().getString(R.string.mobdestination),
                view.getContext().getString(R.string.mobMeasures), view.getContext().getString(R.string.mobcharacteristics),
                view.getContext().getString(R.string.mobtime),
                view.getContext().getString(R.string.hdz)};

        showTable(firstRow,getString(R.string.mobdocname), view, c);

        return  view;
    }

}

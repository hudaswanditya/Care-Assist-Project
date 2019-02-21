package de.careassist.app.Documents;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.careassist.app.Client;
import de.careassist.app.ClientViewActivity;
import de.careassist.app.DocumentTools.PictureButton;
import de.careassist.app.DocumentTools.TableGenerator;
import de.careassist.app.DocumentTools.TableRowExpand;
import de.careassist.app.R;

/**
 * Created by Eko on 12.12.2016.
 */

public class WoundDocumentationFragment extends Fragment{
    private FrameLayout layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private Client c;
    private File overwrite;
    private int index;
    private String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_TAKE_PHOTO = 0;


    public WoundDocumentationFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_document_wound, container, false);
        this.c = ((ClientViewActivity) getActivity()).getClient();
        showTable();
        return view;
    }

    private void showTable() {
        mTable = new TableGenerator(getActivity());
        layMain = (FrameLayout) view.findViewById(R.id.table);

        String[] firstRow = {view.getContext().getString(R.string.wounddate), view.getContext().getString(R.string.woundphase),
                view.getContext().getString(R.string.woundsizel), view.getContext().getString(R.string.woundsizew),
                view.getContext().getString(R.string.woundsized),
                view.getContext().getString(R.string.woundlocation),
                view.getContext().getString(R.string.woundkindfrequency), view.getContext().getString(R.string.wounddescription),
                view.getContext().getString(R.string.hdz), view.getContext().getString(R.string.picture)};

        mTable.addHead(firstRow);
        for (int i = 0; i < c.extractDocumentation().size(); i++) {
            if (c.extractDocumentation().get(i).getName().equals(getString(R.string.wounddocname) + " " + c.generateFullName())) {
                //this.index = i;
                this.overwrite = c.extractDocumentation().get(i);
                int rowCount = 2;
                int fillCount = 0;

                try {
                    String empty = "";
                    String f = c.extractDocumentation().get(i).getPath();

                    BufferedReader bufferedReader = new BufferedReader(
                            new FileReader(f));
                    if (bufferedReader.readLine() != null) {
                        mTable.addwRow();

                        String receiveString = "";

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            View view = mTable.getTable().getChildAt(rowCount);
                            if (view instanceof TableRowExpand) {
                                TableRowExpand t = (TableRowExpand) view;
                                String s = receiveString;

                                while (!(t.getChildAt(fillCount) instanceof EditText) && !(t.getChildAt(fillCount) instanceof PictureButton)) {
                                    fillCount++;
                                }

                                if (t.getChildAt(fillCount) instanceof PictureButton) {
                                    PictureButton pb = (PictureButton) t.getChildAt(fillCount);
                                    pb.setPicPath(s);
                                    pb.setText(getString(R.string.showpicture));

                                } else if (t.getChildAt(fillCount) instanceof EditText) {
                                    EditText firstTextView = (EditText) t.getChildAt(fillCount);
                                    firstTextView.setText(s);
                                }else if(t.getChildAt(fillCount) instanceof TextView){
                                    TextView textView = (TextView) t.getChildAt(fillCount);
                                    textView.setText(s);
                                }
                                fillCount++;

                                if (fillCount == (mTable.getHeadLenght()*2)-1) {
                                    mTable.addwRow();
                                    addDate();
                                    fillCount = 0;
                                    rowCount += 2;
                                }
                            }
                        }
                        bufferedReader.close();
                    } else {
                        bufferedReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        layMain.removeAllViews();
        layMain.addView(mTable.getTable());
        addRow = (Button) view.findViewById(R.id.addRowB);
        saveIt = (Button) view.findViewById(R.id.saveStuff);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTable.addwDateRow();
                addDate();
            }
        });

        saveIt = (Button) view.findViewById(R.id.saveStuff);
        saveIt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FileOutputStream outputStream;

                try {

                    outputStream = new FileOutputStream(overwrite);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(outputStream);
                    myOutWriter.write(" " + "/" + "\n");
                    for (int i = 1; i < mTable.getChildCount(); i++) {
                        View view = mTable.getTable().getChildAt(i);

                        if (view instanceof TableRowExpand) {
                            TableRowExpand t = (TableRowExpand) view;
                            String textLine = "";
                            if (t.getChildCount() > 1) {
                                for (int j = 0; j < mTable.getHeadLenght() * 2; j += 2) {
                                    if (t.getChildAt(j) instanceof PictureButton) {
                                        PictureButton pButton = (PictureButton) t.getChildAt(j);
                                        textLine += pButton.getPicPath() + "\n";
                                    } else if (t.getChildAt(j) instanceof EditText) {
                                        EditText text = (EditText) t.getChildAt(j);
                                        textLine += " " + text.getText().toString() + "\n";
                                    } else if (t.getChildAt(j) instanceof TextView){
                                        TextView text = (TextView) t.getChildAt(j);
                                        textLine += " " + text.getText().toString() + "\n";
                                    }

                                }
                                myOutWriter.write(textLine);
                                myOutWriter.flush();
                                outputStream.flush();
                            }
                        }
                    }
                    myOutWriter.close();
                    outputStream.close();
                    Context context = getContext();
                    CharSequence text = getString(R.string.saved);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public void addDate(){
        int lastRow = mTable.getChildCount()-2;

        View view = mTable.getTable().getChildAt(lastRow);
        TableRowExpand tre =(TableRowExpand) view;
        if (tre.getChildAt(0) instanceof TextView) {
            TextView text = (TextView) tre.getChildAt(0);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy", Locale.GERMAN);
            String dateString = format.format(date);
            text.setText(dateString);
        }
    }
}
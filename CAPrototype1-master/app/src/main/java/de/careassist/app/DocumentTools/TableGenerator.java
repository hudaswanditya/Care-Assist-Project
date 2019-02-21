package de.careassist.app.DocumentTools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.careassist.app.R;

import static android.app.Activity.RESULT_OK;
import static de.careassist.app.DocumentTools.PictureButton.REQUEST_IMAGE_CAPTURE;

/**
 * Created by Eko on 08.12.2016.
 */

public class TableGenerator{
    private final Context mContext;
    private TableLayout mTable;
    private TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private TableRowExpand.LayoutParams colParams = new TableRowExpand.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private Activity act;
    private String mCurrentPhotoPath;
    private Image currentPhoto;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private PictureButton currentButton;

    public int getChildCount() {
        return childCount;
    }
    private int childCount=0;
    public int getHeadLenght() {
        return headLenght;
    }
    private int headLenght;
    public int getIdCount() {
        return idCount;
    }

    private int idCount = 0;

    public TableGenerator(Activity activity) {
        mContext = activity.getApplicationContext();
        act = activity;
        mTable = new TableLayout(activity);
        rowParams.setMargins(0, 0, 0, 0);
        colParams.setMargins(0, 0, 0, 0);

        TableLayout.LayoutParams lptable = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT);
        mTable.setLayoutParams(lptable);
    }

    public void addwRow() {
        TableRowExpand tr = new TableRowExpand(mContext);
        childCount++;

        tr.setLayoutParams(rowParams);

        for (int iCol = 0; iCol < headLenght; iCol++) {
            if (iCol == headLenght - 1) {
                final PictureButton pb = new PictureButton(mContext);
                pb.setGravity(Gravity.CENTER);
                pb.setTextAppearance(mContext, R.style.AppButton);

                pb.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                pb.setLayoutParams(colParams);
                pb.setPadding(3, 3, 3, 3);
                pb.setTextSize(10);
                pb.setPicPath("");

                pb.setText(mContext.getString(R.string.addpicture));

                pb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (pb.getPicPath() == "") {
                            currentButton = pb;

                            // Bild wird aufgenommen
                            dispatchTakePictureIntent();
                            pb.setText(mContext.getString(R.string.showpicture));
                           /* BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            Bitmap bitmap = BitmapFactory.decodeFile(pb.getPicPath(), options);
                            if (options.outWidth != -1 && options.outHeight != -1) {
                                // This is an image file.
                                pb.setText(mContext.getString(R.string.showpicture));
                            }
                            else {
                                // This is not an image file.
                                pb.setPicPath("");
                            }*/


                        } else {
                            currentButton = pb;
                            showImage(pb);

                        }
                    }
                });
                tr.addView(pb);
            } else {
                EditText tvCol = new EditText(mContext);
                tvCol.setGravity(Gravity.CENTER);
                tvCol.setPadding(3, 3, 3, 3);
                tvCol.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryText));
                tvCol.setMinWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                tvCol.setLayoutParams(colParams);
                tvCol.setBackgroundColor(mContext.getResources().getColor(R.color.row_background));
                tvCol.setTextSize(14);
                tr.addView(tvCol);

                addColDivider(tr);
            }
        }


        mTable.addView(tr);

        addDivider();
    }


    public void addwDateRow() {
        TableRowExpand tr = new TableRowExpand(mContext);
        childCount++;

        tr.setLayoutParams(rowParams);

        for (int iCol = 0; iCol < headLenght; iCol++) {
            if (iCol == headLenght - 1) {
                final PictureButton pb = new PictureButton(mContext);
                pb.setGravity(Gravity.CENTER);
                pb.setTextAppearance(mContext, R.style.AppButton);
                pb.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                pb.setLayoutParams(colParams);
                pb.setPadding(3, 3, 3, 3);
                pb.setTextSize(10);
                pb.setPicPath("");

                pb.setText(mContext.getString(R.string.addpicture));

                pb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (pb.getPicPath() == "") {
                            currentButton = pb;

                            // Bild wird aufgenommen
                            dispatchTakePictureIntent();
                            pb.setText(mContext.getString(R.string.showpicture));
                           /* BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            Bitmap bitmap = BitmapFactory.decodeFile(pb.getPicPath(), options);
                            if (options.outWidth != -1 && options.outHeight != -1) {
                                // This is an image file.
                                pb.setText(mContext.getString(R.string.showpicture));
                            }
                            else {
                                // This is not an image file.
                                pb.setPicPath("");
                            }*/


                        } else {
                            currentButton = pb;
                            showImage(pb);

                        }
                    }
                });
                tr.addView(pb);
            }else if(iCol == 0) {
                TextView tvCol = new EditText(mContext);
                tvCol.setGravity(Gravity.CENTER);
                tvCol.setPadding(3, 3, 3, 3);
                tvCol.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryText));
                tvCol.setLayoutParams(colParams);
                tvCol.setBackgroundColor(mContext.getResources().getColor(R.color.row_background));
                tvCol.setTextSize(14);
                tr.addView(tvCol);

                addColDivider(tr);
            }else{
                EditText tvCol = new EditText(mContext);
                tvCol.setGravity(Gravity.CENTER);
                tvCol.setPadding(3, 3, 3, 3);
                tvCol.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryText));
                tvCol.setMinWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                tvCol.setLayoutParams(colParams);
                tvCol.setBackgroundColor(mContext.getResources().getColor(R.color.row_background));
                tvCol.setTextSize(14);
                tr.addView(tvCol);

                addColDivider(tr);
            }
        }


        mTable.addView(tr);

        addDivider();
    }
    private void showImage(PictureButton pb) {
        Intent intent = new Intent(mContext,
                ImageActivity.class);
        String message = pb.getPicPath();
        intent.putExtra("PICTURE_ID", message);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }


    public void addRow() {
        TableRowExpand tr = new TableRowExpand(mContext);
        childCount++;


        tr.setLayoutParams(rowParams);
        for (int iCol = 0; iCol < headLenght; iCol++) {
            if (iCol == headLenght - (headLenght - 1)) {
                TextView tvCol = new EditText(mContext);
                tvCol.setGravity(Gravity.CENTER);
                tvCol.setPadding(3, 3, 3, 3);
                tvCol.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryText));
                tvCol.setMaxWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                tvCol.setLayoutParams(colParams);
                tvCol.setBackgroundColor(mContext.getResources().getColor(R.color.row_background));
                tvCol.setTextSize(14);
                tr.addView(tvCol);

                addColDivider(tr);
            } else {
                EditText tvCol = new EditText(mContext);
                tvCol.setGravity(Gravity.CENTER);
                tvCol.setPadding(3, 3, 3, 3);
                tvCol.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryText));
                tvCol.setMaxWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                tvCol.setLayoutParams(colParams);
                tvCol.setBackgroundColor(mContext.getResources().getColor(R.color.row_background));
                tr.addView(tvCol);

                if (iCol < headLenght - 1) {
                    addColDivider(tr);
                }

            }
        }
        mTable.addView(tr);

        addDivider();
    }

    public void addDivider(){

        TableRowExpand tr = new TableRowExpand(mContext);
        tr.setId(idCount);

        tr.setBackgroundColor(mContext.getResources().getColor(R.color.table_background));

        TextView tvCol = new TextView(mContext);
        tvCol.setHeight(1);
        tr.addView(tvCol);

        mTable.addView(tr);
        childCount++;
    }

    private void addColDivider(TableRow tr){

        TextView tvCol = new TextView(mContext);

        TableRowExpand.LayoutParams layoutParams = new TableRowExpand.LayoutParams(1, TableRowExpand.LayoutParams.MATCH_PARENT);
        tvCol.setLayoutParams(layoutParams);
        tvCol.setBackgroundColor(mContext.getResources().getColor(R.color.table_background));
        tr.addView(tvCol);

    }

    public void addHead(String[] data) {
        TableRowExpand tr = new TableRowExpand(mContext);
        headLenght = data.length;

        mTable.setStretchAllColumns(false);
        for(int i = 0; i < headLenght*2; i+=2){
            mTable.setColumnStretchable(i, true);
        }

        tr.setLayoutParams(rowParams);
        childCount++;
        for (int iCol = 0; iCol < headLenght; iCol++) {
            TextView tvCol = new TextView(mContext);
            tvCol.setText(data[iCol]);
            tvCol.setGravity(Gravity.CENTER);
            tvCol.setPadding(3, 3, 3, 3);
            tvCol.setLayoutParams(colParams);
            tvCol.setBackgroundColor(mContext.getResources().getColor(R.color.row_background));
            tvCol.setTextSize(14);
            tvCol.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            tr.addView(tvCol);

            if(iCol < headLenght-1) {
                addColDivider(tr);
            }
        }

        mTable.addView(tr);

        addDivider();
    }


    public TableLayout getTable() {
        return mTable;
    }


    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(act.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                Uri photoURI = FileProvider.getUriForFile(mContext,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                act.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = act.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.println(Log.INFO, ",", storageDir.toString());
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        currentButton.setPicPath(mCurrentPhotoPath);
        return image;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            currentPhoto = (Image) extras.get("data");
        }
    }
}
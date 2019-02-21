package de.careassist.app.DocumentTools;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import de.careassist.app.R;

public class ImageActivity extends AppCompatActivity {
    private ImageView image;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        this.image = (ImageView) findViewById(R.id.woundimage);
        Intent intent = getIntent();
       // String message = intent.getStringExtra();

        s = intent.getStringExtra("PICTURE_ID");

        Uri uri = Uri.parse(s);
        image.setImageURI(uri);

    }
}

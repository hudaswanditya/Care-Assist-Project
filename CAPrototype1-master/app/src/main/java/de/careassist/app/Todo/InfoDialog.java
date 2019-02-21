package de.careassist.app.Todo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.careassist.app.Client;
import de.careassist.app.DataManager;
import de.careassist.app.R;

/**
 * Created by linakriebel on 26.07.17.
 */

public class InfoDialog extends Dialog{

    private Context context;
    private ToDo todo;

    public InfoDialog(Context context, ToDo todo) {
        super(context);
        this.context = context;
        this.todo = todo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_info);
        ListView information = (ListView) findViewById(R.id.information);
        Button ok = (Button) findViewById(R.id.ok);

        ArrayList<String> infoLines = new ArrayList<>();
        String[] lines = todo.getTask().getDescription();
        for(int i=0; i < lines.length; i++){
            String line = "- " + lines[i];
            infoLines.add(line);
        }
        information.setAdapter(new ArrayAdapter<>(context, R.layout.information, R.id.info, infoLines));

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }
}


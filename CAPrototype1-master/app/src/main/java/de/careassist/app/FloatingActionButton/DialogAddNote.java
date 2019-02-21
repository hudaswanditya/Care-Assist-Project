package de.careassist.app.FloatingActionButton;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.careassist.app.ClientViewActivity;
import de.careassist.app.R;
import de.careassist.app.Todo.ToDo;

/**
 * Created by V2 on 25.12.2016.
 */

public class DialogAddNote extends Dialog {

    private ClientViewActivity activity;
    private EditText newNote;
    private AutoCompleteTextView tag;

    private boolean isFixedNote;


    public DialogAddNote(Activity activity) {
        super(activity);
        this.activity = (ClientViewActivity) activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_note);


        final ImageButton fixNote = (ImageButton) findViewById(R.id.fixNoteBtn);
        fixNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFixedNote) {
                    isFixedNote = false;
                    fixNote.setColorFilter(activity.getResources().getColor(R.color.colorSecondaryText));
                } else {
                    isFixedNote = true;
                    fixNote.setColorFilter(activity.getResources().getColor(R.color.colorAccent));
                }
            }
        });
        Button confirm = (Button) findViewById(R.id.dialog_button_positive);
        Button cancel = (Button) findViewById(R.id.dialog_button_negative);
        newNote = (EditText) findViewById(R.id.dialog_input_text);
        tag = (AutoCompleteTextView) findViewById(R.id.dialog_input_tag);
        String[] tags = getAllExistingTags();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.auto_complete_item, tags);

        tag.setAdapter(adapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset errors.
                newNote.setError(null);
                tag.setError(null);

                String sNewNote = newNote.getText().toString().trim();
                String sTag = tag.getText().toString();

                boolean cancel = false;
                View focusView = null;


                // Check if mandatory fields are empty.
                if (sNewNote.isEmpty()) {
                    newNote.setError(activity.getString(R.string.error_field_required));
                    focusView = newNote;
                    cancel = true;
                }

                if (sTag.isEmpty()) {
                    tag.setError(activity.getString(R.string.error_field_required));
                    focusView = tag;
                    cancel = true;
                }

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {
                    //no error
                    if(isFixedNote) activity.addFixedNote(sNewNote, sTag);
                    else activity.addNote(sNewNote, sTag);
                    dismiss();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private String[] getAllExistingTags() {
        Set<String> tags = new HashSet<>();
        for (List<ToDo> items : activity.getClient().getToDos().values()) {
            for (int i = 0; i < items.size(); i++) {
                ToDo toDo = items.get(i);
                tags.add(toDo.getTask().getTag());
            }
        }
        return tags.toArray(new String[tags.size()]);
    }
}

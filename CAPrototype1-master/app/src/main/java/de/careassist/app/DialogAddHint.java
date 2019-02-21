package de.careassist.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by linakriebel on 25.07.17.
 */

public class DialogAddHint extends Dialog {

    private Context context;
    private EditText newHint;
    private Client client;
    private ListView hintView;

    public DialogAddHint(Context context, Client client, ListView hint) {
        super(context);
        this.context = context;
        this.client = client;
        this.hintView = hint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_hint);

        final Button confirm = (Button) findViewById(R.id.dialog_button_positive);
        Button cancel = (Button) findViewById(R.id.dialog_button_negative);

        newHint = (EditText) findViewById(R.id.hint_input_text);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset errors.
                newHint.setError(null);

                String hintString = newHint.getText().toString().trim();

                boolean cancel = false;
                View focusView = null;

                // Check if mandatory fields are empty.
                if (hintString.isEmpty()) {
                    newHint.setError(context.getString(R.string.error_field_required));
                    focusView = newHint;
                    cancel = true;
                }

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {
                    //no error
                    client.getHints().add(hintString);
                    DataManager.getSharedInstance(context).updateClient(client);
                    hintView.setAdapter(hintView.getAdapter());
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
}

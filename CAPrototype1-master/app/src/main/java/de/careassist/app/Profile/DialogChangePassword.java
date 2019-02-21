package de.careassist.app.Profile;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.careassist.app.Carer;
import de.careassist.app.DataManager;
import de.careassist.app.R;

/**
 * Created by Max on 18.07.17.
 *
 * Dialog for changing the password
 */

public class DialogChangePassword extends Dialog {
    private Context c;

    private EditText oldPassword;
    private EditText newPassword1;
    private EditText newPassword2;

    protected DialogChangePassword(Context context) {
        super(context);
        this.c = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_password);

        oldPassword = (EditText) findViewById(R.id.oldPassword);
        newPassword1 = (EditText) findViewById(R.id.newPassword1);
        newPassword2 = (EditText) findViewById(R.id.newPassword2);

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setText(R.string.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setText(R.string.saveContent);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(changePassword()) {
                    //show toast notification
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(R.string.success_changed_password);

                    Toast toast = new Toast(c);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 10);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                    dismiss();
                }
            }
        });
    }

    private boolean changePassword() {
        Carer carer = Carer.getInstance();

        // Reset errors.
        oldPassword.setError(null);
        newPassword1.setError(null);
        newPassword2.setError(null);

        // Store values at the time of the change password attempt.
        String oldP = oldPassword.getText().toString();
        String new1 = newPassword1.getText().toString();
        String new2 = newPassword2.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check if new passwords are valid
        if (new1.length()<4 || new2.length()<4) {
            newPassword1.setError(c.getString(R.string.error_invalid_password));
            focusView = newPassword1;
            cancel = true;
        }

        // Check if new password is empty.
        if (TextUtils.isEmpty(new2)) {
            newPassword2.setError(c.getString(R.string.error_field_required));
            focusView = newPassword2;
            cancel = true;
        }

        // Check if new password is empty.
        if (TextUtils.isEmpty(new1)) {
            newPassword1.setError(c.getString(R.string.error_field_required));
            focusView = newPassword1;
            cancel = true;
        }

        // Check if old password is correct or even empty
        if (!carer.getPassword().equals(oldP)) {
            String error = TextUtils.isEmpty(oldP)?c.getString(R.string.error_field_required):c.getString(R.string.error_incorrect_password);
            oldPassword.setError(error);
            focusView = oldPassword;
            cancel = true;
        }

        // Check if new passwords are equal
        if (!new1.equals(new2)) {
            newPassword1.setError(c.getString(R.string.error_not_equal_passwords));
            focusView = newPassword1;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // no error
            carer.setPassword(new1);
            DataManager.getSharedInstance(c).updateCarer(carer);
            return true;
        }

        return  false;
    }
}

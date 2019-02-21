package de.careassist.app.FloatingActionButton;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.careassist.app.Client;
import de.careassist.app.ClientViewActivity;
import de.careassist.app.GeneralTask;
import de.careassist.app.R;
import de.careassist.app.Todo.ToDo;

/**
 * Created by v2 on 05.01.2017.
 */

public class DialogAddToDo extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Activity activity;
    private DatePicker datePicker;
    private NumberPicker numberPicker;
    List<ToDo> toDos;
    private TextView newTodo;
    private AutoCompleteTextView tagEdittext;
    private EditText descriptionEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        activity = getActivity();
        if (activity instanceof ClientViewActivity){
            Client c = ((ClientViewActivity)activity).getClient();
            toDos = c.getToDos().firstEntry().getValue(); // TODO
        }

        final LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.dialog_add_todo, container, false);
        newTodo = (EditText) rootView.findViewById(R.id.newTodo);
        tagEdittext = (AutoCompleteTextView) rootView.findViewById(R.id.dialog_add_todo_tag);
        descriptionEditText = (EditText) rootView.findViewById(R.id.dialog_add_todo_description);
        Button confirm = (Button) rootView.findViewById(R.id.dialog_todo_button_confirm);
        Button cancel = (Button) rootView.findViewById(R.id.dialog_todo_button_cancel);



        String[] tags = getTags();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.auto_complete_item, tags);

        tagEdittext.setThreshold(1);
        tagEdittext.setAdapter(adapter);
        tagEdittext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.auto_complete_text_view);
                String tag = textView.getText().toString().trim();
                for (int i = 0; i < toDos.size(); i++) {
                    ToDo toDo = toDos.get(i);
                    if (toDo.getTask().getTag().equals(tag)) {
                        String result = "";
                        for (String line : toDo.getTask().getDescription()) {
                            result += line;
                            result += "\n";
                        }
                        descriptionEditText.setText(result);

                        break;
                    }
                }

            }
        });

        datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        Calendar calender = Calendar.getInstance();

        calender.add(Calendar.DATE, 30);

        datePicker.setMaxDate(calender.getTime().getTime());
        datePicker.setOnClickListener(null);
        numberPicker = (NumberPicker) rootView.findViewById(R.id.dialog_number_picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2);

        String[] values = getActivity().getResources().getStringArray(R.array.date_options);
        numberPicker.setDisplayedValues(values);

        Button switcher = (Button) rootView.findViewById(R.id.switch_picker);
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datePicker.getVisibility() == View.GONE) {
                    datePicker.setVisibility(View.VISIBLE);
                    numberPicker.setVisibility(View.GONE);
                } else {
                    datePicker.setVisibility(View.GONE);
                    numberPicker.setVisibility(View.VISIBLE);
                }

                // hide keyboard
                InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Reset errors.
                newTodo.setError(null);
                tagEdittext.setError(null);

                String name = newTodo.getText().toString().trim();
                String tag = tagEdittext.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                boolean cancel = false;
                View focusView = null;


                // Check if mandatory fields are empty.
                if (name.isEmpty()) {
                    newTodo.setError(getString(R.string.error_field_required));
                    focusView = newTodo;
                    cancel = true;
                }

                if (tag.isEmpty()) {
                    tagEdittext.setError(getString(R.string.error_field_required));
                    focusView = tagEdittext;
                    cancel = true;
                }

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {
                    // no error
                    Calendar calendar = Calendar.getInstance();

                    if (datePicker.getVisibility() == View.VISIBLE) {

                        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

                        GeneralTask generalTask = new GeneralTask(name, description.split("\\n"), tag);
                        ToDo toDo = new ToDo(generalTask);
                        ((ClientViewActivity)activity).addToDo(calendar.getTime(), toDo);
                        dismissThis();
                    } else {
                        GeneralTask generalTask = new GeneralTask(name, description.split("\\n"), tag);
                        ToDo toDo = new ToDo(generalTask);
                        if(((ClientViewActivity)activity).addToDo(numberPicker.getValue(), toDo)) {
                            dismissThis();
                        } else {
                            //show toast notification
                            LayoutInflater inflater = activity.getLayoutInflater();
                            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) activity.findViewById(R.id.custom_toast_container));

                            TextView text = (TextView) layout.findViewById(R.id.text);
                            String when = numberPicker.getValue()==1?"morgen":"übermorgen";
                            text.setText("Es wurde noch kein Termin für " + when + " angelegt. Gebe alternativ ein Datum an.");

                            Toast toast = new Toast(activity.getApplicationContext());
                            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 10);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        }
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissThis();
            }
        });

        datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePickerFragment = new FragmentDatePicker();
                datePickerFragment.show(getFragmentManager(), "Date Picker");
            }
        });
        return rootView;
    }

    private String[] getTags() {
        Set<String> tags = new HashSet<>();
        for (int i = 0; i < toDos.size(); i++) {
            ToDo toDo = toDos.get(i);
            tags.add(toDo.getTask().getTag());
        }

        return tags.toArray(new String[tags.size()]);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

//        String myFormat = "dd/MM/yy"; //In which you need put here
//        Locale current = getResources().getConfiguration().locale;
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, current);

//        datePicker.setText(sdf.toString());
//        datePicker.setText("" + day + "." + month + "." + year);
    }

    public void dismissThis() {
        ClientViewActivity ac = (ClientViewActivity) getActivity();
        ac.updateView();
        dismiss();
    }


}

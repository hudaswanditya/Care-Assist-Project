package de.careassist.app;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.sql.Timestamp;

import de.careassist.app.Todo.Note;
import de.careassist.app.Todo.ToDo;
import de.careassist.app.Todo.TodoFragment;

/**
 * Created by V2 on 25.12.2016.
 */

public class DialogShiftTask extends Dialog implements View.OnClickListener {

    private ClientViewActivity activity;
    private Button confirm;
    private Button cancel;
    private ToDo todo;
    private TodoFragment frag;



    public DialogShiftTask(TodoFragment frag, Activity activity, ToDo toDo) {
        super(activity);
        this.activity = (ClientViewActivity) activity;
        this.todo = toDo;
        this.frag = frag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_shift_task);

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(3);

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        confirm = (Button) findViewById(R.id.select_shift);
        confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_shift:
                NumberPicker input = (NumberPicker) findViewById(R.id.number_picker);
                int inputValue = input.getValue();
                GeneralTask task = todo.getTask();
                Note note;
                if (inputValue==0) {
                    todo.setDone(false);
                    todo.setShiftedDays(inputValue);
                    String shiftedNote = "" + task.getName() + " ist wieder aktuell";
                    note = new Note(task.getTag(), shiftedNote, Carer.getInstance().generateFullName(), new Timestamp(System.currentTimeMillis()));
                    task.setNote(note);
                } else {
                    todo.setDone(true);
                    todo.setShiftedDays(inputValue);
                    String day = inputValue == 1 ? " Tag" : " Tage";
                    String shiftedNote = "" + task.getName() + " wurde um " + inputValue + day + " verschoben";
                    note = new Note(task.getTag(), shiftedNote, Carer.getInstance().generateFullName(), new Timestamp(System.currentTimeMillis()));
                    task.setNote(note);
                }

                activity.addNote(note);

                DataManager.getSharedInstance(getContext()).updateClient(activity.getClient());
                frag.updateList();
                dismissDialog();
                break;
            case R.id.cancel:
                dismissDialog();
        }
    }

    private void dismissDialog() {
        dismiss();
    }
}

package de.careassist.app.Todo;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import de.careassist.app.Carer;
import de.careassist.app.ClientViewActivity;
import de.careassist.app.GeneralTask;
import de.careassist.app.R;

public class TodoExpandableListViewAdapter extends BaseExpandableListAdapter {

    private final List<ToDo> mValues;
    private final TodoFragment.OnListFragmentInteractionListener mListener;
//    private final TodoFragment.OnInfoClickedInteractionListener infoListener;
    private Context c;
    private TodoFragment fragment;
    private List<Note> mFixedNotes;
    private ExpandableListView parentListView;


    public TodoExpandableListViewAdapter(Context context, TodoFragment fragment, List<ToDo> listDataHeader, List<Note> notes, TodoFragment.OnListFragmentInteractionListener listener, ExpandableListView listView) {
        this.fragment = fragment;
        c = context;
        mValues = listDataHeader;
        mListener = listener;
//        infoListener = newInfoListener;
        mFixedNotes = notes;
        parentListView = listView;
    }

    @Override
    public int getGroupCount() { return mValues.size(); }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<Note> items = new ArrayList<>();
        for(int i = 0; i < mFixedNotes.size(); i++) {
                String note = mFixedNotes.get(i).getTag();
                String todo = mValues.get(groupPosition).getTask().getTag();
                if(note.equals(todo)){
                    items.add(mFixedNotes.get(i));
                }
            }
        return items.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mValues.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<Note> items = new ArrayList<>();
        for(int i = 0; i < mFixedNotes.size(); i++) {
                String note = mFixedNotes.get(i).getTag();
                String todo = mValues.get(groupPosition).getTask().getTag();
                if(note.equals(todo)){
                    items.add(mFixedNotes.get(i));
                }
            }
        return items.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() { return false; }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View view, final ViewGroup parent) {

        if(view == null){
            LayoutInflater infalInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.fragment_todo, null);
        }
        final View mView = view;
        final TextView mNameView = (TextView) view.findViewById(R.id.name);
        mNameView.setText(mValues.get(groupPosition).getTask().getName());

        ImageButton shiftTask = (ImageButton) view.findViewById(R.id.shift_task);
        final ImageButton mInfo = (ImageButton) view.findViewById(R.id.info);
        CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
        ImageButton addNote = (ImageButton) view.findViewById(R.id.add_note);
        final ToDo currentToDo = mValues.get(groupPosition);

        if (currentToDo.isDone()) {
            mCheckBox.setChecked(true);
            addNote.setVisibility(View.VISIBLE);
            //mView.setBackgroundColor(mView.getResources().getColor(R.color.colorPrimaryLight));
            mInfo.clearColorFilter();
            //shiftTask.setVisibility(View.GONE);
            System.out.println(currentToDo.getTask().getName() + " " + currentToDo.getShiftedDays());
            if (currentToDo.getShiftedDays() > 0) {
                System.out.println("yes");
                shiftTask.setVisibility(View.VISIBLE);
                shiftTask.setColorFilter(ContextCompat.getColor(mView.getContext(), R.color.colorAccent));
            } else {
                shiftTask.setVisibility(View.GONE);
            }
        } else {
            addNote.setVisibility(View.GONE);
        }

        //only show indicator if group has children
        ImageView groupIndicator = (ImageView) mView.findViewById(R.id.indicator);
        if(getChildrenCount(groupPosition) < 1) {
            groupIndicator.setImageResource(R.color.colorPrimary);
        } else {
            if(isExpanded){
                groupIndicator.setImageResource(R.drawable.ic_expand_less_black_24dp);
            } else {
                groupIndicator.setImageResource(R.drawable.ic_expand_more_black_24dp);
            }
        }

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isExpanded) {
                    parentListView.collapseGroup(groupPosition);
                }
                if (!isExpanded){
                    parentListView.expandGroup(groupPosition, true);
                }
            }
        });


        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GeneralTask task = currentToDo.getTask();
                currentToDo.setDone(isChecked);
                mListener.onListFragmentInteraction(-2, true);
                ClientViewActivity activity = (ClientViewActivity) fragment.getActivity();

                if (isChecked) {
                    //mView.setBackgroundColor(mView.getResources().getColor(R.color.grey));
                    Note note = new Note(task.getTag(), "" + task.getName() + " durchgeführt", Carer.getInstance().generateFullName(), new Timestamp(System.currentTimeMillis()));
                    task.setNote(note);
                    activity.addNote(note);
                } else {
                    //mView.setBackgroundColor(mView.getResources().getColor(R.color.transparent));
                    for (int i = 0; i < mValues.size(); i++) {
                        if (mValues.get(i).getTask().getTag().toLowerCase().contains(task.getTag().toLowerCase())) {
                            ArrayList<Note> notes = (ArrayList<Note>) activity.getClient().getNotes();
                            notes.remove(task.getNote());
                        }
                    }
                }
            }
        });

        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog infoDialog = new InfoDialog(fragment.getContext(), currentToDo);
                infoDialog.show();
            }
        });

        shiftTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDatePickerInteraction(fragment, currentToDo);
            }
        });

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                builder.setTitle(R.string.new_note);
                final EditText editText = new EditText(fragment.getContext());

                builder.setView(editText);
                if (currentToDo.getShiftedDays() > 0) {
                    editText.setText(currentToDo.getTask().getShiftedNote());
                } else {
                    editText.setText(currentToDo.getTask().getNote().getContent());
                }

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = editText.getText().toString();
                        if (currentToDo.getShiftedDays() > 0) {
                            currentToDo.getTask().setShiftedNote(input);
                        } else {
                            currentToDo.getTask().getNote().setContent(input);
                        }
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });


        return view;
    }


    public void clearSelection() {
        if (TodoFragment.getOldSelection() != null) {
            TextView name = (TextView) TodoFragment.getOldSelection().findViewById(R.id.name);
            //name.setTextColor(TodoFragment.getOldSelection().getResources().getColor(R.color.colorPrimaryDark));

            ImageButton info = (ImageButton) TodoFragment.getOldSelection().findViewById(R.id.info);
            info.setBackgroundColor(TodoFragment.getOldSelection().getResources().getColor(android.R.color.transparent));
            info.clearColorFilter();

        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater infalInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.note_for_todo_list, null);
        }

        TextView tView = (TextView) view.findViewById(R.id.note_title);
        final Note note = (Note) getChild(groupPosition, childPosition);
        tView.setText(note.getContent());


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

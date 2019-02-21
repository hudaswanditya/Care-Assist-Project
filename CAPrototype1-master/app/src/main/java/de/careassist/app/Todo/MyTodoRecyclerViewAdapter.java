package de.careassist.app.Todo;

import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import de.careassist.app.ClientViewActivity;
import de.careassist.app.DataManager;
import de.careassist.app.R;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTodoRecyclerViewAdapter extends RecyclerView.Adapter<MyTodoRecyclerViewAdapter.ViewHolder> {

    private final List<ToDo> mValues;
    private final TodoFragment.OnListFragmentInteractionListener mListener;
    private final TodoFragment.OnInfoClickedInteractionListener infoListener;
    TodoFragment fragment;


    public MyTodoRecyclerViewAdapter(TodoFragment fragment, List<ToDo> items, TodoFragment.OnListFragmentInteractionListener listener, TodoFragment.OnInfoClickedInteractionListener newInfoListener) {
        this.fragment = fragment;
        mValues = items;
        mListener = listener;
        infoListener = newInfoListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_todo, parent, false);
        return new ViewHolder(view);
    }

    // Called by RecyclerView to display the data at the specified position. This method should update the contents of the itemView to reflect the item at the given position.
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        int note = position;
        final ToDo currentTodo = holder.mItem;
        holder.mNameView.setText(mValues.get(position).getTask().getName());

        if (currentTodo.isDone()) {
            holder.mCheckBox.setChecked(true);
            holder.addNote.setVisibility(View.VISIBLE);
            //holder.addNote.setColorFilter(holder.mView.getResources().getColor(R.color.grey));
            //holder.mView.setBackgroundColor(holder.mView.getResources().getColor(R.color.colorPrimaryLight));
            holder.mInfo.clearColorFilter();
            if (currentTodo.getShiftedDays() > 0) {
                if (currentTodo.getShiftedDays() > 1) {
                    //holder.shiftet.setText("um " + currentTodo.getShiftedDays() + " Tage verschoben");
                    holder.shiftTask.setColorFilter(ContextCompat.getColor(holder.mView.getContext(), R.color.colorAccent));

                } else {
                    //holder.shiftet.setText("um " + currentTodo.getShiftedDays() + " Tag verschoben");
                    holder.shiftTask.setColorFilter(ContextCompat.getColor(holder.mView.getContext(), R.color.colorAccent));
                }
            } else {
                holder.shiftTask.setVisibility(View.GONE);
            }
        } else {
            //holder.shiftTask.setColorFilter(holder.mView.getResources().getColor(R.color.grey));
            //holder.shiftet.setVisibility(View.GONE);
            holder.addNote.setVisibility(View.GONE);
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    if (TodoFragment.getOldSelection() == holder.mView) {
                        mListener.onListFragmentInteraction(-1, true);
                    } else {
                        clearSelection();
                        TodoFragment.setOldSelection(holder.mView);
                        mListener.onListFragmentInteraction(position, currentTodo.isDone());
                        holder.mNameView.setTextColor(ContextCompat.getColor(holder.mView.getContext(), R.color.colorAccent));
                    }
                }
            }
        });


        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentTodo.setDone(isChecked);
                mListener.onListFragmentInteraction(-2, true);
                ClientViewActivity activity = (ClientViewActivity) fragment.getActivity();

                if (isChecked) {
                    //TODO was macht das?
                    holder.mView.setBackgroundColor(ContextCompat.getColor(holder.mView.getContext(), R.color.grey));

                    Note note = new Note(currentTodo.getTask().getTag(), "" + currentTodo.getTask().getName() + " durchgeführt", activity.getCarer().generateFullName(), new Timestamp(System.currentTimeMillis()));
                    currentTodo.getTask().setNote(note);
                    activity.addNote(note);
                    currentTodo.getTask().setNote(note);
                } else {
                    //TODO was macht das?
                    holder.mView.setBackgroundColor(holder.mView.getResources().getColor(R.color.transparent));

                    for (int i = 0; i < mValues.size(); i++) {
                        if (mValues.get(i).getTask().getTag().toLowerCase().contains(currentTodo.getTask().getTag().toLowerCase())) {
//                            long timestamp = holder.mItem.getTimestamp().getDay();
//                            long secondTimestamp = mValues.get(i).getTimestamp().getDay();
//
//                            if (timestamp == secondTimestamp) {
//                                mValues.remove(i);
//                            }
                            ArrayList<Note> notes = (ArrayList<Note>) activity.getClient().getNotes();
                            notes.remove(currentTodo.getTask().getNote());
                        }
                    }
                    currentTodo.setShiftedDays(0);

                }
                DataManager.getSharedInstance(activity.getApplicationContext()).updateClient(activity.getClient());
            }
        });

        holder.mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                TodoFragment.setOldSelection(holder.mView);
                holder.mInfo.setColorFilter(ContextCompat.getColor(holder.mView.getContext(), R.color.colorAccent));
                if (null != infoListener) {
                    infoListener.onInfoClickedListener(position, currentTodo.isDone());
                }
            }
        });

        holder.shiftTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDatePickerInteraction(fragment, currentTodo);

            }
        });

        holder.addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                builder.setTitle(R.string.new_note);
                final EditText editText = new EditText(fragment.getContext());

                builder.setView(editText);
                if (currentTodo.getShiftedDays() > 0) {
                    editText.setText(currentTodo.getTask().getShiftedNote());
                } else {
                    editText.setText(currentTodo.getTask().getNote().getContent());
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
                        if (currentTodo.getShiftedDays() > 0) {
                            currentTodo.getTask().setShiftedNote(input);
                        } else {
                            currentTodo.getTask().getNote().setContent(input);
                        }

                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }

    public void clearSelection() {
        if (TodoFragment.getOldSelection() != null) {
            TextView name = (TextView) TodoFragment.getOldSelection().findViewById(R.id.name);
            name.setTextColor(TodoFragment.getOldSelection().getResources().getColor(R.color.colorPrimaryDark));

            ImageButton info = (ImageButton) TodoFragment.getOldSelection().findViewById(R.id.info);
            info.setBackgroundColor(TodoFragment.getOldSelection().getResources().getColor(android.R.color.transparent));
            info.clearColorFilter();

        }
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        //public final TextView shiftet;
        public final ImageButton shiftTask;
        public final ImageButton mInfo;
        public final ImageButton addNote;
        public final CheckBox mCheckBox;
        public ToDo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            //shiftet = (TextView) view.findViewById(R.id.shiftet);
            shiftTask = (ImageButton) view.findViewById(R.id.shift_task);
            mInfo = (ImageButton) view.findViewById(R.id.info);
            mCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
            addNote = (ImageButton) view.findViewById(R.id.add_note);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}

package de.careassist.app.Todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.careassist.app.R;

/**
 *
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;

    public MyNoteRecyclerViewAdapter(List<Note> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTagView.setText(mValues.get(position).getTag());
        holder.mContentView.setText(mValues.get(position).getContent());

        Date date = new Date(mValues.get(position).getTimestamp().getTime());

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy, HH:mm", Locale.GERMAN);

        String dateString = format.format(date);

        holder.mTimestampView.setText(dateString+" Uhr");

        holder.mCarerView.setText(mValues.get(position).getCarerName());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTagView;
        public final TextView mContentView;
        public final TextView mCarerView;
        public final TextView mTimestampView;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTagView = (TextView) view.findViewById(R.id.tag);
            mContentView = (TextView) view.findViewById(R.id.content);
            mCarerView = (TextView) view.findViewById((R.id.carer));
            mTimestampView = (TextView) view.findViewById(R.id.time);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

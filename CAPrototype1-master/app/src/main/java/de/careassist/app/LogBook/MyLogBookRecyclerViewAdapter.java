package de.careassist.app.LogBook;

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
import de.careassist.app.Todo.Note;

/**

 * TODO: Replace the implementation with code for your data type.
 */
public class MyLogBookRecyclerViewAdapter extends RecyclerView.Adapter<MyLogBookRecyclerViewAdapter.LogBookItemHolder> {

    private List<Note> mValues;



    public MyLogBookRecyclerViewAdapter(List<Note> notes) {
        initializeList(notes);
    }

    private void initializeList(List<Note> values) {
        this.mValues = values;
    }

    public List<Note> getmValues() {
        return mValues;
    }

    @Override
    public void onBindViewHolder(final LogBookItemHolder holder, int position) {
        Note note = mValues.get(position);

        // DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        // df.format()
        Date date = new Date(note.getTimestamp().getTime());

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy, HH:mm", Locale.GERMAN);

        String dateString = format.format(date);
        holder.date.setText(dateString+" Uhr");
        //holder.date.setText(note.getTimestamp().toString());
        holder.tag.setText(note.getTag());
        holder.carer.setText(note.getCarerName());
        holder.content.setText(note.getContent());
    }

    @Override
    public LogBookItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_logbook_item, parent, false);
        return new LogBookItemHolder(view);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public Note getItem(int pos) {
        return this.mValues.get(pos);
    }


    public class LogBookItemHolder extends RecyclerView.ViewHolder {
        public final TextView date;
        public final TextView tag;
        public final TextView content;
        public final TextView carer;

        public LogBookItemHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.frag_logB_date);
            tag = (TextView) view.findViewById(R.id.frag_logB_tag);
            content = (TextView) view.findViewById(R.id.frag_logB_content);
            carer = (TextView) view.findViewById(R.id.frag_logB_carer);
        }
    }
}

package de.careassist.app.LogBook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.careassist.app.R;

/**
 * Created by dsfd on 29.12.2016.
 */

public class LogBookHeadline extends Fragment {

        private TextView time;
        private TextView note;
        private TextView tag;
        private TextView carer;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_logbook_item, container, false);

            time = (TextView) view.findViewById(R.id.frag_logB_date);
            TextViewCompat.setTextAppearance(time,R.style.AppTextNormalLight);
            time.setTextSize(16);
            time.setText(R.string.date);

            note = (TextView) view.findViewById(R.id.frag_logB_content);
            TextViewCompat.setTextAppearance(note,R.style.AppTextNormalLight);
            note.setTextSize(16);
            note.setText(R.string.note);

            tag = (TextView) view.findViewById(R.id.frag_logB_tag);
            TextViewCompat.setTextAppearance(tag,R.style.AppTextNormalLight);
            tag.setTextSize(16);
            tag.setText(R.string.tag);

            carer = (TextView) view.findViewById(R.id.frag_logB_carer);
            TextViewCompat.setTextAppearance(carer,R.style.AppTextNormalLight);
            carer.setTextSize(16);
            carer.setText(R.string.carer);

            return view;
        }
}

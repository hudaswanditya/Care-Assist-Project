package de.careassist.app.Medication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import de.careassist.app.R;

import java.util.List;

/**

 * TODO: Replace the implementation with code for your data type.
 */
public class MyMedicineListRecyclerViewAdapter extends RecyclerView.Adapter<MyMedicineListRecyclerViewAdapter.ViewHolder> {

    private final List<Medicine> medicine;
    View view;
    //private final MedicineListFragment.OnListFragmentInteractionListener mListener;

    public MyMedicineListRecyclerViewAdapter(List<Medicine> items) {
        medicine = items;
        //mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_medication, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = medicine.get(position);
        holder.substance.setText(holder.mItem.getSubstance());
        holder.intensity.setText(holder.mItem.getIntensity());
        holder.form.setText(holder.mItem.getForm());

        holder.morning.setText(""+holder.mItem.getMornings());
        holder.noon.setText(""+holder.mItem.getNoon());
        holder.afternoon.setText(""+holder.mItem.getAfternoon());
        holder.night.setText(""+holder.mItem.getNight());
        holder.unit.setText(holder.mItem.getUnit());
        holder.information.setText(holder.mItem.getInformatio());
        holder.reason.setText(holder.mItem.getReason());
/*
        if(position % 2 == 0){
            holder.mView.setBackgroundColor(view.getResources().getColor(R.color.colorPrimaryLight));
        }
*/
        /*
        if(position == (medicine.size()-1)){
            holder.lastStroke.setBackgroundColor(view.getContext().getResources().getColor(R.color.transparent));
        }*/

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicine.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView substance;
        public final TextView intensity;
        public final TextView form;
        public final TextView morning;
        public final TextView noon;
        public final TextView afternoon;
        public final TextView night;
        public final TextView unit;
        public final TextView information;
        public final TextView reason;
        public final FrameLayout frame;
        //public final View lastStroke;
        public Medicine mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            substance = (TextView) view.findViewById(R.id.substance);
            intensity = (TextView) view.findViewById(R.id.intensity);
            form = (TextView) view.findViewById(R.id.form);
            morning = (TextView) view.findViewById(R.id.morning);
            noon = (TextView) view.findViewById(R.id.noon);
            afternoon = (TextView) view.findViewById(R.id.afternoon);
            night = (TextView) view.findViewById(R.id.night);
            unit = (TextView) view.findViewById(R.id.unit);
            information = (TextView) view.findViewById(R.id.information);
            reason = (TextView) view.findViewById(R.id.reason);
            frame = (FrameLayout) view.findViewById(R.id.timeContainer);
            //lastStroke = (View) view.findViewById(R.id.lastStroke);
        }
    }
}

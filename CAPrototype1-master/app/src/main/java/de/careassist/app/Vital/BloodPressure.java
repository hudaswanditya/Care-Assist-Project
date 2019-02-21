package de.careassist.app.Vital;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

import de.careassist.app.ClientViewActivity;
import de.careassist.app.R;

/**
 * Created by dsfd on 28.01.2017.
 */

public class BloodPressure extends Fragment {

    private View view;
    private SimpleDateFormat format;
    private TreeMap<Date, Integer> diastolicValues;
    private TreeMap<Date, Integer> systolicValues;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.graph_layout, container, false);

        VitalValues v = ((ClientViewActivity) getActivity()).getClient().getVital();

        LineGraphSeries<DataPoint> diastolicB = new LineGraphSeries<>();
        diastolicValues = v.getDiastolicValues();
        LineGraphSeries<DataPoint> systolicB = new LineGraphSeries<>();
        systolicValues = v.getSystolicValues();

        GraphView graph = (GraphView) view.findViewById(R.id.graph);

        DataPoint[] diastolicData = new DataPoint[diastolicValues.size()];

        int i = 0;
        for (Date date : diastolicValues.keySet()) {
            diastolicData[i] = new DataPoint(date, diastolicValues.get(date));
            i++;
        }

        diastolicB.resetData(diastolicData);

        diastolicB.setTitle("Diastolischer Blutdruck");
        diastolicB.setColor(getResources().getColor(R.color.colorPrimary));
        diastolicB.setDrawDataPoints(true);
        diastolicB.setBackgroundColor(R.color.transparent);
        diastolicB.setDrawBackground(false);
        diastolicB.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                LayoutInflater inflater = getLayoutInflater(getArguments());
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView title = (TextView) layout.findViewById(R.id.title);
                title.setText("Diastolischer Blutdruck: ");

                TextView value = (TextView) layout.findViewById(R.id.value);
                value.setText("Wert: " + dataPoint.getY() + " mmHg");

                setToast(dataPoint, layout);
            }
        });


        DataPoint[] systolicData = new DataPoint[systolicValues.size()];

        i = 0;
        for (Date date : systolicValues.keySet()) {
            systolicData[i] = new DataPoint(date, systolicValues.get(date));
            i++;
        }

        systolicB.resetData(systolicData);

        systolicB.setTitle("Systolischer Blutdruck");
        systolicB.setColor(getResources().getColor(R.color.colorPrimaryDark));
        systolicB.setDrawDataPoints(true);
        systolicB.setBackgroundColor(R.color.colorAccent);
        systolicB.setDrawBackground(false);
        systolicB.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                LayoutInflater inflater = getLayoutInflater(getArguments());
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView title = (TextView) layout.findViewById(R.id.title);
                title.setText("Systolischer Blutdruck: ");

                TextView value = (TextView) layout.findViewById(R.id.value);
                value.setText("Wert: " + dataPoint.getY() + " mmHg");

                setToast(dataPoint, layout);
            }
        });

        graph.addSeries(systolicB);
        graph.addSeries(diastolicB);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), dateFormat));
        graph.getGridLabelRenderer().setNumHorizontalLabels(7);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Datum");
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(true);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.argb(0, 0, 0, 0));

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.onDataChanged(true, true);

        graph.getViewport().setMinX(diastolicValues.firstKey().getTime());
        graph.getViewport().setMaxX(diastolicValues.lastKey().getTime());

        graph.getViewport().setMinY(65);
        graph.getViewport().setMaxY(190);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setVerticalAxisTitle("mmHg");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);

        format = new SimpleDateFormat("dd.MM.yy, HH:mm", Locale.GERMAN);

        return view;
    }

    private void setToast(DataPointInterface dataPoint, View layout) {

        Date currentTime = new Date((long) dataPoint.getX());

        String dateString = format.format(currentTime);

        TextView time = (TextView) layout.findViewById(R.id.time);
        time.setText("Zeit: " + dateString + " Uhr");

        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void valueChanged(int value, int secondValue){

        Date now = Calendar.getInstance().getTime();

        systolicValues.put(now, value);
        diastolicValues.put(now, secondValue);
    }
}

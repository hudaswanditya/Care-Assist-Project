package de.careassist.app.dummy;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import de.careassist.app.Vital.VitalValues;

/**
 * Created by dsfd on 19.01.2017.
 */

public class DummyVitalValues {

    public VitalValues vitalValues;

    public DummyVitalValues(){

        TreeMap<Date, Integer> diastolicValues = new TreeMap<>();
        TreeMap<Date, Integer> systolicValues = new TreeMap<>();
        TreeMap<Date, Double> tempValues = new TreeMap<>();
        TreeMap<Date, Integer> bloodSugarValues = new TreeMap<>();


        // ZEITEN

        Date[] date = new Date[31];
        int dateSize = date.length;
        Calendar calendar = Calendar.getInstance();
        for(int i = dateSize-1; i>=0; i--) {
            calendar.add(Calendar.DATE, -1);
            date[i] = calendar.getTime();
        }

        // BLUTDRUCK

        int[] diastolicValues1 = new int[]{79, 81, 82, 78, 79, 77, 78};
        int diastolicValues1Size = diastolicValues1.length;
        for(int i = 0; i < diastolicValues1Size; i++){
            diastolicValues.put(date[dateSize - diastolicValues1Size + i], diastolicValues1[i]);
        }

        int[] systolicValues1 = new int[]{119, 122, 120, 119, 119, 115, 116};
        int systolicValues1Size = systolicValues1.length;
        for(int i = 0; i < systolicValues1Size; i++){
            systolicValues.put(date[dateSize - systolicValues1Size + i], systolicValues1[i]);
        }


        // BLUTZUCKER

        int[] bloodSugarValues1 = new int[]{
                106, 100, 97, 99,
                136, 104, 142, 119,
                110, 68, 81, 86,
                114, 110, 91, 167,
                94, 83, 78, 98,
                103, 125, 130, 111,
                95, 98, 120, 130,
        };
        int bloodSugarValues1Size = bloodSugarValues1.length;
        for (int i = 0; i < bloodSugarValues1Size; i++) {
            bloodSugarValues.put(date[dateSize - bloodSugarValues1Size + i], bloodSugarValues1[i]);
        }


        // TEMPERATUR

        double[] tempValues1 = new double[]{36.5, 36.25, 37.5, 36.7, 35.8, 36.5, 36};
        int tempValues1Size = tempValues1.length;
        for (int i = 0; i < tempValues1Size; i++) {
            tempValues.put(date[dateSize - tempValues1Size + i], tempValues1[i]);
        }

        vitalValues = new VitalValues(diastolicValues, systolicValues, tempValues, bloodSugarValues);
    }

}

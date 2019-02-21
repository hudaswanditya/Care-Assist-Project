package de.careassist.app.dummy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeMap;

import de.careassist.app.Appointment;
import de.careassist.app.Carer;
import de.careassist.app.R;


/**
 * Created by Max on 19.06.17.
 */

public class DummyCarer {

    public static final List<Carer> ITEMS = new ArrayList<>();

    static {

        // Calender Instance and trim date
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        // general appointment variations
        ArrayList<Appointment> appointments_1 = new ArrayList<>();
        appointments_1.add(new Appointment(1, "8:30", "9:00"));
        appointments_1.add(new Appointment(2, "9:30", "10:15"));
        appointments_1.add(new Appointment(5, "11:30", "12:15"));

        ArrayList<Appointment> appointments_2 = new ArrayList<>();
        appointments_2.add(new Appointment(3, "8:30", "9:00"));
        appointments_2.add(new Appointment(4, "9:30", "10:00"));
        appointments_2.add(new Appointment(5, "12:30", "14:45"));

        ArrayList<Appointment> appointments_3 = new ArrayList<>();
        appointments_3.add(new Appointment(5, "8:30", "9:00"));
        appointments_3.add(new Appointment(3, "9:30", "10:00"));
        appointments_3.add(new Appointment(1, "12:30", "13:00"));
        appointments_3.add(new Appointment(4, "15:30", "16:00"));

        ArrayList<Appointment> appiontments_4 = new ArrayList<>();
        appiontments_4.add(new Appointment(0, "8:30", "9:00"));
        appiontments_4.add(new Appointment(1, "9:30", "10:00"));
        appiontments_4.add(new Appointment(2, "10:30", "11:00"));
        appiontments_4.add(new Appointment(3, "11:30", "12:00"));
        appiontments_4.add(new Appointment(4, "13:30", "14:00"));
        appiontments_4.add(new Appointment(5, "15:30", "16:00"));
        appiontments_4.add(new Appointment(6, "16:00", "16:30"));

        TreeMap<Date, List<Appointment>> allClientsToday = new TreeMap<>();
        allClientsToday.put(calendar.getTime(), appiontments_4);

        TreeMap<Date, List<Appointment>> appointmentMap = new TreeMap<>();
        appointmentMap.put(calendar.getTime(), appointments_3);
        calendar.add(Calendar.DATE, -1);
        appointmentMap.put(calendar.getTime(), appointments_2);
        calendar.add(Calendar.DATE, -1);
        appointmentMap.put(calendar.getTime(), appointments_1);
        calendar.add(Calendar.DATE, 2);
        appointmentMap.put(calendar.getTime(), appointments_1);
        calendar.add(Calendar.DATE, 3);
        appointmentMap.put(calendar.getTime(), appointments_1);
        calendar.add(Calendar.DATE, 4);
        appointmentMap.put(calendar.getTime(), appointments_2);



        // Carer 0 -> Gregor Muster
        ITEMS.add(new Carer(0, "Muster", "Gregor", "test", "test", "Pflegedienstleitung", R.drawable.carer_gregor, allClientsToday));

        // Carer 1 -> Manuela Doe
        ITEMS.add(new Carer(1, "Doe", "Manuela", "manuela@project-careassist.de", "1234", "Pflegerin", R.drawable.carer_manuela,  appointmentMap));

        // Carer 2 -> Christian Hood
        ITEMS.add(new Carer(2, "Hood", "Christian", "robin@project-careassist.de", "1234", "Pfleger", R.drawable.carer_christian, appointmentMap));

        // Carer 3 -> Kathrin Zimmermann
        ITEMS.add(new Carer(3, "Zimmermann", "Kathrin", "kathrin@project-careassist.de", "1234", "Pflegehelferin", R.drawable.carer_kathrin, appointmentMap));

        // Carer 4 -> Helga Anders
        ITEMS.add(new Carer(4, "Anders", "Helga", "helga@project-careassist.de", "1234", "Pflegerin", R.drawable.carer_helga, appointmentMap));

    }
}

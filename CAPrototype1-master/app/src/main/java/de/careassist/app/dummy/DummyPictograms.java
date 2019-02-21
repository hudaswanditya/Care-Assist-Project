package de.careassist.app.dummy;

import java.util.HashMap;

import de.careassist.app.R;

/**
 * Created by dsfd on 28.01.2017.
 */

public class DummyPictograms {

    public static final HashMap<Integer, String> pictogramDescriptions = new HashMap<>();

    static {

        pictogramDescriptions.put(R.drawable.ear_, "Schwerhörig");
        pictogramDescriptions.put(R.drawable.blizzard_, "Leicht reizbar");
        pictogramDescriptions.put(R.drawable.eye_, "Probleme bei dem Sehen");
        pictogramDescriptions.put(R.drawable.mouth__, "Spricht gerne viel");
        pictogramDescriptions.put(R.drawable.brain, "Demenz");

    }

}

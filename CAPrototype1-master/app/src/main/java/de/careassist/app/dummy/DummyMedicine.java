package de.careassist.app.dummy;

import java.util.ArrayList;
import java.util.List;

import de.careassist.app.Medication.Medicine;

/**
 * Created by dsfd on 29.12.2016.
 */

public class DummyMedicine {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Medicine> ITEMS_PRESCRIBED = new ArrayList<>();
    public static final List<Medicine> ITEMS_TEMPORARY = new ArrayList<>();
    public static final List<Medicine> ITEMS_SELF_ORDERED = new ArrayList<>();

    public static final List<Medicine> ITEMS_PRESCRIBED_Erna = new ArrayList<>();
    public static final List<Medicine> ITEMS_PRESCRIBED_Ernst = new ArrayList<>();
    public static final List<Medicine> ITEMS_PRESCRIBED_Helga = new ArrayList<>();
    public static final List<Medicine> ITEMS_PRESCRIBED_Emma = new ArrayList<>();
    public static final List<Medicine> ITEMS_PRESCRIBED_Hans = new ArrayList<>();
    public static final List<Medicine> ITEMS_PRESCRIBED_Karl = new ArrayList<>();
    public static final List<Medicine> ITEMS_PRESCRIBED_Irmgard = new ArrayList<>();

    static {
        addErnaMedication();
        addErnstMedication();
        addHelgaMedication();
        addEmmaMedication();
        addHansMedication();
        addKarlMedication();
        addIrmgardMedication();

        ITEMS_PRESCRIBED.add(new Medicine("Levothyroxin", "100 µg", "Tablette", 1, 0, 0, 0, "Stück", "morgens nüchtern, 30 Min. vor dem Frühstück", "Schilddrüse"));
        ITEMS_PRESCRIBED.add(new Medicine("Ramipril/Hydrochlorothiazid", "5/25 mg", "Tablette", 1, 0, 0, 0, "Stück", "", "Bluthochdruck"));
        ITEMS_PRESCRIBED.add(new Medicine("Ramipril", "5 mg", "Tablette", 0, 0, 1, 0, "Stück", "", "Bluthochdruck"));
        ITEMS_PRESCRIBED.add(new Medicine("Phenprocoumon", "3 mg", "Tablette", 0, 0, 0, 0, "Stück", "nach Marcumarplan", "Blutverdünnung"));
        ITEMS_PRESCRIBED.add(new Medicine("Metamizol", "500 mg", "Tablette", 0, 0, 0, 0, "Stück", "max. drei Tabletten am Tag", "Schmerzen"));
        ITEMS_PRESCRIBED.add(new Medicine("Tiotropium", "18 µg", "Spray", 1, 0, 0, 0, "Hübe", "", "Chronische Lungenerkrankung"));
        ITEMS_PRESCRIBED.add(new Medicine("Macrogol", "13,12 g", "Pulver", 1, 0, 0, 0, "Btl.", "ausreichend dazu trinken", "Stuhlgang"));

        ITEMS_TEMPORARY.add(new Medicine("Clarithromycin", "250 mg", "Tabletten", 0, 0, 0, 0, "Stück", "alle 12 Stunden eine, von 01.04.-06.04.", "Bronchitis"));

        ITEMS_SELF_ORDERED.add(new Medicine("Paracetamol", "500 mg", "Tabletten", 0, 0, 0, 0, "max. 3x tgl.", "", "Schmerzen, Fieber"));
        ITEMS_SELF_ORDERED.add(new Medicine("Ibuprofen", "600 mg", "Filmtablette", 0, 0, 0, 0, "max 3x tgl.", "", "Schmerzen, Fieber, Entzündungen"));
        ITEMS_SELF_ORDERED.add(new Medicine("Baldrian Dragees", "Dragee", "", 0, 0, 0, 0, "1x tgl.", "", "Einschlafprobleme"));
        ITEMS_SELF_ORDERED.add(new Medicine("Imodium akut", "2 mg", "Hartkapsel", 0, 0, 0, 0, "max. 6x tgl.", "Zu Beginn 2 Hartkapseln, dann jeweils 1 Hartkapsel pro ungeformtem Stuhl", "akuter Durchfall"));
        ITEMS_SELF_ORDERED.add(new Medicine("Gelymyrtol", "300 mg", "Weichkapsel", 0, 0, 0, 0, "3-4x tgl.", "Eine Weichkapsel eine halbe Stunde vor dem Essen mit reichlich kalter Flüssigkeit einnehmen", "Schleimlösung bei Bronchitis"));
        ITEMS_SELF_ORDERED.add(new Medicine("Johanniskraut", "425 mg", "Hartkapsel", 0, 0, 0, 0, "2x tgl.", "Eine Hartkapsel mit ausreichend Flüssigkeit einnehmen. Regelmäßige Einnahme zu gleichen Zeiten.", "depressive Verstimmungen"));
        ITEMS_SELF_ORDERED.add(new Medicine("Cetirizin", "10 mg", "Filmtablette", 0, 0, 0, 0, "1x tgl.", "Tablette vorzugsweise am Abend einnehmen. Kann Müdigkeit hervorrufen.", "Heuschnupfen, allergische Erkrankungen"));

    }

    private static void addErnaMedication(){

        ITEMS_PRESCRIBED_Erna.add(new Medicine("Thyroxin", "150 µg", "Tabletten", 1,0,0,0, "1x tgl.", "", "bei Schilddrüsenunterfunktion"));
        ITEMS_PRESCRIBED_Erna.add(new Medicine("Metropolol", "47,5 mg", "Retard-Tabletten", 1,0,0,1, "2x tgl.", "", "bei Bluthochdruck"));
        ITEMS_PRESCRIBED_Erna.add(new Medicine("Protaphane", "20 IE", "Zylinderampullen", 1,0,0,0, "1x tgl.", "Injektionen immer subkutan (unter die Haut), niemals intravenös oder intramuskulär injezieren.", "bei Diabetis mellitus"));

    }

    private static void addErnstMedication(){

        ITEMS_PRESCRIBED_Ernst.add(new Medicine("Salbutamol Inhalationslösung", "20 Tropfen", "Inhalationslösung", 1,0,1,0, "2x tgl.", "zusätzlich 5 ml NaCL 0.9%", "bei COPD"));
        ITEMS_PRESCRIBED_Ernst.add(new Medicine("Tiotropium", "18 µg", "Inhalationslösung", 1, 0, 0, 0, "1x tgl.", "Bei jeder Anwendung 2 Hübe. Das Arzneimittel wirkt 24h. Nur einmal am Tag, möglichst zur gleichen Zeit anwenden.", "bei Chronischen Lungenerkrankungen, COPD"));
        ITEMS_PRESCRIBED_Ernst.add(new Medicine("Metropolol", "95 mg", "Retard-Tabletten", 1,0,0,1, "2x tgl.", "", "bei Bluthochdruck"));
        ITEMS_PRESCRIBED_Ernst.add(new Medicine("Soledum", "100 mg", "Kapseln", 1,0,0,1, "2x tgl.", "", "zur Schleimlösung, entzündungshemmend"));
        ITEMS_PRESCRIBED_Ernst.add(new Medicine("Morphin", "10 mg", "Retard-Kapseln", 1,0,0,1, "2x tgl.", "", "bei starken Schmerzen"));

    }

    private static void addHelgaMedication(){

        ITEMS_PRESCRIBED_Helga.add(new Medicine("Thyroxin", "150 µg", "Tabletten", 1,0,0,0, "1x tgl.", "", "bei Schilddrüsenunterfunktion"));
        ITEMS_PRESCRIBED_Helga.add(new Medicine("Ramipril/Hydrochlorothiazid", "5/25 mg", "Tabletten", 1, 0, 0, 0, "1x tgl.", "Mit reichlich Flüssigkeit einnehmen.", "bei Bluthochdruck"));
        ITEMS_PRESCRIBED_Helga.add(new Medicine("Ramipril", "2,5 mg", "Tabletten", 1, 0, 0 , 0, "1x tgl.", "", "bei Bluthochdruck"));
        ITEMS_PRESCRIBED_Helga.add(new Medicine("Phenprocoumon", "3 mg", "Tabletten", 1, 0, 0, 0, "1x tgl.", "", "zur Blutverdünnung"));
        ITEMS_PRESCRIBED_Helga.add(new Medicine("Metamizol", "500 mg", "Filmtablette", 1, 0, 0, 0, "1-2x tgl.", "", "bei Schmerzen, Fieber"));
        ITEMS_PRESCRIBED_Helga.add(new Medicine("Tiotropium", "18 µg", "Inhalationslösung", 1, 0, 0, 0, "1x tgl.", "Bei jeder Anwendung 2 Hübe. Das Arzneimittel wirkt 24h. Nur einmal am Tag, möglichst zur gleichen Zeit anwenden.", "bei Chronischen Lungenerkrankungen, COPD"));
        ITEMS_PRESCRIBED_Helga.add(new Medicine("Macrogol", "13,7 g", "Pulverbeutel", 1, 0, 0, 0, "1-3x tgl.", "Ausreichend dazu trinken.", "bei Verstopfungen"));

    }

    private static void addEmmaMedication(){


        ITEMS_PRESCRIBED_Emma.add(new Medicine("Protaphane", "20 IE", "Zylinderampullen", 1,0,0,0, "1x tgl.", "Injektionen immer subkutan (unter die Haut), niemals intravenös oder intramuskulär injezieren.", "bei Diabetis mellitus"));
        ITEMS_PRESCRIBED_Emma.add(new Medicine("ACE Hemmer Captopril", "25 mg", "Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Emma.add(new Medicine("Diuretika Furosemid", "40 mg", "Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Emma.add(new Medicine("Betablocker Metoprolol", "47,5 mg", "Retard-Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));

    }

    private static void addHansMedication(){

        ITEMS_PRESCRIBED_Hans.add(new Medicine("ACE Hemmer Captopril", "25 mg", "Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Hans.add(new Medicine("Diuretika Furosemid", "40 mg", "Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Hans.add(new Medicine("Betablocker Metoprolol", "47,5 mg", "Retard-Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Hans.add(new Medicine("Blutverdünner Marcumar", "3 mg", "Tabletten", 1,0,0,0, "1x tgl.", "nach Marcumar-Plan", "zur Blutverdünnung"));
        ITEMS_PRESCRIBED_Hans.add(new Medicine("Metropolol", "95 mg", "Retard-Tabletten", 1,0,0,1, "2x tgl.", "", "bei Bluthochdruck"));

    }

    private static void addKarlMedication(){

        ITEMS_PRESCRIBED_Karl.add(new Medicine("ACE Hemmer Captopril", "25 mg", "Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Karl.add(new Medicine("Diuretika Furosemid", "40 mg", "Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Karl.add(new Medicine("Betablocker Metoprolol", "47,5 mg", "Retard-Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Helga.add(new Medicine("Metamizol", "500 mg", "Filmtablette", 1, 0, 0, 0, "1-2x tgl.", "", "bei Schmerzen, Fieber"));

    }

    private static void addIrmgardMedication(){

        ITEMS_PRESCRIBED_Irmgard.add(new Medicine("ACE Hemmer Captopril", "25 mg", "Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Irmgard.add(new Medicine("Diuretika Furosemid", "40 mg", "Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Irmgard.add(new Medicine("Betablocker Metoprolol", "47,5 mg", "Retard-Tabletten", 1,0,1,0, "2x tgl.", "", "bei Herzinsuffizienz"));
        ITEMS_PRESCRIBED_Irmgard.add(new Medicine("Metamizol", "500 mg", "Filmtablette", 1, 0, 0, 0, "1-2x tgl.", "", "bei Schmerzen, Fieber"));
        ITEMS_PRESCRIBED_Irmgard.add(new Medicine("Salbutamol Inhalationslösung", "20 Tropfen", "Inhalationslösung", 1,0,1,0, "2x tgl.", "zusätzlich 5 ml NaCL 0.9%", "bei COPD"));
        ITEMS_PRESCRIBED_Irmgard.add(new Medicine("Tiotropium", "18 µg", "Inhalationslösung", 1, 0, 0, 0, "1x tgl.", "Bei jeder Anwendung 2 Hübe. Das Arzneimittel wirkt 24h. Nur einmal am Tag, möglichst zur gleichen Zeit anwenden.", "bei Chronischen Lungenerkrankungen, COPD"));

    }
}

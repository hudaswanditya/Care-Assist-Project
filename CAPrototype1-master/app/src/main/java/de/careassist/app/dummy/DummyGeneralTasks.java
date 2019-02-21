package de.careassist.app.dummy;

import java.util.ArrayList;
import java.util.List;

import de.careassist.app.GeneralTask;

/**
 * Created by Max on 20.07.17.
 *
 */

class DummyGeneralTasks {
    static List<GeneralTask> generalTasks1;
    static List<GeneralTask> generalTasks2;
    static List<GeneralTask> generalTasks3;
    static List<GeneralTask> generalTasks4;
    static List<GeneralTask> generalTasks5;
    static List<GeneralTask> generalTasks6;
    static List<GeneralTask> generalTasks7;

    static {

        // general decription
        String[] ganzkörperwaschung = {"Waschen, Duschen, Baden",
                "Mund-, Zahn- und Lippenpflege",
                "Rasieren",
                "Hautpflege",
                "Haarpflege (Kämmen, ggf. Waschen)",
                "Nagelpflege",
                "An- und Auskleiden incl. An- und Ablegen von Körperersatzstücken",
                "Vorbereiten/Aufräumen des Pflegebereiches"};
        String[] teilwaschung = {"Teilwaschung (z.B. Intimbereich)",
                "Mund-, Zahn- und Lippenpflege",
                "Rasieren", "Hautpflege", "Haarpflege",
                "Nagelpflege", "An- und Auskleiden incl. An- und Ablegen von Körperersatzstücken",
                "Vorbereiten/Aufräumen des Pflegebereiches"};
        String[] aussscheidungen = {"Utensilien bereitstellen, anreichen",
                "Zur Toilette führen",
                "Unterstützung u. allgem. Hilfestellung (Urin, Stuhl, Schweiß, Sputum, Erbrochenes)",
                "Überwachung der Ausscheidung",
                "Entsorgen, Reinigen des Gerätes und des Bettes",
                "Katheterpflege (insb. Wechseln von Urinbeuteln), Stomaversorgung bei Anus praeter (Wechsel u. Entleerung, des Stomabeutels)",
                "Empfehlung zum Kontinenztraining / Inkontinenzversorgung",
                "Nachbereiten des Pflegebedürftigen ggf. Intimpflege"};
        String[] clean = {"Reinigen des allgemeinüblichen Lebensbereiches", "Trennen und entsorgen des Abfalls", "Keine Grundreinigung" };
        String[] laundry = {"Waschen und Trockenen", "Bügeln", "Ausbessern", "Sortieren und einräumen", "Schuhpflege" };
        String[] helptoeat = {"Mundgerechtes Vorbereiten der Nahrung (auch angelieferte Warmspeisen)",
                "Lagern und Vorbereiten des Pflegebedürftigen",
                "Darreichung der Nahrung",
                "Entsorgen der benötigten Materialien",
                "Säubern des Arbeitsbereiches (spülen)",
                "Versorgung des Pflegebedürftigen (Hygiene im Zusammenhang mit der Nahrungsaufnahme)",
                "Kenntnisvermittlung (keine Ernährungsberatung) über richtige Ernährung (z.B. Diabetiker) ausreichende Flüssigkeitszufuhr incl. Beratung über Esshilfen"};
        String[] setBloodSugar = {"Zusätzlich Körpertemperatur kontrollieren"};
        String[] bloodpressure = {"Bei der Messung den linken Arm verwenden"};
        String[] shopping = {"Zusammenstellen des Einkaufszettels", "Einkaufen ( incl. Arzneimittelbeschaffung) und notwendige Besorgung; (z.B. Bank- und Behördengänge",
                "Unterbringung und Versorgung der eingekauften Lebensmittel", "Anleitung zur Beachtung von Genieß- und Haltbarkeit von Lebensmittel", "Ggf. Wäsche zur Reinigung bringen und abholen"};
        String[] drinking = {"Trinkmenge erfragen"};
        String[] wounds = {"Nutzen Sie Gelegenheiten, wie z.B. die Ganzkörperwäsche um den Körper auf Verletzungen und/oder Druckstellen zu überprüfen."};
        String[] medicament_insulin = {"Ganz wichtig: Insulin", "Thyroxin: 150 µg", "Metropolol: 47,5 mg", "Protaphane: 20 IE"};
        String[] breathing = {"Technik zum Ausatmen (Lippenbremse)", "Einatmen durch die Nase", "Atem Trainer Triflow für 2 Minuten"};
        String[] freshAir = {"Fenster für mindestens 10 Minuten öffnen."};
        String[] ear = {"Batterie der Hörgeräte prüfen", "Gegebenenfalls neue Batterien einlegen", "Hörgeräte anlegen"};
        String[] breath = {"Die Atemstimulierende Einreibung wird nicht AM Klienten, sondern MIT dem Klienten durchgeführt", "Die Einreibung wird OHNE Handschuhe durchgeführt.", "Die Lotion in den Händen anwärmen."
                , "Die Lotion auf den Rücken vom Nacken bis zum unteren Rippenbogen auftragen, auch die Thoraxseiten mit einbeziehen.", "Niemals den Kontakt zum Klienten verlieren, d.h. eine Hand verbleibt ständig am Körper des Klienten.",
                "Beginnen Sie ruhig und gleichmäßig zu atmen. Die Bewegungen werden dem eigenen Atem angepasst und beginnen im Nacken.", "Bei der Exspiration findet die Abwärts- und bei der Inspiration die Aufwärtsbewegung statt.",
                "Es werden mehrere spiegelsymetrische Kreise beschrieben, bei den inspiratorischen Bewegungen lagert der Druck auf Daumen und Zeigefinger, bei den exspiratorischen auf der gesamten Hand.",
                "Hierbei lässt der Druck dann an den Thoraxseiten nach.", "Die Hand hierbei unbedingt geschlossen und flach halten.", "Der Patient sollte relativ schnell die eigene Atmung an die Bewegung der Einreibung anpassen.",
                "Während der Einreibung unbedingt das Sprechen vermeiden und das Befinden des Patienten bzgl. Atmung und/oder Schmerzen beobachten."};

        // Client 1 --> Erna Schubert
        generalTasks1 = new ArrayList<>();
        generalTasks1.add(new GeneralTask("Blutzucker bestimmen", setBloodSugar, "Blutzucker", 1, 5));
        generalTasks1.add(new GeneralTask("Blutdruck messen", bloodpressure, "Blutdruck", 1, 3));
        generalTasks1.add(new GeneralTask("Reinigen der Wohnung", clean, "Wohungsreinigung", 8, 5));
        generalTasks1.add(new GeneralTask("Waschen und Pflegen der Wäsche u. Kleidung", laundry, "Waschen", 8, 10));
        generalTasks1.add(new GeneralTask("Teilwaschung durchführen", teilwaschung, "Teilwaschung", 3, 10));
        generalTasks1.add(new GeneralTask("Auf Verletzungen/Druckstellen prüfen", wounds, "Wunden", 1, 5));
        generalTasks1.add(new GeneralTask("Medikamente verabreichen", medicament_insulin, "Medikamente", 1, 5));

        // Client 2 --> Ernst Meier
        generalTasks2 = new ArrayList<>();
        generalTasks2.add(new GeneralTask("Atemübungen durchführen", breathing, "Atmung", 3, 5));
        generalTasks2.add(new GeneralTask("Blutdruck messen", bloodpressure, "Blutdruck", 3, 3));
        generalTasks2.add(new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Flüssigkeitsbilanz", 1, 2));
        generalTasks2.add(new GeneralTask("Hautpflege", teilwaschung, "Hautpflege", 2, 5));
        generalTasks2.add(new GeneralTask("Auf Verletzungen/Druckstellen prüfen", wounds, "Wunden", 1, 2));
        generalTasks2.add(new GeneralTask("Medikamente verabreichen", medicament_insulin, "Medikamente", 1, 3));

        // Client 3 --> Helga Stern
        generalTasks3 = new ArrayList<>();
        generalTasks3.add(new GeneralTask("Wohnung lüften", freshAir, "Wohnung",1, 1));
        generalTasks3.add(new GeneralTask("Ausscheidungen dokumentieren", aussscheidungen, "Ausscheidungen", 1, 7));
        generalTasks3.add(new GeneralTask("Wundversorgung durchführen", new String[]{"Wunde am Steißbein versorgen und dokumentieren"}, "Wunden", 2, 10));
        generalTasks3.add(new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Flüssigkeitsbilanz", 2, 4));
        generalTasks3.add(new GeneralTask("Ganzkörperwaschung", ganzkörperwaschung, "Ganzkörperwaschung", 2, 10));
        generalTasks3.add(new GeneralTask("Hilfe bei der Nahrungsaufnahme", helptoeat, "Nahrungsaufnahme", 1, 15));
        generalTasks3.add(new GeneralTask("Medikamente verabreichen", new String[]{"Bei Bedarf die Magentabletten anbieten"}, "Medikamente", 1, 2));

        // Client 4 --> Emma Liesengang
        generalTasks4 = new ArrayList<>();
        generalTasks4.add(new GeneralTask("Wohnung lüften", freshAir, "Wohnung", 3, 1));
        generalTasks4.add(new GeneralTask("Blutdruck messen", bloodpressure, "Blutdruck", 3, 3));
        generalTasks4.add(new GeneralTask("Puls messen", new String[]{"Bei Möglichkeit mit Emma vorher nicht über Fußball reden"}, "Puls", 3, 3));
        generalTasks4.add(new GeneralTask("Wundversorgung durchführen", new String[]{"Wunde am Unterarm versorgen und dokumentieren"}, "Wunden", 3, 10));
        generalTasks4.add(new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Flüssigkeitsbilanz", 3, 4));
        generalTasks4.add(new GeneralTask("Medikamente verabreichen", new String[]{"Ganz wichtig: Insulin"}, "Medikamente", 3, 2));

        // Client 5 --> Hans Rüdiger Schmidt
        generalTasks5 = new ArrayList<>();
        generalTasks5.add(new GeneralTask("Wohnung lüften", freshAir, "Wohnung", 8, 1));
        generalTasks5.add(new GeneralTask("Hörgeräte anlegen", ear, "Hörgerät", 2, 2));
        generalTasks5.add(new GeneralTask("Blutdruck messen", new String[]{"Nach selbst gemessenen Werten fragen"}, "Blutdruck", 1, 3));
        generalTasks5.add(new GeneralTask("Körpergewicht wiegen", new String[]{"Die Wage steht im Bad"}, "Gewicht", 8, 3));
        generalTasks5.add(new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Flüssigkeitsbilanz", 3,2));
        generalTasks4.add(new GeneralTask("Medikamente verabreichen", new String[]{""}, "Medikamente", 1, 2));

        // Client 6 --> Karl Heinz Hufschmied
        generalTasks6 = new ArrayList<>();
        generalTasks6.add(new GeneralTask("Unterstützung der Ehefrau bei der Ganzkörperwaschung", ganzkörperwaschung, "Ganzkörperwaschung", 5, 15));
        generalTasks6.add(new GeneralTask("Wundversorgung durchführen", new String[]{"Wunde am Arm versorgen und dokumentieren"}, "Wunden", 5, 5));
        generalTasks6.add(new GeneralTask("Blutdruck messen", new String[]{"Das Blutdruckgerät von Karl verwenden"}, "Blutdruck", 5, 3));
        generalTasks6.add(new GeneralTask("Puls messen", new String[]{""}, "Blutdruck", 5, 3));
        generalTasks6.add(new GeneralTask("Ausscheidungen dokumentieren", aussscheidungen, "Ausscheidungen", 5, 5));

        // Client 7 --> Irmgard
        generalTasks7 = new ArrayList<>();
        generalTasks7.add(new GeneralTask("Atemstimulierende Einreibung", breath, "Atem", 3, 5));
        generalTasks7.add(new GeneralTask("Atemübungen durschführen", breathing, "Atem", 3, 5));
        generalTasks7.add(new GeneralTask("Flüssigkeitsbilanzierung", drinking, "Flüssigkeitsbilanz", 3, 2));
        generalTasks7.add(new GeneralTask("Hautpflege", ganzkörperwaschung, "Ganzkörperwaschung", 2, 4));
        generalTasks7.add(new GeneralTask("Waschen, Duschen, Baden", ganzkörperwaschung, "Ganzkörperwaschung", 2, 15));
        generalTasks7.add(new GeneralTask("Medikamente verabreichen", new String[]{}, "Medikamente", 1, 2));
        generalTasks7.add(new GeneralTask("Einkaufen", shopping, "Puls", 8, 20));
        generalTasks7.add(new GeneralTask("Körpergewicht wiegen", new String[]{"Die Wage steht in der Küche"}, "Gewicht", 8, 5));
    }
}

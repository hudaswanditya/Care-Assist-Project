package de.careassist.app.LogBook.NoteComparators;

import java.util.Comparator;

import de.careassist.app.Todo.Note;

/**
 * Created by v2 on 05.02.2017.
 */

public class TagComparator implements Comparator<Note> {
    @Override
    public int compare(Note note1, Note note2) {
        final int EQUAL = 0;

        int comparison = note1.getTag().compareTo(note2.getTag());
        if (comparison != EQUAL) return comparison;

        comparison = note1.getTimestamp().compareTo(note2.getTimestamp());
        if (comparison != EQUAL) return comparison;

        return EQUAL;
    }
}

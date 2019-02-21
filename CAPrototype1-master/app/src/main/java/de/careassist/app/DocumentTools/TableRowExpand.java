package de.careassist.app.DocumentTools;

import android.content.Context;
import android.widget.TableRow;

/**
 * Created by Eko on 19.12.2016.
 */

public class TableRowExpand extends TableRow {

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int id) {
        Id = id;
    }

    private int Id;

    public TableRowExpand(Context context){
        super(context);
    }
}

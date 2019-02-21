package de.careassist.app.dummy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeMap;

import de.careassist.app.GeneralTask;
import de.careassist.app.Todo.ToDo;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyToDos {

    public static TreeMap<Date, List<ToDo>> TODO_MAP_Erna = new TreeMap<>();
    public static TreeMap<Date, List<ToDo>> TODO_MAP_Ernst = new TreeMap<>();
    public static TreeMap<Date, List<ToDo>> TODO_MAP_Helga = new TreeMap<>();
    public static TreeMap<Date, List<ToDo>> TODO_MAP_Emma = new TreeMap<>();
    public static TreeMap<Date, List<ToDo>> TODO_MAP_Hans = new TreeMap<>();
    public static TreeMap<Date, List<ToDo>> TODO_MAP_Karl = new TreeMap<>();
    public static TreeMap<Date, List<ToDo>> TODO_MAP_Irmgard = new TreeMap<>();

    static {
        generateTodos(DummyGeneralTasks.generalTasks1, TODO_MAP_Erna);
        generateTodos(DummyGeneralTasks.generalTasks2, TODO_MAP_Ernst);
        generateTodos(DummyGeneralTasks.generalTasks3, TODO_MAP_Helga);
        generateTodos(DummyGeneralTasks.generalTasks4, TODO_MAP_Emma);
        generateTodos(DummyGeneralTasks.generalTasks5, TODO_MAP_Hans);
        generateTodos(DummyGeneralTasks.generalTasks6, TODO_MAP_Karl);
        generateTodos(DummyGeneralTasks.generalTasks7, TODO_MAP_Irmgard);
    }

    private static void generateTodos(List<GeneralTask> generalTasks, TreeMap<Date, List<ToDo>> todos){

        // Calender Instance and trim date
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        List<ToDo> today = new ArrayList<>();
        for (GeneralTask task : generalTasks) {
            today.add(new ToDo(task));
        }
        todos.put(calendar.getTime(), today);

        List<ToDo> yesterday = new ArrayList<>();
        for (GeneralTask task : generalTasks) {
            int freq = task.getFrequencyInDays();
            if(freq == 1) yesterday.add(new ToDo(task));
        }
        calendar.add(Calendar.DATE, -1);
        todos.put(calendar.getTime(), yesterday);

        List<ToDo> tomorrow = new ArrayList<>();
        for (GeneralTask task : generalTasks) {
            int freq = task.getFrequencyInDays();
            if(freq == 1) tomorrow.add(new ToDo(task));
        }
        calendar.add(Calendar.DATE, 2);
        todos.put(calendar.getTime(), tomorrow);

        List<ToDo> day_after_tmrw = new ArrayList<>();
        for (GeneralTask task : generalTasks) {
            int freq = task.getFrequencyInDays();
            if(freq == 1 || freq == 2) day_after_tmrw.add(new ToDo(task));
        }
        calendar.add(Calendar.DATE, 4);
        todos.put(calendar.getTime(), day_after_tmrw);
    }

    public static void sortAlphabet(List<ToDo> items){
        Collections.sort(items, new Comparator<ToDo>() {
            @Override
            public int compare(ToDo first, ToDo second){
                return first.getTask().getName().compareTo(second.getTask().getName());
            }
        });
        List<ToDo> done = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isDone()){
                done.add(items.get(i));
                items.remove(i);
                i = i-1;
            }
        }
        for(int i = 0; i < done.size(); i++){
            items.add(done.get(i));
        }
    }

    public static List<ToDo> getDone(List<ToDo> items){
        List<ToDo> done = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isDone()){
                done.add(items.get(i));
            }
        }
        return done;
    }

    public static List<ToDo> getUndone(List<ToDo> items){
        List<ToDo> undone = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(!items.get(i).isDone()){
                undone.add(items.get(i));
            }
        }
        return undone;
    }
}

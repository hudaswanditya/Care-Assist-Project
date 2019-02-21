package de.careassist.app.Todo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import de.careassist.app.Client;
import de.careassist.app.ClientViewActivity;
import de.careassist.app.FloatingActionButton.DialogAddToDo;
import de.careassist.app.R;
import de.careassist.app.dummy.DummyToDos;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TodoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    public static String ARG_Position;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
//    private OnInfoClickedInteractionListener infoListener;
    private View view;
    private static View oldSelection;
    private Context context;
    private Client client;
    private List<ToDo> currentToDos;
    private TreeMap<Date, List<ToDo>> toDoMap;
    private Date currentDate;
    private List<Note> fixedNotes;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TodoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TodoFragment newInstance(int columnCount) {
        TodoFragment fragment = new TodoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    // Called to do initial creation of a fragment. This is called after onAttach(Activity) and before onCreateView(LayoutInflater, ViewGroup, Bundle)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        Activity a = getActivity();
        if(a instanceof ClientViewActivity){
            client = ((ClientViewActivity)a).getClient();
            toDoMap = client.getToDos();
            currentToDos = extractToDosAndUpdateCurrentDate();
            fixedNotes = client.getFixedNotes();
        }
    }

    // Called to have the fragment instantiate its user interface view. This is optional, and non-graphical fragments can return null (which is the default implementation). This will be called between onCreate(Bundle) and onActivityCreated(Bundle).
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_todo_list_expandable, container, false);


        context = view.getContext();
        createView();

        return view;
    }

    private void createView() {
        DummyToDos.sortAlphabet(currentToDos);

        //ImageView divider = (ImageView) view.findViewById(R.id.divider);

        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.todolist);
//        if (mColumnCount <= 1) {
//            expandableListView.setLayoutManager(new LinearLayoutManager(context));
//        } else {
//            expandableListView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//        }
        expandableListView.setAdapter(new TodoExpandableListViewAdapter(getContext(), this, DummyToDos.getUndone(currentToDos), fixedNotes, mListener, expandableListView));
        //expandableListView.setDivider(getResources().getDrawable(R.drawable.divider_transparent));


        ExpandableListView expandableListViewDone = (ExpandableListView) view.findViewById(R.id.todolist_done);
//        if (mColumnCount <= 1) {
//            expandableListViewDone.setLayoutManager(new LinearLayoutManager(context));
//        } else {
//            expandableListViewDone.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//        }
        expandableListViewDone.setAdapter(new TodoExpandableListViewAdapter(getContext(), this, DummyToDos.getDone(currentToDos), fixedNotes, mListener, expandableListViewDone));
        //expandableListViewDone.setDivider(getResources().getDrawable(R.drawable.divider_transparent));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        expandableListViewDone.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0f);
        expandableListView.setLayoutParams(layoutParams2);

        if (DummyToDos.getDone(currentToDos).size() == 0) {
            expandableListViewDone.setVisibility(View.GONE);

            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,0f);
            expandableListViewDone.setLayoutParams(layoutParams3);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,1f);
            expandableListView.setLayoutParams(layoutParams4);

        } else {
            expandableListViewDone.setVisibility(View.VISIBLE);
        }

        TextView undoneHeader = (TextView) view.findViewById(R.id.undone_headline);
            undoneHeader.setTextSize(16);

        if (DummyToDos.getUndone(currentToDos).size() == 0) {
            expandableListView.setVisibility(View.GONE);
            //divider.setVisibility(View.GONE);
            if(isToday(currentToDos)) {
                client.setDoneForToday(true);
                undoneHeader.setText("Sehr gut. Alle Aufgaben für heute sind erledigt.");
            }
        } else {
            client.setDoneForToday(false);
            expandableListView.setVisibility(View.VISIBLE);
            //divider.setVisibility(View.VISIBLE);
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
            undoneHeader.setText("Deine Aufgaben für den " + format.format(currentDate.getTime()));
        }

        ImageButton prev = (ImageButton) view.findViewById(R.id.prevToDos);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentDate != toDoMap.firstKey()) {
                    Date lowerKey = toDoMap.lowerKey(currentDate);
                    currentToDos = toDoMap.get(lowerKey);
                    currentDate = lowerKey;
                    updateList();
                }
            }
        });

        Button today = (Button) view.findViewById(R.id.today_btn);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentToDos = extractToDosAndUpdateCurrentDate();
                updateList();
            }
        });

        if(isToday(currentToDos))
            today.setTextColor(getResources().getColor(R.color.colorPrimaryText));
        else
            today.setTextColor(getResources().getColor(R.color.colorSecondaryText));

        ImageButton next = (ImageButton) view.findViewById(R.id.nextToDos);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentDate != toDoMap.lastKey()) {
                    Date higherKey = toDoMap.higherKey(currentDate);
                    currentToDos = toDoMap.get(higherKey);
                    currentDate = higherKey;
                    updateList();
                }
            }
        });

        FloatingActionButton fab_todo = (FloatingActionButton) view.findViewById(R.id.fab_todo);
        fab_todo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogAddToDo dialogAddToDo = new DialogAddToDo();
                dialogAddToDo.show(getActivity().getFragmentManager(),"Add todo");
            }
        });

        expandableListView.requestLayout();
        expandableListViewDone.requestLayout();
    }

    public void updateList() {
        createView();
    }

    // Called when a fragment is first attached to its context. onCreate(Bundle) will be called after this.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
//            infoListener = (OnInfoClickedInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener and OnInfoClickedInteractionListener");
        }
    }

    //Called when the fragment is no longer attached to its activity. This is called after onDestroy(), except in the cases where the fragment instance is retained across Activity re-creation (see setRetainInstance(boolean)), in which case it is called after onStop().
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
//        infoListener = null;
    }

    public static View getOldSelection() {
        return oldSelection;
    }

    public static void setOldSelection(View newSelection) {
        oldSelection = newSelection;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(int position, boolean done);

        void onDatePickerInteraction(TodoFragment frag, ToDo todo);
    }

    public interface OnInfoClickedInteractionListener {
        void onInfoClickedListener(int position, boolean done);
    }

    private List<ToDo> extractToDosAndUpdateCurrentDate() {
        final int DAY=1000*60*60*24;
        Date today = new Date();
        for (Date date : toDoMap.keySet()) {
            if(today.getTime()/DAY == date.getTime()/DAY) {
                currentDate = date;
                return  toDoMap.get(date);
            }
        }
        return new ArrayList<>();
    }

    private boolean isToday(List<ToDo> toDoList) {
        final int DAY=1000*60*60*24;
        Date today = new Date();
        for (Date date : toDoMap.keySet()) {
            if(today.getTime()/DAY == date.getTime()/DAY) {
                return toDoList.containsAll(toDoMap.get(date));
            }
        }
        return false;
    }
}

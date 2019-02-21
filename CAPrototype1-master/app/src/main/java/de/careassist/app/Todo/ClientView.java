package de.careassist.app.Todo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.careassist.app.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    public static String ARG_Position;

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;


    public ClientView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientView.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientView newInstance(String param1, String param2) {
        ClientView fragment = new ClientView();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_view, container, false);

        if (view.findViewById(R.id.todos)!=null){
            TodoFragment firstFragment = new TodoFragment();
            getChildFragmentManager().beginTransaction().add(R.id.todos, firstFragment).commit();
        }
        if (view.findViewById(R.id.todoNotes)!=null){
            ToDoNotes secondFragment = new ToDoNotes();
            getChildFragmentManager().beginTransaction().add(R.id.todoNotes, secondFragment).commit();
        }

        return view;
    }


    public void updateClientView(int position, boolean done){
        if(position!=-2) {
            ToDoNotes tFrag = (ToDoNotes) getChildFragmentManager().findFragmentById(R.id.todoNotes);
            if (tFrag != null) {
                tFrag.updateFragView(position, done);
            } else {
                tFrag = new ToDoNotes();
                Bundle args = new Bundle();
                args.putInt(ClientView.ARG_Position, position);
                tFrag.setArguments(args);

                FragmentTransaction trans = getChildFragmentManager().beginTransaction();

                trans.replace(R.id.todoNotes, tFrag);

                trans.commit();
            }
        }else{
            TodoFragment todoFrag = (TodoFragment) getChildFragmentManager().findFragmentById(R.id.todos);
            if (todoFrag != null) {
                todoFrag.updateList();
            } else {
                todoFrag = new TodoFragment();
                Bundle args = new Bundle();
                args.putInt(ClientView.ARG_Position, position);
                args.putBoolean("done", done);
                todoFrag.setArguments(args);


                FragmentTransaction trans = getChildFragmentManager().beginTransaction();

                trans.replace(R.id.todos, todoFrag);

                trans.commit();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

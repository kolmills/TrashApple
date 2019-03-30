package com.example.trashapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.example.trashapp.BackgroundWorker.customerList;
import static com.example.trashapp.BackgroundWorker.myRef;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TicketListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TicketListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketListFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static List<Ticket> ticketList = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private ArrayList<String> ticketnums = new ArrayList<>();
    OnHeadlineSelectedListener callback;
    ArrayAdapter<String> listViewAdapter;
    ListView listView;

    public void setOnHeadlineSelectedListener(OnHeadlineSelectedListener activity) {
        callback = activity;
    }

    public TicketListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TicketListFragment newInstance(List<Customer> list) {
        customers = list;
        TicketListFragment fragment = new TicketListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_list, container, false);
        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setAdapter(listViewAdapter);
        final ArrayList<Customer> Array = new ArrayList<>();
        listViewAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,ticketnums);
        listView.setAdapter(listViewAdapter);
        myRef.child("CustomerSet").addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                int i = 0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    Log.v(TAG,""+ postSnapshot.child("CustomerSet").getKey()); //displays the key for the node
                    listViewAdapter.notifyDataSetChanged();
                    Customer test = postSnapshot.getValue(Customer.class);
                    Array.add(test);
                    MainActivity.backgroundWorker.customerList = Array;
                    listViewAdapter.add(test.getFirstName() + " " + test.getLastName());
                    i++;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Database error:","Could not connect!");
            }

        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        // Send the event to the host activity
        Customer c =(Customer) MainActivity.backgroundWorker.getCustomerList().get(position);
        MainActivity.backgroundWorker.setCurrentCustomer(position);
        MainActivity.currentTicket = c.getTicketList().get(0);
        callback.onArticleSelected(position);
    }

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }
}

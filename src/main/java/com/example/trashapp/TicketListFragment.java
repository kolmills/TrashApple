package com.example.trashapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TicketListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TicketListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketListFragment extends Fragment {
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

    public TicketListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TicketListFragment newInstance(List<Customer> list) {
        //for (int i = 0; i < list.size(); i++){
         //   ticketList.add(list.get(i).getTicket());
       // }
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
        //String[] menuItems = {"cat", "dog", "mexican", "hotdog"};
        //menuItems[2] = customers.get(0).getFirstName();
        ListView listView = (ListView) view.findViewById(R.id.TicketList);
        listView.setAdapter(null);
        //List y = MainActivity.backgroundWorker.getTicketList();
        String[] ticketnums = new String[customers.size()];
        for (int i = 0; i < customers.size();i++){
            ticketnums[i] = "Ticket: " + (i+1);
        }
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, ticketnums);


        listView.setAdapter(listViewAdapter);
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
}

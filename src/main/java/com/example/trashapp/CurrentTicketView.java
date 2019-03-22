package com.example.trashapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentTicketView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentTicketView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentTicketView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CurrentTicketView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentTicketView.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentTicketView newInstance(String param1, String param2) {
        CurrentTicketView fragment = new CurrentTicketView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        //return inflater.inflate(R.layout.fragment_current_ticket_view, container, false);

        View RootView = inflater.inflate(R.layout.fragment_current_ticket_view, container, false);
        Customer customer = MainActivity.customer;
        /**SET CURRENT TICKET VALUES*/
        TextView bangCustomer = (TextView) RootView.findViewById(R.id.currentCustomer);
        String fullName = String.format("%s %s", customer.getFirstName(), customer.getLastName());
        bangCustomer.setText(fullName);
        /***/
        TextView currentAddress = (TextView) RootView.findViewById(R.id.currentCustomerAddress);
        currentAddress.setText(customer.getAddress());
        /***/
        TextView currentPhoneNumber = (TextView) RootView.findViewById(R.id.currentCustomerPhoneNumber);
        currentPhoneNumber.setText(customer.getPhoneNumber());
        /***/
        TextView currentEmail = (TextView) RootView.findViewById(R.id.currentCustomerEmail);
        currentEmail.setText(customer.getEmail());
        /***/
        TextView currentNotes = (TextView) RootView.findViewById(R.id.currentCustomerSpecialNotes);
        currentNotes.setText(customer.getSpecialNotes());
        /***/
        TextView currentGarbageDay = (TextView) RootView.findViewById(R.id.currentCustomerGarbageDay);
        currentGarbageDay.setText(customer.getGarbageDay());
        /***/
        TextView currentSubscription = (TextView) RootView.findViewById(R.id.currentCustomerSubscriptionInfo);
        currentSubscription.setText(customer.getSubscriptionInfo());

        return RootView;

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

    public void setText(Customer customer){

    }
}

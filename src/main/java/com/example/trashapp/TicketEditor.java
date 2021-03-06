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
 * {@link TicketEditor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TicketEditor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketEditor extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TicketEditor() {
        // Required empty public constructor
    }

    /**
     * creates the new instance of the fragment
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TicketEditor.
     */
    // TODO: Rename and change types and number of parameters
    public static TicketEditor newInstance(String param1, String param2) {
        TicketEditor fragment = new TicketEditor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * not much happens on create
     * @param savedInstanceState previous instnce of the fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * when it switched to view this fragment it will fill all of the text boxes to be edited
     * so that upon editing uses can delete or add tot he info already there
     * @param inflater a variable with functions attached to is
     * @param container the container in main activity to be filled
     * @param savedInstanceState the previous instance
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_ticket_editor, container, false);
        Customer customer = MainActivity.backgroundWorker.currentCustomer;
        //SET ALL THE VALUES TO WHAT THEY CURRENTLY ARE
        TextView currentNotes = (TextView) RootView.findViewById(R.id.specialnoteSave);
        currentNotes.setText(customer.getSpecialNotes());

        TextView first = (TextView) RootView.findViewById(R.id.firstNameSave);
        first.setText(customer.getFirstName());

        TextView last = (TextView) RootView.findViewById(R.id.lastNameSave);
        last.setText(customer.getLastName());

        TextView address = (TextView) RootView.findViewById(R.id.addressSave);
        address.setText(customer.getAddress());

        TextView phone = (TextView) RootView.findViewById(R.id.phoneNumberSave);
        phone.setText(customer.getPhoneNumber());

        TextView email = (TextView) RootView.findViewById(R.id.emailSave);
        email.setText(customer.getEmail());

        TextView gDay = (TextView) RootView.findViewById(R.id.garbageDaySave);
        gDay.setText(customer.getGarbageDay());

        TextView subscribe = (TextView) RootView.findViewById(R.id.subscribeDateSave);
        subscribe.setText(customer.getSubscriptionInfo());

        return RootView;
    }

    /**
     * this waits for its button to be pressed so the fragment can become active
     * @param uri
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * default fragment function
     * @param context the Main context
     */
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

    /**
     * default fragment function
     */
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

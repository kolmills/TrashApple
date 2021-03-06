package com.example.trashapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapDisplay extends Fragment implements OnMapReadyCallback {

    private OnFragmentInteractionListener mListener;

    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private View mView;
    private List<Customer> tempTicketList = new ArrayList<>();
    private List<Marker> markerList = new ArrayList<Marker>();
    private boolean mLocationPermissionGranted;



    public MapDisplay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapView.
     */
    // TODO: Rename and change types and number of parameters
    public static MapDisplay newInstance(String param1, String param2) {
        MapDisplay fragment = new MapDisplay();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Execute when a fragment is created
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLocationPermission();
    }

    /**
     * Execute when the view is created
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map_view, container, false);
        return mView;
    }

    /**
     * Execute after onCreatView  is called
     * The map view is initialzied
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    /**
     * Execute when the fragment is attached to the activity
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * execute when the fragment is detached from the activity
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        if (!markerList.isEmpty()){ markerList.clear(); }
        //if (!MainActivity.backgroundWorker.getCustomerList().isEmpty()) {tempTicketList = MainActivity.backgroundWorker.customerList;}
        for (int i = 0; i < MainActivity.backgroundWorker.customerList.size(); i++){
            Marker tempMarker;
            tempMarker = googleMap.addMarker(new MarkerOptions()
                    .position(getLocationFromAddress(getContext(), MainActivity.backgroundWorker.customerList.get(i).getAddress()))
                    .title(MainActivity.backgroundWorker.customerList.get(i).getLastName()));
            tempMarker.setTag(0);
            markerList.add(i, tempMarker);
        }//googleMap.addMarker(markerList.get(0).);
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        updateUI();

    }

    /**
     * The function will update the view of the map
     */
    private void updateUI() {

        if(mGoogleMap == null){
            return;
        }

        try {
            Task<Location> locationResult = LocationServices.getFusedLocationProviderClient(getContext()).getLastLocation();
            locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Location mLastKnownLocation = (Location) task.getResult();
                        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getLocationFromAddress(getContext(), MainActivity.backgroundWorker.currentCustomer.getAddress()), 15.0f));
                    }
                }
            });
        }
        catch(SecurityException e) {
            Log.e("MapDisplay", "Can't get the location");
        }
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
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }


    /**
     * Get the current location permission
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateUI();
    }

    /**
     * this converts an inputted address into latitude and longitude coordinates
     * @param context the context
     * @param strAddress the street address to be converted
     * @return returns lat and lon coordinates
     */
    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address = new ArrayList<>();
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

}

package com.futureapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import timber.log.Timber;

import static android.support.constraint.Constraints.TAG;

public class MapTab extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    public MapTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // This makes no sense, should be something else
        return inflater.inflate(R.layout.map_tab, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Not reached
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Not reached

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity().getApplicationContext(), R.raw.style_json));

            if (!success) {
                Timber.i(TAG + "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Timber.i(TAG + "Can't find style. Error: " + e);
        }

        Timber.i("mMap = " + mMap);
        mMap = googleMap;
        // Move the camera to India.
        /*LatLng india = new LatLng(28.7, 78.9);
        googleMap.addMarker(new MarkerOptions().position(india)
                .title("Marker in India"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));*/

       /* Marker m1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(38.609556, -1.139637))
                .anchor(0.5f, 0.5f)
                .title("Title1")
                .snippet("Snippet1"));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1)));


        Marker m2 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.4272414,-3.7020037))
                .anchor(0.5f, 0.5f)
                .title("Title2")
                .snippet("Snippet2"));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo2)));


        Marker m3 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.2568193,-2.9225534))
                .anchor(0.5f, 0.5f)
                .title("Title3")
                .snippet("Snippet3"));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo3)));*/

        MarkerOptions options = new MarkerOptions();
        ArrayList<LatLng> latlngs = new ArrayList<>();

        latlngs.add(new LatLng(28.7041, 77.1025));
        latlngs.add(new LatLng(19.0760, 72.8777));
        latlngs.add(new LatLng(12.9716, 77.5946));
        latlngs.add(new LatLng(22.5726, 88.3639));

        for (LatLng point : latlngs) {
            options.position(point);
            options.title("someTitle");
            options.snippet("someDesc");
            mMap.addMarker(options);
        }

        LatLng india = new LatLng(20.5937, 78.9629);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(india, 5f));


    }
}

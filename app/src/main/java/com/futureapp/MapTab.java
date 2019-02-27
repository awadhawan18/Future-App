package com.futureapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import timber.log.Timber;

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
        Toast.makeText(getActivity().getApplicationContext(), "onAttach()", Toast.LENGTH_SHORT).show();
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Not reached
        Toast.makeText(getActivity().getApplicationContext(), "map ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        Timber.i("mMap = " + mMap);
        // Move the camera to India.
        LatLng india = new LatLng(28.7, 78.9);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));
    }
}

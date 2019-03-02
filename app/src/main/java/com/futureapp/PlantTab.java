package com.futureapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlantTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlantTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantTab extends Fragment {

    private OnFragmentInteractionListener mListener;

    public PlantTab() {
        // Required empty public constructor
    }

    public static PlantTab newInstance(String param1, String param2) {
        PlantTab fragment = new PlantTab();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.plant_tab, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setAdapter() {
        final ArrayList<Plant> list = setList();
        PlantAdapter adapter = new PlantAdapter(getActivity(), list);
        RecyclerView myView = Objects.requireNonNull(getView()).findViewById(R.id.list_plants);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);
        Timber.v("Adapter set!");
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setAdapter();
//    }

    private ArrayList<Plant> setList() {
        ArrayList<Plant> plants = new ArrayList<>();
        plants.add(new Plant("plant 1", "500"));
        plants.add(new Plant("plant 2", "100"));
        plants.add(new Plant("plant 3", "1000"));
        plants.add(new Plant("plant 4", "700"));
        return plants;
    }

    class Plant {
        String name, capacity;

        Plant(String name, String capacity){
            this.name = name;
            this.capacity = capacity;
        }
    }
}

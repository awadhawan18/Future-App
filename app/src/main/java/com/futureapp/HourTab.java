package com.futureapp;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import timber.log.Timber;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HourTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HourTab extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private GoogleMap mMap;
    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;

    //private OnFragmentInteractionListener mListener;

    private ArrayList<HashMap<String, String>> hourValues;

    public HourTab() {
        // Required empty public constructor
    }

    public static HourTab newInstance(String param1, String param2) {
        HourTab fragment = new HourTab();
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
        return inflater.inflate(R.layout.hour_tab, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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

        /*Marker m1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(8.2649025,77.5667683))
                .anchor(0.5f, 0.5f)
                //.title("Title1")
                //.snippet("Snippet1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.wind_icon)));


        Marker m2 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.9493984,76.0465579))
                .anchor(0.5f, 0.5f)
                //.title("Title2")
                //.snippet("Snippet2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.wind_icon)));


        Marker m3 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(17.1171577,74.9861709))
                .anchor(0.5f, 0.5f)
                //.title("Title3")
                //.snippet("Snippet3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.wind_icon)));*/

        /*MarkerOptions options = new MarkerOptions();
        ArrayList<LatLng> latlngs = new ArrayList<>();

        latlngs.add(new LatLng(28.7041, 77.1025));
        latlngs.add(new LatLng(19.0760, 72.8777));
        latlngs.add(new LatLng(12.9716, 77.5946));
        latlngs.add(new LatLng(22.5726, 88.3639));*/

        List<LatLng> list = null;

        // Get the data: latitude/longitude positions of police stations.
        try {
            list = readItems(R.raw.heat_maps);
        } catch (JSONException e) {
            Toast.makeText(getContext(), "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        }

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

        LatLng india = new LatLng(12.9716, 77.5946);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(india, 5f));


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
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.heat_map);
        mapFragment.getMapAsync(this);
        addHeatMap();
        /*WebView myWebView = getView().findViewById(R.id.producers_webview);
        myWebView.loadUrl("http://ipiyush.com/wiso2/producers");
        WebSettings webSettings = myWebView.getSettings();
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);*/

        /*hourValues = new ArrayList<>();
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());
        hourValues.add(new HashMap<String, String>());

        addLineChart();

        HourViewAdapter adapter = new HourViewAdapter(getContext(), hourValues);
        RecyclerView myView = getView().findViewById(R.id.hour_view_list);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);*/
    }

    /*private void addLineChart() {
        List<Entry> valsComp1 = new ArrayList<>();
        List<Entry> valsComp2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Entry c1e1 = new Entry((float) i, 100000f * (i + 1) * (float) Math.pow(-1, i)); // 0 == quarter 1
            valsComp1.add(c1e1);
        }

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setValueTextColor(getResources().getColor(R.color.textPrimary));
        setComp1.setColor(getResources().getColor(R.color.line_color));
        setComp1.setCircleColor(getResources().getColor(R.color.line_color));
        setComp1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setValueTextColor(getResources().getColor(R.color.textPrimary));

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        LineData data = new LineData(dataSets);
        data.setValueTextColor(getResources().getColor(R.color.textPrimary));

        LineChart mLineChart = getView().findViewById(R.id.line_chart);
        mLineChart.setData(data);
        mLineChart.getAxisLeft().setTextColor(getResources().getColor(R.color.textPrimary));
        mLineChart.getAxisRight().setTextColor(getResources().getColor(R.color.textPrimary));
        mLineChart.getXAxis().setTextColor(getResources().getColor(R.color.textPrimary));
        mLineChart.getLegend().setTextColor(getResources().getColor(R.color.textPrimary));
        mLineChart.animateY(2000);

        Legend l = mLineChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setTextSize(12f);
        l.setTextColor(getResources().getColor(R.color.textPrimary));

        mLineChart.invalidate(); // refresh
    }*/

    private void addHeatMap() {

    }

    private ArrayList<LatLng> readItems(int resource) throws JSONException {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            list.add(new LatLng(lat, lng));
        }
        return list;
    }

}

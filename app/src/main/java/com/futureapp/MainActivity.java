package com.futureapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    ChartTab chartTab;
    HourTab hourTab;
    PlantTab plantTab;
    MapTab mapTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MapsActivity.class));
        setContentView(R.layout.app_bar_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Nation Grid");
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);

        //Initializing the tablayout
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position, false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        chartTab = new ChartTab();
        hourTab = new HourTab();
        plantTab = new PlantTab();
        mapTab = new MapTab();
        adapter.addFragment(chartTab, "Capacity");
        adapter.addFragment(hourTab, "Hour View");
        adapter.addFragment(plantTab, "Plants");
        adapter.addFragment(mapTab, "Map View");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        MainActivity.this.finish();
    }
}

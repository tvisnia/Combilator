package com.hexati.combilator;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tomek on 22.09.15.
 */
public class MainActivity2 extends AppCompatActivity {

    private static final String BOSS_NAME = "Tomasz Wiśniewski";
    private static final String BOSS_EMAIL = "tomek97@gmail.com";

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView leftDrawerList;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationDrawerAdapter drawerAdapter;
    private CountingFragment newtonFragment;


    private String[] leftSliderData = {"Kombinacje", "Wariacje z powtórzeniami", "Wariacje bez powtórzeń"};
    private int[] icons = {R.drawable.nozyce, R.drawable.list, R.drawable.miotla};
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main2));
        initView();
        initToolbar();
        initDrawer();
        newtonFragment = new CountingFragment();
        onNavDrawerItemClickHandler(1);
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void initView() {
        leftDrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        leftDrawerList.setHasFixedSize(true);
        drawerAdapter = new NavigationDrawerAdapter(leftSliderData, icons, BOSS_NAME, BOSS_EMAIL, R.drawable.unnamed, this);

        leftDrawerList.setAdapter(drawerAdapter);
        mLayoutManager = new LinearLayoutManager(this);
    }

    private void initDrawer() {
        final GestureDetector mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        leftDrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        leftDrawerList.setLayoutManager(mLayoutManager);
        leftDrawerList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent) {
                View child = leftDrawerList.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mDetector.onTouchEvent(motionEvent)) {
                    onNavDrawerItemClickHandler(leftDrawerList.getChildAdapterPosition(child));
                    return true;
                } else return false;
            }


            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    public void onNavDrawerItemClickHandler(int position) {
        switch (position) {
            case 1: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newtonFragment).commit();
            }
            break;
            case 2: {

                break;
            }
            case 3: {
                break;
            }

            default:

                break;
        }
        drawerLayout.closeDrawers();
    }
}


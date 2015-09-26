package com.hexati.combilator;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

import utils.AnimationUtils;

/**
 * Created by tomek on 22.09.15.
 */
public class MainActivity2 extends AppCompatActivity {
    private FloatingActionButton countNewtonButton;
    private EditText nInput;
    private EditText kInput;
    private TextView newtonResult;
    private int nValue;
    private int kValue;
    private BigInteger valueNewton = new BigInteger("1");
    private BigInteger valueV = new BigInteger("1");
    private BigInteger valueW = new BigInteger("1");
    private BigInteger valueFactorial = new BigInteger("1");

    private static final java.lang.String NAV_DRAWER_ITEM_CLICKED_POS_KEY = "key1";
    private static final String BOSS_NAME = "Tomasz Wiśniewski";
    private static final String BOSS_EMAIL = "tomek97@gmail.com";

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView leftDrawerList;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationDrawerAdapter drawerAdapter;

    LinearLayout coloredLayout;

    private String[] leftSliderData = {"Kombinacje", "Wariacje z powtórzeniami", "Wariacje bez powtórzeń"};
    private int[] icons = {R.drawable.nozyce, R.drawable.list, R.drawable.miotla};
    private LinearLayoutManager mLayoutManager;

    private Toolbar topToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main2));
        countNewtonButton = (FloatingActionButton) findViewById(R.id.button_count_newton);
        init();
        initToolbar();
        initDrawer();
        countNewtonButton = (FloatingActionButton) findViewById(R.id.button_count_newton);
        countNewtonButton.setImageResource(R.drawable.icon_numbers);
        countNewtonButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_orange_light)));
        onNavDrawerItemClickHandler(1);

    }

    private void initCountingViews() {
        countNewtonButton = (FloatingActionButton) findViewById(R.id.button_count_newton);
        nInput = (EditText) findViewById(R.id.newton_nInput);
        kInput = (EditText) findViewById(R.id.newton_kInput);
        newtonResult = (TextView) findViewById(R.id.result_newton);
        countNewtonButton.setImageResource(R.drawable.icon_numbers);
        countNewtonButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_orange_light)));
        countNewtonButton.setOnClickListener(v -> {

                    String nInputText = nInput.getText().toString();
                    String kInputText = kInput.getText().toString();

                    if (!isInteger(nInputText, kInputText) || isEmpty(kInputText, kInputText)) {
                        AnimationUtils.animateButton(countNewtonButton, false);
                        Toast.makeText(v.getContext(), "Wprowadź N i K !", Toast.LENGTH_SHORT).show();
                    } else {
                        nValue = Integer.valueOf(nInputText);
                        kValue = Integer.valueOf(kInputText);

                        setNewtonResult();
                    }
                }
        );
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void init() {
        leftDrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        leftDrawerList.setHasFixedSize(true);
        drawerAdapter = new NavigationDrawerAdapter(leftSliderData, icons, BOSS_NAME, BOSS_EMAIL, R.drawable.unnamed, this);

        leftDrawerList.setAdapter(drawerAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        initCountingViews();
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
        topToolbar = (Toolbar) findViewById(R.id.toolbar_top);
        coloredLayout = (LinearLayout) findViewById(R.id.coloredLayout);
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
                changeColor();
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

    public void changeColor() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.primaryColorDark));
        topToolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        coloredLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
    }

    private BigInteger countNewtonBinomial(int n, int k) {
        valueNewton = BigInteger.valueOf(1);
        for (int i = 1; i <= k; i++) {
            valueNewton = valueNewton.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
            //valueNewton = valueNewton * (n - i + 1) / i;
        }

        return valueNewton;
    }

    private boolean isInteger(String nSymbol, String kSymbol) {
        return (nSymbol.matches("^-?\\d+$") && kSymbol.matches("^-?\\d+$"));
        //check whether String is Integer using regular expression
    }

    private boolean isEmpty(String nSymbol, String kSymbol) {
        return (TextUtils.isEmpty(nSymbol) || TextUtils.isEmpty(kSymbol));
    }

    private void setNewtonResult() {
        if (nValue == kValue || kValue == 0) {
            newtonResult.setText("1");
            AnimationUtils.animateButton(countNewtonButton, true);
        } else if (kValue > nValue) {
            AnimationUtils.animateButton(countNewtonButton, false);
            Toast.makeText(this, "N => K", Toast.LENGTH_SHORT).show();
        } else {
            AnimationUtils.animateButton(countNewtonButton, true);
            newtonResult.setText(String.valueOf(countNewtonBinomial(nValue, kValue)));
        }
    }
}

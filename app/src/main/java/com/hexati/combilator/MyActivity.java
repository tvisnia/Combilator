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

import com.nineoldandroids.animation.ValueAnimator;

import java.math.BigInteger;

import utils.AnimationUtils;

/**
 * Created by tomek on 22.09.15.
 */
public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String BOSS_NAME = "Tomasz Wiśniewski";
    private static final String BOSS_EMAIL = "tomek97@gmail.com";
    private static final long COLOR_CHANGE_ANIMATION_DURATION = 1000;

    private FloatingActionButton countNewtonButton;
    private EditText nInput;
    private EditText kInput;
    private TextView permutationSymbol;
    private TextView resultTextView;
    private int nValue;
    private int kValue;
    private BigInteger valueNewton = new BigInteger("1");
    private BigInteger valueV = new BigInteger("1");
    private BigInteger valueW = new BigInteger("1");
    private BigInteger valueFactorial = new BigInteger("1");

    private Integer toolbarColorFrom;
    private Integer toolbarColorTo;
    private Integer toolbarTopColorFrom;
    private Integer toolbarTopColorTo;
    private Integer layoutColorFrom;
    private Integer layoutColorTo;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView leftDrawerList;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationDrawerAdapter drawerAdapter;

    LinearLayout coloredLayout;

    private String[] leftSliderData = {"Kombinacje", "Wariacje z powtórzeniami", "Wariacje bez powtórzeń", "Permutacje"};
    private int[] icons = {R.drawable.nozyce, R.drawable.list, R.drawable.miotla, R.drawable.nozyce};
    private LinearLayoutManager mLayoutManager;

    private Toolbar topToolbar;
    private int clickedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultColors();
        setContentView((R.layout.activity_main2));
        init();
        initToolbar();
        initDrawer();

        onNavDrawerItemClickHandler(1);

    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void init() {
        countNewtonButton = (FloatingActionButton) findViewById(R.id.button_count_newton);
        countNewtonButton.setImageResource(R.drawable.icon_numbers);
        countNewtonButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_orange_light)));
        countNewtonButton.setOnClickListener(this);
        leftDrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        leftDrawerList.setHasFixedSize(true);
        drawerAdapter = new NavigationDrawerAdapter(leftSliderData, icons, BOSS_NAME, BOSS_EMAIL, R.drawable.unnamed, this);

        leftDrawerList.setAdapter(drawerAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        permutationSymbol = (TextView) findViewById(R.id.permutation_symbol);
        permutationSymbol.setVisibility(View.INVISIBLE);
        initCountingViews();
    }

    private void initCountingViews() {
        nInput = (EditText) findViewById(R.id.n_Input);
        kInput = (EditText) findViewById(R.id.k_Input);
        resultTextView = (TextView) findViewById(R.id.result_newton);
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
        clickedPosition = position;
        savePreviousColors();
        drawerLayout.closeDrawers();
        if (position == 4) {
            layoutColorTo = getResources().getColor(R.color.layoutColor4);
            toolbarColorTo = getResources().getColor(R.color.toolbarColor4);
            toolbarTopColorTo = getResources().getColor(R.color.topToolbarColor4);

            permutationSymbol.setVisibility(View.VISIBLE);
            kInput.setVisibility(View.INVISIBLE);
        } else {
            permutationSymbol.setVisibility(View.INVISIBLE);
            kInput.setVisibility(View.VISIBLE);
            switch (position) {
                default: {
                    layoutColorTo = getResources().getColor(R.color.layoutColor1);
                    toolbarColorTo = getResources().getColor(R.color.primaryColor);
                    toolbarTopColorTo = getResources().getColor(R.color.primaryColorDark);
                    break;
                }
                case 2: {
                    layoutColorTo = getResources().getColor(R.color.layoutColor2);
                    toolbarColorTo = getResources().getColor(R.color.toolbarColor2);
                    toolbarTopColorTo = getResources().getColor(R.color.topToolbarColor2);
                    break;
                }
                case 3: {
                    layoutColorTo = getResources().getColor(R.color.layoutColor3);
                    toolbarColorTo = getResources().getColor(R.color.toolbarColor3);
                    toolbarTopColorTo = getResources().getColor(R.color.topToolbarColor3);
                    break;
                }

            }
        }
        animateChangingColors();
    }

    private void savePreviousColors() {
        layoutColorFrom = layoutColorTo;
        toolbarColorFrom = toolbarColorTo;
        toolbarTopColorFrom = toolbarTopColorTo;
    }

    private void animateChangingColors() {
        ValueAnimator layoutAnimator = ValueAnimator
                .ofObject(new com.nineoldandroids.animation.ArgbEvaluator(),
                        layoutColorFrom, layoutColorTo);
        ValueAnimator toolbarAnimator = ValueAnimator
                .ofObject(new com.nineoldandroids.animation.ArgbEvaluator(),
                        toolbarColorFrom, toolbarColorTo);
        ValueAnimator toolbarTopAnimator = ValueAnimator
                .ofObject(new com.nineoldandroids.animation.ArgbEvaluator(),
                        toolbarTopColorFrom, toolbarTopColorTo);

        layoutAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                coloredLayout.setBackgroundColor((Integer) layoutAnimator.getAnimatedValue());
            }
        });

        toolbarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                toolbar.setBackgroundColor((Integer) toolbarAnimator.getAnimatedValue());
            }
        });

        toolbarTopAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                topToolbar.setBackgroundColor((Integer) toolbarTopAnimator.getAnimatedValue());
            }
        });
        com.nineoldandroids.animation.AnimatorSet animatorSet = new com.nineoldandroids.animation.AnimatorSet();
        animatorSet.setDuration(COLOR_CHANGE_ANIMATION_DURATION);
        animatorSet.playTogether(layoutAnimator, toolbarAnimator, toolbarTopAnimator);
        animatorSet.start();
    }

    private void setDefaultColors() {
        layoutColorTo = getResources().getColor(R.color.layoutColor4);
        toolbarColorTo = getResources().getColor(R.color.toolbarColor4);
        toolbarTopColorTo = getResources().getColor(R.color.topToolbarColor4);
    }

    private BigInteger countNewtonBinomial(int n, int k) {
        valueNewton = BigInteger.valueOf(1);
        for (int i = 1; i <= k; i++) {
            valueNewton = valueNewton.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
            //valueNewton = valueNewton * (n - i + 1) / i;
        }

        return valueNewton;
    }

    private boolean isNSymbolInteger(String nSymbol) {
        return (nSymbol.matches("^-?\\d+$"));
    }

    private boolean isInteger(String nSymbol, String kSymbol) {
        return (nSymbol.matches("^-?\\d+$") && kSymbol.matches("^-?\\d+$"));
        //check whether String is Integer using regular expression
    }

    private boolean isEmpty(String nSymbol, String kSymbol) {
        return (TextUtils.isEmpty(nSymbol) || TextUtils.isEmpty(kSymbol));
    }

    private boolean isNSymbolInputEmpty(String nSymbol) {
        return (TextUtils.isEmpty(nSymbol));
    }

    private void setNewtonResult() {
        if (nValue == kValue || kValue == 0) {
            resultTextView.setText("1");
            AnimationUtils.animateButton(countNewtonButton, true);
        } else {
            AnimationUtils.animateButton(countNewtonButton, true);
            resultTextView.setText(String.valueOf(countNewtonBinomial(nValue, kValue)));
        }
    }

    @Override
    public void onClick(View v) {
        String nInputText = nInput.getText().toString();
        String kInputText = kInput.getText().toString();
        if (clickedPosition == 4) {
            if (isNSymbolInputEmpty(nInputText) || !isNSymbolInteger(nInputText)) {
                Toast.makeText(v.getContext(), "Wprowadź prawidłowe dane !", Toast.LENGTH_SHORT).show();
                AnimationUtils.animateButton(countNewtonButton, false);
                resultTextView.setText("");
            } else {
                nValue = Integer.valueOf(nInputText);
                AnimationUtils.animateButton(countNewtonButton, true);
                resultTextView.setText(String.valueOf(countFactorial(nValue)));
            }
        } else if (!isInteger(nInputText, kInputText) || isEmpty(nInputText, kInputText)) {
            AnimationUtils.animateButton(countNewtonButton, false);
            Toast.makeText(v.getContext(), "Wprowadź prawidłowe dane !", Toast.LENGTH_SHORT).show();
            resultTextView.setText("");
        }
        else if(clickedPosition ==2) {
            nValue = Integer.valueOf(nInputText);
            kValue = Integer.valueOf(kInputText);
            AnimationUtils.animateButton(countNewtonButton, true);
            resultTextView.setText(String.valueOf(countVariationWithRepetition(nValue, kValue)));
        }
            else if (kValue > nValue) {
                AnimationUtils.animateButton(countNewtonButton, false);
                Toast.makeText(v.getContext(), "Wprowadź prawidłowe dane !", Toast.LENGTH_SHORT).show();
                resultTextView.setText("");
        } else {
            nValue = Integer.valueOf(nInputText);
            kValue = Integer.valueOf(kInputText);
            switch (clickedPosition) {
                default: {
                    setNewtonResult();
                    break;
                }
                case 3: {
                    AnimationUtils.animateButton(countNewtonButton, true);
                    resultTextView.setText(String.valueOf(valueV.multiply(countNewtonBinomial(nValue, kValue)).multiply(countFactorial(kValue))));
                    break;
                }
            }
        }
    }

    private BigInteger countVariationWithRepetition(int n, int k) {
        valueW = BigInteger.valueOf(n);
        BigInteger toReturn = null;
        if (k == 0)
            toReturn = BigInteger.valueOf(1);
        else
        if (n > 0) {
            for (int i = 0; i < k-1 ; i++) {
                valueW = valueW.multiply(BigInteger.valueOf(n));
            }
            toReturn = valueW;
        }
        else if (n == 0)
            toReturn = BigInteger.valueOf(0);
        return toReturn;
    }

    private BigInteger countFactorial(int i) {
        return i == 0 ? BigInteger.valueOf(1) : countFactorial(i - 1).multiply(BigInteger.valueOf(i));
    }
}

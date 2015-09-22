package com.hexati.combilator;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.math.BigInteger;

public class MainActivity extends FragmentActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2; //  =4


    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private BigInteger valueV;
    private BigInteger valueW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter();
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends PagerAdapter {

        View layout1;
        View layout2;
        View layout3;
        View layout4;

        public ScreenSlidePagerAdapter(View layout1, View layout2, View layout3, View layout4) {
            this();
            this.layout1 = layout1;
            this.layout2 = layout2;
            this.layout3 = layout3;
            this.layout4 = layout4;

        }

        public ScreenSlidePagerAdapter() {
            layout1 = getLayoutInflater().inflate(R.layout.fragment_binomial_newton, null);
            layout2 = getLayoutInflater().inflate(R.layout.fragment_permutation, null);
            //layout3 = getLayoutInflater().inflate(R.layout.fragment_vVariation, null);
            //layout4 = getLayoutInflater().inflate(R.layout.fragment_wVariation, null);
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mPager = (ViewPager) container;
            switch (position) {
                case 1 : {
                    mPager.addView(layout2);
                    return layout2;
                }
                //nie ma jeszcze zrobionych layoutów i fragmentów do tamtych wzorów

//                case 2 : {
//                    mPager.addView(layout3);
//                    return layout3;
//                }
//                case 3 : {
//                    mPager.addView(layout4);
//                    return layout4;
//                }
                default: {
                    mPager.addView(layout1);
                    return layout1;
                }
            }

        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

    }

//pozostałe metody do obliczania tamtych wzorów, nie będzie ich tu jak będą fragmenty do liczenia tych wzorów
    private BigInteger countV(int n, int k) { //variation without repetition
        valueV = BigInteger.valueOf(1);
        for (int i = 1; i <= k; i++) {
            valueV = valueV.multiply(BigInteger.valueOf(n - i + 1));
            valueV = valueV.divide(BigInteger.valueOf(i));
            //valueNewton = valueNewton * (n - i + 1) / i;
        }
        return valueV;
    }

    private BigInteger countW(int n, int k) { //variation with repetition
        valueW = BigInteger.valueOf(1);

        for (int i = 1; i <= k; i++) {
            valueW = valueW.multiply(valueW);
        }
        return valueW;
    }





}

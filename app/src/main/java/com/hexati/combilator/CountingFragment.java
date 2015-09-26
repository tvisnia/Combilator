package com.hexati.combilator;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.math.BigInteger;

import utils.AnimationUtils;

/**
 * Created by tomek on 21.09.15.
 */

public class CountingFragment extends Fragment {

    private static final java.lang.String NAV_DRAWER_ITEM_CLICKED_POS_KEY ="key1" ;
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
    private Toolbar upperToolbar;
    private Toolbar lowerToolbar;
    private LinearLayout coloredLayout;

    private int navDrawerItemClicked;

    public CountingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_binomial_newton, container, false);
        countNewtonButton = (FloatingActionButton) view.findViewById(R.id.button_count_newton);
        nInput = (EditText) view.findViewById(R.id.newton_nInput);
        kInput = (EditText) view.findViewById(R.id.newton_kInput);
        newtonResult = (TextView) view.findViewById(R.id.result_newton);
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
        return view;
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
            Toast.makeText(getActivity().getApplicationContext(), "N => K", Toast.LENGTH_SHORT).show();
        } else {
            AnimationUtils.animateButton(countNewtonButton, true);
            newtonResult.setText(String.valueOf(countNewtonBinomial(nValue, kValue)));
        }
    }

//    public void Action(Context context, int position) {
////        if(!isAdded()) {
////            return;
////        }
//        Toast.makeText(context, position + "", Toast.LENGTH_SHORT)
//                .show();
//        switch (position) {
//            case 1 : {
//                coloredLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
////                lowerToolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
//                upperToolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
//                break;
//            }
//        }
//    }
}



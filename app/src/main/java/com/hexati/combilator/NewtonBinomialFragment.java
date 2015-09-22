package com.hexati.combilator;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

import utils.AnimationUtils;

/**
 * Created by tomek on 21.09.15.
 */

public class NewtonBinomialFragment extends Fragment {

    private FloatingActionButton countNewtonButton;
    private EditText nInput;
    private EditText kInput;
    private TextView newtonResult;
    private TextView vResult;
    private TextView wResult;
    private TextView permutationResult;
    private BigInteger valueNewton = new BigInteger("1");
    private BigInteger valueV = new BigInteger("1");
    private BigInteger valueW = new BigInteger("1");
    private BigInteger valueFactorial = new BigInteger("1");

    public NewtonBinomialFragment() {
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

                    if (TextUtils.isEmpty(kInputText) || TextUtils.isEmpty(nInputText)) {
                        AnimationUtils.animateButton(countNewtonButton, false);
                        Toast.makeText(v.getContext(), "Wprowadź N i K !", Toast.LENGTH_SHORT).show();
                    } else {
                        int nValue = Integer.valueOf(nInputText);
                        int kValue = Integer.valueOf(kInputText);

                        if (nValue == kValue || kValue == 0) {
                            newtonResult.setText("1");
                            AnimationUtils.animateButton(countNewtonButton, true);
                        } else if (kValue > nValue) {
                            AnimationUtils.animateButton(countNewtonButton, false);
                            Toast.makeText(v.getContext(), "N => K", Toast.LENGTH_SHORT).show();
                        } else {
                            AnimationUtils.animateButton(countNewtonButton, true);
                            newtonResult.setText(String.valueOf(countNewtonBinomial(nValue, kValue)));
                        }
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
}



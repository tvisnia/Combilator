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
public class PermutationFragment extends Fragment {

    private FloatingActionButton countPermutationButton;
    private EditText nInput;
    private TextView permutationResult;

    public PermutationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_permutation, container, false);
        countPermutationButton = (FloatingActionButton) view.findViewById(R.id.button_count_permutation);
        nInput = (EditText) view.findViewById(R.id.permutation_nInput);
        permutationResult = (TextView) view.findViewById(R.id.result_permutation);
        countPermutationButton.setImageResource(R.drawable.icon_numbers);
        countPermutationButton.setBackgroundTintList(ColorStateList.valueOf(view.getResources().getColor(android.R.color.holo_orange_light)));
        countPermutationButton.setOnClickListener(v -> {
                    String nInputText = nInput.getText().toString();
                    if (TextUtils.isEmpty(nInputText)) {
                        AnimationUtils.animateButton(countPermutationButton, false);
                        Toast.makeText(v.getContext(), "Wprowadź N !", Toast.LENGTH_SHORT).show();
                    } else {
                        int nValue = Integer.valueOf(nInputText);
                        if (nValue < 0) {
                            AnimationUtils.animateButton(countPermutationButton, false);
                            Toast.makeText(v.getContext(), "Wprowadź prawidłowe N !", Toast.LENGTH_SHORT).show();
                        } else {
                            countFactorial(nValue);
                            AnimationUtils.animateButton(countPermutationButton, true);
                            permutationResult.setText(String.valueOf(countFactorial(nValue)));
                        }
                    }

                }
        );
        return view;
    }

    private BigInteger countFactorial(int i) {
        return i == 0 ? BigInteger.valueOf(1) : countFactorial(i - 1).multiply(BigInteger.valueOf(i));
    }

}
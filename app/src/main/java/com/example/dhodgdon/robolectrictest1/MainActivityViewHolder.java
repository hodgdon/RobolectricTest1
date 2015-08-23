package com.example.dhodgdon.robolectrictest1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Provides access to views defined in {@link R.layout#activity_main}
 */
final class MainActivityViewHolder {
    MainActivityViewHolder(@NonNull Activity activity) {
        result = (TextView) activity.findViewById(R.id.resultTextView);
        operand1 = (EditText) activity.findViewById(R.id.operand1EditText);
        operand2 = (EditText) activity.findViewById(R.id.operand2EditText);
        operator = (Spinner) activity.findViewById(R.id.operatorSpinner);
    }

    final TextView result;
    public final EditText operand1, operand2;
    public final Spinner operator;
}

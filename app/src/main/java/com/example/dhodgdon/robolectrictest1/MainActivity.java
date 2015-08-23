package com.example.dhodgdon.robolectrictest1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.math.BigInteger;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_views = new MainActivityViewHolder(this);
        attachListeners();
    }

    private void attachListeners() {
        m_views.operator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                computerResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                computerResult();
            }
        });
        m_views.operand1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                computerResult();
            }
        });
        m_views.operand2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                computerResult();
            }
        });
    }

    private boolean isOperandValid(@NonNull String s) {
        return s.matches("^\\d+$");
    }

    @CalculatorModel.Operator private int decodeOperator(@NonNull String operatorString) {
        if(operatorString.equals(getString(R.string.operator_addition))) {
            return CalculatorModel.OPERATOR_PLUS;
        } else if(operatorString.equals(getString(R.string.operator_subtraction))) {
            return CalculatorModel.OPERATOR_MINUS;
        } else {
            return CalculatorModel.OPERATOR_INVALID;
        }
    }

    private void computerResult() {
        String operand1 = m_views.operand1.getText().toString(), operand2 = m_views.operand2.getText().toString();
        @CalculatorModel.Operator int operator = decodeOperator(m_views.operator.getSelectedItem().toString());

        if(isOperandValid(operand1) && isOperandValid(operand2) && operator != CalculatorModel.OPERATOR_INVALID) {
            m_views.result.setText(CalculatorModel.compute(new BigInteger(operand1),operator,new BigInteger(operand2)).toString());
        } else {
            m_views.result.setText(R.string.error_text);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private MainActivityViewHolder m_views;
}

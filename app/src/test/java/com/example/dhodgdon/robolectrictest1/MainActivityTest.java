package com.example.dhodgdon.robolectrictest1;

import android.content.Context;
import android.support.annotation.NonNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Programmatically insert sample inputs into edittext & spinner and observe results
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {
    @Test
    public void testFullCompute() throws Exception {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        MainActivityViewHolder views = new MainActivityViewHolder(mainActivity);
        views.operand1.setText("");
        views.operand2.setText("");
        assertEquals(mainActivity.getString(R.string.error_text), views.result.getText().toString());

        views.operand1.setText("1");
        views.operand2.setText("2");
        views.operator.setSelection(CalculatorModel.OPERATOR_PLUS);
        assertEquals("3", views.result.getText().toString());

        views.operand1.setText("1");
        views.operand2.setText("2");
        views.operator.setSelection(CalculatorModel.OPERATOR_MINUS);
        assertEquals("-1", views.result.getText().toString());

        views.operand1.setText(getEmojiByUnicode(0x1F3AF));
        views.operand2.setText("46");
        views.operator.setSelection(CalculatorModel.OPERATOR_PLUS);
        assertEquals(mainActivity.getString(R.string.error_text), views.result.getText().toString());
    }

    /**
     * Check that operators available to the spinner are correctly order with respect to {@link CalculatorModel}
     */
    @Test
    public void testOperatorsArray() throws Exception {
        Context context = RuntimeEnvironment.application;
        String[] operators = context.getResources().getStringArray(R.array.operator_array);

        assertTrue(operators.length >= 2);

        Set<String> operatorStringSet = new HashSet<>(Arrays.asList(operators));
        assertTrue(operatorStringSet.contains(context.getString(R.string.operator_addition)));
        assertTrue(operatorStringSet.contains(context.getString(R.string.operator_subtraction)));

        assertEquals(context.getString(R.string.operator_addition), operators[CalculatorModel.OPERATOR_PLUS]);
        assertEquals(context.getString(R.string.operator_subtraction), operators[CalculatorModel.OPERATOR_MINUS]);
    }

    @Test
    public void testOperatorSpinner() throws Exception {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        MainActivityViewHolder views = new MainActivityViewHolder(mainActivity);

        views.operator.setSelection(CalculatorModel.OPERATOR_PLUS);
        assertEquals(CalculatorModel.OPERATOR_PLUS, views.operator.getSelectedItemPosition());
        assertEquals(mainActivity.getString(R.string.operator_addition), views.operator.getSelectedItem().toString());

        views.operator.setSelection(CalculatorModel.OPERATOR_MINUS);
        assertEquals(CalculatorModel.OPERATOR_MINUS, views.operator.getSelectedItemPosition());
        assertEquals(mainActivity.getString(R.string.operator_subtraction), views.operator.getSelectedItem().toString());


        // TODO: investigate behavior of spinner given bogus values.  Can this be done by a user with an accessibility tool?  Or some hacking tool?
        views.operator.setSelection(CalculatorModel.OPERATOR_INVALID);
        assertNotEquals(CalculatorModel.OPERATOR_INVALID, views.operator.getSelectedItemPosition());

        boolean exceptionThrown = true;
        try {
            views.operator.setSelection(2);
            exceptionThrown = false;
        } catch(ArrayIndexOutOfBoundsException ignored) {}
        assertTrue(exceptionThrown);
        assertEquals(2,views.operator.getSelectedItemPosition());
        try {
            assertEquals("", views.operator.getSelectedItem().toString());
            exceptionThrown = false;
        } catch(ArrayIndexOutOfBoundsException ignored) {}
        assertTrue(exceptionThrown);

    }

    /**
     * From <A HREF="http://stackoverflow.com/questions/26893796/how-set-emoji-by-unicode-in-android-textview">StackOverflow</A>
     * check out <A HREF="http://apps.timwhitlock.info/emoji/tables/unicode">Emoji reference</A>
     * @return a string containing emoji specified by {@code unicode} parameter
     */
    @NonNull
    public static String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
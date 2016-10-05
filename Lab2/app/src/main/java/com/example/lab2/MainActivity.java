package com.example.lab2;


import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.renderscript.Double2;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author jamiekujawa
 *
 */
public class MainActivity extends Activity {

    /**
     * A string builder to represent the first number entered in the series of entries
     */
    private StringBuilder expression1;

    /**
     * A string builder to represent the second number entered in the series of entries
     */
    private StringBuilder expression2;

    /**
     * A string to represent the last operator performed
     */
    private String oldOperator;

    private boolean displayResult = false;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare all buttons used within the layout
        Button zero = (Button) findViewById(R.id.button0);
        Button one = (Button) findViewById(R.id.button1);
        Button two = (Button) findViewById(R.id.button2);
        Button three = (Button) findViewById(R.id.button3);
        Button four = (Button) findViewById(R.id.button4);
        Button five = (Button) findViewById(R.id.button5);
        Button six = (Button) findViewById(R.id.button6);
        Button seven = (Button) findViewById(R.id.button7);
        Button eight = (Button) findViewById(R.id.button8);
        Button nine = (Button) findViewById(R.id.button9);
        Button times = (Button) findViewById(R.id.buttontimes);
        Button clear = (Button) findViewById(R.id.buttonClear);
        Button equal = (Button) findViewById(R.id.buttonEqual);
        Button decimal = (Button) findViewById(R.id.buttonDecimal);
        Button divide = (Button) findViewById(R.id.buttondivide);
        Button add = (Button) findViewById(R.id.buttonplus);
        Button subtract = (Button) findViewById(R.id.buttonminus);

        // declare main text view
        final TextView main = (TextView) findViewById(R.id.CalculatorText);

        // Main Strings to represent the expressions
        expression1 = new StringBuilder();
        expression2 = new StringBuilder();
        oldOperator = null;
        main.setText(expression1.append("0.0"));

		/*
		 * Set up all key listener events
		 */
        zero.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("0"));
            }

        });

        one.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("1"));
            }

        });

        two.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("2"));
            }

        });

        three.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("3"));
            }

        });

        four.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("4"));
            }

        });

        five.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("5"));
            }

        });

        six.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("6"));
            }

        });

        seven.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("7"));
            }

        });

        eight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("8"));
            }

        });

        nine.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("9"));
            }

        });

        times.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("*"));
            }

        });

        clear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                expression1.setLength(0);
                expression2.setLength(0);
                oldOperator = null;
                main.setText("0.0");
            }

        });

        equal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("="));
            }

        });

        decimal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("."));
            }

        });

        divide.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("/"));
            }

        });

        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("+"));
            }

        });

        subtract.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                main.setText(evaluate("-"));
            }

        });
    }

    /**
     * This method will evaluate an operation using expression1 and expression 2
     *
     * @param buttonPressed or operation to be performed
     * @return the result of the operation
     */
    public String evaluate(String buttonPressed) {
        String expression;
        if(buttonPressed.equals("=")){
            if(expression1.length() > 0 && expression2.length() > 0 && oldOperator != null){
                double val1 = Double.parseDouble(expression1.toString());
                double val2 = Double.parseDouble(expression2.toString());
                double result = 0.0;
                if(oldOperator.equals("+")){
                    result = val1 + val2;
                }else if(oldOperator.equals("-")){
                    result = val1 - val2;
                }else if(oldOperator.equals("*")){
                    result = val1 * val2;
                }else if(oldOperator.equals("/")){
                    result = val1 / val2;
                }
                expression = Double.toString(result);
            }else{
                expression = "0.0";
            }
            oldOperator = null;
            expression1.setLength(0);
            expression1.append(expression);
            expression2.setLength(0);
            displayResult = true;
            return expression;
        }else if(buttonPressed.equals("+") || buttonPressed.equals("-") || buttonPressed.equals("*") || buttonPressed.equals("/")){
            oldOperator = buttonPressed;
            expression = expression1.toString() + oldOperator;
            expression2.setLength(0);
            return expression;
        }else{
            if(oldOperator == null){
                if(expression1.toString().equals("0.0") || displayResult){
                    expression1.setLength(0);
                    displayResult = false;
                }
                expression1.append(buttonPressed);
                expression = expression1.toString();
            }else{
                expression2.append(buttonPressed);
                expression = expression1.toString() + oldOperator + expression2.toString();
            }
            return expression;
        }
    }
}
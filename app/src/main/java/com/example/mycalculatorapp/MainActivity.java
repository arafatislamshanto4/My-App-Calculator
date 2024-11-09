package com.example.mycalculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String input = "";    // Stores the current input
    private String operator = ""; // Stores the current operator
    private double firstNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Set number button listeners
        setNumberButtonListeners();

        // Set operator button listeners
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                input += button.getText().toString();
                display.setText(input);
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = {
                R.id.buttonAdd, R.id.buttonSubtract,
                R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonEquals, R.id.buttonClear
        };

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();

                switch (buttonText) {
                    case "C":
                        clear();
                        break;
                    case "=":
                        calculate();
                        break;
                    default:
                        setOperator(buttonText);
                        break;
                }
            }
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(operatorClickListener);
        }
    }

    private void setOperator(String op) {
        if (!input.isEmpty()) {
            firstNumber = Double.parseDouble(input);
            operator = op;
            input = ""; // Clear input for the next number
            display.setText(operator);
        }
    }

    private void calculate() {
        if (!input.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(input);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        display.setText("Error");
                        input = "";
                        operator = "";
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            input = String.valueOf(result); // Store result for further calculations
            operator = "";
        }
    }

    private void clear() {
        input = "";
        operator = "";
        firstNumber = 0;
        display.setText("0");
    }
}

package com.melonsandwich.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultLabel;

    private Reference<String> firstNumber;
    private Reference<String> secondNumber;
    private Reference<String> operand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumber = new Reference<>("");
        secondNumber = new Reference<>("");
        operand = new Reference<>("");

        resultLabel = findViewById(R.id.tv_result_label);
    }

    private void changeText(View view, Reference<String> containedString, boolean doNotRewrite)
    {
        String text = ((Button)view).getText().toString();
        if (doNotRewrite)
            containedString.setValue(containedString.getValue() + text);
        else
            containedString.setValue(text);
        resultLabel.setText(containedString.getValue());
    }

    public void inputNumber(View view)
    {
        if (operand.getValue().isEmpty())
            changeText(view, firstNumber, true);
        else
            changeText(view, secondNumber, true);

    }

    public void clearAction(View view)
    {
        firstNumber.setValue("");
        secondNumber.setValue("");
        operand.setValue("");

        resultLabel.setText("");
    }

    public void inputOperand(View view)
    {
        if (operand.getValue().isEmpty())
        {
            changeText(view, operand, false);
        }
    }

    private void displayResult(double x)
    {
        if (x % 1 == 0) {
            int wholeNumber = (int)Math.round(x);
            resultLabel.setText(String.valueOf(wholeNumber));
            return;
        }
        resultLabel.setText(String.valueOf(x));
    }

    public void resultAction(View view)
    {
        if (firstNumber.getValue().equals("") || secondNumber.getValue().equals("") || operand.getValue().equals(""))
            return;

        displayResult(Operator.get(operand.getValue()).count(Double.parseDouble(firstNumber.getValue()), Double.parseDouble(secondNumber.getValue())));
    }
}
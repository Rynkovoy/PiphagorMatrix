package com.example.antonrynkovoy.piphagormatrix;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.FormatException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity{

    TextView tvNumber0, tvNumber1, tvNumber2, tvNumber3, tvNumber4, tvNumber5, tvNumber6,
            tvNumber7, tvNumber8, tvNumber9, tvNumber10, tvNumber11, tvNumber12;

    EditText etDate;
    Button btnResult;

    MatrixHandler matrixHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber0 = (TextView) findViewById(R.id.number0);
        tvNumber1 = (TextView) findViewById(R.id.number1);
        tvNumber2 = (TextView) findViewById(R.id.number2);
        tvNumber3 = (TextView) findViewById(R.id.number3);
        tvNumber4 = (TextView) findViewById(R.id.number4);
        tvNumber5 = (TextView) findViewById(R.id.number5);
        tvNumber6 = (TextView) findViewById(R.id.number6);
        tvNumber7 = (TextView) findViewById(R.id.number7);
        tvNumber8 = (TextView) findViewById(R.id.number8);
        tvNumber9 = (TextView) findViewById(R.id.number9);
        tvNumber10 = (TextView) findViewById(R.id.number10);
        tvNumber11 = (TextView) findViewById(R.id.number11);
        tvNumber12 = (TextView) findViewById(R.id.number12);

        btnResult = (Button) findViewById(R.id.btnResult);
        etDate = (EditText) findViewById(R.id.etDate);

        matrixHandler = new MatrixHandler();

    }



    public void clickButton(View view) {

            tvNumber0.setText("");
            tvNumber1.setText("");
            tvNumber2.setText("");
            tvNumber3.setText("");
            tvNumber4.setText("");
            tvNumber5.setText("");
            tvNumber6.setText("");
            tvNumber7.setText("");
            tvNumber8.setText("");
            tvNumber9.setText("");
            tvNumber10.setText("");
            tvNumber11.setText("");
            tvNumber12.setText("");

            matrixHandler.setData(etDate.getText()+"");
            //matrixHandler.setData("26.10.1996");



            matrixHandler.showFirst();
            matrixHandler.showSecond();
            matrixHandler.showThird();
            matrixHandler.showForth();
            matrixHandler.showFifth();
            matrixHandler.showSixeth();
            Toast.makeText(this, matrixHandler.getFormulas(), Toast.LENGTH_LONG).show();
            matrixHandler.setFormulas("");



        ArrayList<Integer> allNumbers = matrixHandler.getAllNumbers();
        TextView[] textViews = new TextView[]{
                tvNumber0, tvNumber1, tvNumber2, tvNumber3, tvNumber4, tvNumber5, tvNumber6
                , tvNumber7, tvNumber8, tvNumber9, tvNumber10, tvNumber11, tvNumber12};
        for (int i = 0; i < allNumbers.size(); i++) {

            switch (allNumbers.get(i)){
                case 0: tvNumber0.setText(tvNumber0.getText()+"" + allNumbers.get(i)+""); break;
                case 1: tvNumber1.setText(tvNumber1.getText()+"" + allNumbers.get(i)+""); break;
                case 2: tvNumber2.setText(tvNumber2.getText()+"" + allNumbers.get(i)+""); break;
                case 3: tvNumber3.setText(tvNumber3.getText()+"" + allNumbers.get(i)+""); break;
                case 4: tvNumber4.setText(tvNumber4.getText()+"" + allNumbers.get(i)+""); break;
                case 5: tvNumber5.setText(tvNumber5.getText()+"" + allNumbers.get(i)+""); break;
                case 6: tvNumber6.setText(tvNumber6.getText()+"" + allNumbers.get(i)+""); break;
                case 7: tvNumber7.setText(tvNumber7.getText()+"" + allNumbers.get(i)+""); break;
                case 8: tvNumber8.setText(tvNumber8.getText()+"" + allNumbers.get(i)+""); break;
                case 9: tvNumber9.setText(tvNumber9.getText()+"" + allNumbers.get(i)+""); break;
                case 10: tvNumber10.setText(tvNumber10.getText()+"" + allNumbers.get(i)+""); break;
                case 11: tvNumber11.setText(tvNumber11.getText()+"" + allNumbers.get(i)+""); break;
                case 12: tvNumber12.setText(tvNumber12.getText()+"" + allNumbers.get(i)+""); break;
            }
        }

        for (int i = 0; i < textViews.length; i++) {
            if(textViews[i].getText().equals("")){
                textViews[i].setText("-");
            }
        }
    }
}

class MatrixHandler {
    private int sum[];
    private String data = "07.03.1997";
    private String formulas = "";
    ArrayList<Integer> dataDigits;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        sum = new int[6];
        dataDigits = dataToDigits(data);
        this.data = data;
    }

    public ArrayList getAllNumbers(){
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] == 10 || sum[i] == 12) {
                dataDigits.add(sum[i]);
            }else if (sum[i] == 11){
                dataDigits.add(sum[i]);
                continue;
            }

            String numbs = sum[i] + "";
            for (int j = 0; j < numbs.length(); j++) {
                dataDigits.add(Character.getNumericValue(numbs.charAt(j)));
            }

        }
        return dataDigits;
    }

    public String getFormulas() {
        return formulas;
    }

    public void setFormulas(String formulas) {
        this.formulas = formulas;
    }

    public ArrayList<Integer> dataToDigits(String data){
        dataDigits = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            if(Character.isDigit(data.charAt(i))){
                dataDigits.add(Integer.parseInt(data.charAt(i)+""));
            }
        }
        return dataDigits;
    }



    public void showFirst(){
        formulas += "1) ";
        for (int i = 0; i < dataDigits.size(); i++) {
            sum[0] += dataDigits.get(i);
            if(i == dataDigits.size()-1) {
                formulas += (dataDigits.get(i)+"");
            }else {
                formulas += (dataDigits.get(i)+"+");
            }
        }
        formulas += ("=" + sum[0] + "\n");
    }


    public void showSecond(){
        formulas += ("\n2) ");
        if(sum[0] < 10){
            sum[1] = sum[0];
        }else {
            sum[1] =
                    Character.getNumericValue(String.valueOf(sum[0]).charAt(0)) +
                            Character.getNumericValue(String.valueOf(sum[0]).charAt(1));
        }

        for (int i = 0; i < String.valueOf(sum[0]).length(); i++) {
            if(i == String.valueOf(sum[0]).length()-1){
                formulas += (String.valueOf(sum[0]).charAt(i) + "");
            }else {
                formulas += (String.valueOf(sum[0]).charAt(i) + "+");
            }
        }
        formulas += ("=" + sum[1]+"\n");
    }

    public void showThird(){
        sum[2] = sum[0] - 2 * dataDigits.get(0);
        formulas += ("\n3) " + sum[0] + "-2*"+dataDigits.get(0)+"="+sum[2]+"\n");
    }

    public void showForth(){
        formulas += ("\n4) ");
        if(sum[0] < 10){
            sum[3] = sum[2];
        }else {
            sum[3] =
                    Character.getNumericValue(String.valueOf(sum[2]).charAt(0)) +
                            Character.getNumericValue(String.valueOf(sum[2]).charAt(1));
        }

        for (int i = 0; i < String.valueOf(sum[2]).length(); i++) {
            if(i == String.valueOf(sum[2]).length()-1){
                formulas += (String.valueOf(sum[2]).charAt(i) + "");
            }else {
                formulas += (String.valueOf(sum[2]).charAt(i) + "+");
            }
        }
        formulas += ("=" + sum[3]+"\n");
    }

    public void showFifth(){
        sum[4] = sum[0] + sum[2];
        formulas += ("\n5) " + sum[0] + "+" + sum[2] + "=" + sum[4] + "\n");
    }

    public void showSixeth(){
        sum[5] = sum[1] + sum[3];
        formulas += ("\n6) " + sum[1] + "+" + sum[3] + "=" + sum[5] + "\n");
    }
}



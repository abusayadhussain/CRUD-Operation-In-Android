package com.example.ash.tipsandsavingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormatValue = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percemtFormatValue = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double tipPercent = 0.25;
    private TextView txtBillAmount;
    private TextView txtTipPercent;
    private TextView txtTip;
    private TextView txtTotalBillAmount;

    private double totalSalary = 0.0;
    private double savingsPercent = 0.25;
    private TextView txtSavePercent;
    private TextView txtMOneySaved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBillAmount = findViewById(R.id.txtBillAmount);
        txtTipPercent = findViewById(R.id.txtTipPercent);
        txtTip = findViewById(R.id.txtTip);
        txtTotalBillAmount = findViewById(R.id.txtTotalBillAmount);

        txtTip.setText(currencyFormatValue.format(0));
        txtTotalBillAmount.setText(currencyFormatValue.format(0));

        txtSavePercent = findViewById(R.id.txtSavePercent);
        txtMOneySaved = findViewById(R.id.txtMoneySaved);

        txtMOneySaved.setText(currencyFormatValue.format(0));

        EditText edtMoneyAmount = findViewById(R.id.edtMoneyAmount);
        edtMoneyAmount.addTextChangedListener(tipEdtMoneyAmountTextWatcher);

        SeekBar seekBarPercent = findViewById(R.id.seekBarPercent);
        seekBarPercent.setOnSeekBarChangeListener(tipSeekBarChangeListener);

        EditText edtSalary = findViewById(R.id.edtSalary);
        edtSalary.addTextChangedListener(edtSalaryChangedTextWatcher);

        SeekBar seekBarSavePercent = findViewById(R.id.seekBarSavePercent);
        seekBarSavePercent.setOnSeekBarChangeListener(seekbarSavingsPercentChangeLIstener);

    }

    private final TextWatcher tipEdtMoneyAmountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try{
                billAmount = Double.parseDouble(s.toString()) / 100.00;
                txtBillAmount.setText(currencyFormatValue.format(billAmount));
            } catch (NumberFormatException e){

                txtBillAmount.setText("");
                billAmount = 0.0;
            }

            calculateTip();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final SeekBar.OnSeekBarChangeListener tipSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            tipPercent = progress / 100.00;
            calculateTip();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private  void calculateTip(){

        txtTipPercent.setText(percemtFormatValue.format(tipPercent));

        double tipValue = billAmount * tipPercent;
        double totalValue = billAmount + tipValue;

        txtTip.setText(currencyFormatValue.format(tipValue));
        txtTotalBillAmount.setText(currencyFormatValue.format(totalValue));
    }

    private  final TextWatcher edtSalaryChangedTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try{

                totalSalary = Double.parseDouble(s.toString());
                calculateSavings();
            }catch (NumberFormatException e){

                totalSalary = 0.0;
            }



        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final SeekBar.OnSeekBarChangeListener seekbarSavingsPercentChangeLIstener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            savingsPercent = progress / 100.00;

            calculateSavings();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void calculateSavings(){

        txtSavePercent.setText(percemtFormatValue.format(savingsPercent));
        double savedMoney = totalSalary * savingsPercent;

        txtMOneySaved.setText(currencyFormatValue.format(savedMoney));
    }
}

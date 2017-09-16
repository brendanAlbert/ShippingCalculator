package edu.orangecoastcollege.cs273.balbert.shippingcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
{

    // Currency formatter
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

    // Connections from Controller to View
    private EditText mWeightEditText;
    private TextView mBaseCostAmount;
    private TextView mAddedCostAmount;
    private TextView mTotalCostAmount;

    // Connect Controller to Model
    ShipItem mShipItem = new ShipItem();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connects all widgets with VIEW
        mWeightEditText = (EditText) findViewById(R.id.weightEditText);
        mWeightEditText.requestFocus();
        mBaseCostAmount = (TextView) findViewById(R.id.baseCostAmount);
        mAddedCostAmount = (TextView) findViewById(R.id.addedCostAmount);
        mTotalCostAmount = (TextView) findViewById(R.id.totalCostAmount);

        mWeightEditText.addTextChangedListener(weightAmountWatcher);
        updateViews();
    }

    private void updateViews()
    {
        mBaseCostAmount.setText(currency.format(mShipItem.getBASE_COST()));
        mAddedCostAmount.setText(currency.format(mShipItem.getAddedCost()));
        mTotalCostAmount.setText(currency.format(mShipItem.getTotalCost()));
    }

    private final TextWatcher weightAmountWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence weightText, int i, int i1, int i2)
        {
            try
            {
                double weight = Double.parseDouble(weightText.toString());
                mShipItem.setWeight(weight);
            } catch (NumberFormatException nfe)
            {
                mShipItem.setWeight(0.0);
            }
            updateViews();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}

package edu.orangecoastcollege.cs273.balbert.shippingcalculator;

/**
 * Created by brendantyleralbert on 9/15/17.
 */

public class ShipItem {
    private double mWeight;
    private final double BASE_COST = 3.00;
    private final double BASE_WEIGHT = 16.0;
    private double mAddedCost;
    private double mTotalCost;

    public ShipItem()
    {
        this.mWeight = 0.0;
        this.mAddedCost = 0.0;
        this.mTotalCost = 0.0;
    }

    public double getWeight()
    {
        return mWeight;
    }

    public double getBASE_COST()
    {
        return BASE_COST;
    }

    public double getAddedCost()
    {
        return mAddedCost;
    }

    public double getTotalCost()
    {
        return mTotalCost;
    }

    public void setWeight(double weight)
    {
        this.mWeight = weight;
        recalculate();
    }

    private void recalculate()
    {
        mTotalCost = 0.0;
        mAddedCost = 0.0;

        double weight = getWeight();

        if (weight > BASE_WEIGHT)
        {
            // everything additional beyond 16, in increments of four, is $0.50 in added cost
            // i.e. 16 ounces = $0.00 additional
            // i.e. 16.1 oz   = $0.50 additional
            // ...
            // i.e. 20 ounces = $0.50 additional
            // i.e. 20.1 oz   = %1.00 additional
            // 24 ounces = $1.00

            // 16 / 4 = (4 * .50) - $2.00 -> $0.00

            // 16.1 / 4 = (5 * .50) - $2.00 -> $0.50

            // 17 / 4 =
            // 20 / 4 = (5 * .50) - $2.00 -> $0.50
            // 24 / 4 = (6 * .50) - $2.00 -> $1.00
            mAddedCost += ( Math.ceil( (weight - 16) / 4 ) * .50 );
        }

        if ( weight > 0 )
            mTotalCost += BASE_COST;

        mTotalCost += mAddedCost;
    }
}


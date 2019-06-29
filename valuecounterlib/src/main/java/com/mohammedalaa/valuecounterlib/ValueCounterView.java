package com.mohammedalaa.valuecounterlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ValueCounterView extends ConstraintLayout {

    View rootView;
    TextView valueTextView;
    ImageView subButton;
    ImageView addButton;
    View leftSeparator, rightSeparator;
    private int minValue = 0;
    private int maxValue = 0;
    private int defaultValue = 0;
    private int valueColor = 0;
    int stepValue = 0;
    int outlineColor = 0;
    int valueTextSize = 12;
    int cornerRadius = 2;
    int strokeWidth = 2;


    public ValueCounterView(Context context) {
        super(context);
        init(context);
    }

    public ValueCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        getDefaultValues(context, attrs);
    }

    public ValueCounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getDefaultValues(context, attrs);
    }

    private void getDefaultValues(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.ValueCounterView);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float displayDensity = dm.density;

        float defaultStrokeWidth = displayDensity * 2f;
        float defaultCornerRadius = displayDensity * 2f;


        if (typedArray.hasValue(R.styleable.ValueCounterView_minValue)) {
            minValue = typedArray.getInt(R.styleable.ValueCounterView_minValue, 1);
        }
        if (typedArray.hasValue(R.styleable.ValueCounterView_maxValue)) {
            maxValue = typedArray.getInt(R.styleable.ValueCounterView_maxValue, 1);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_defaultValue)) {
            defaultValue = typedArray.getInt(R.styleable.ValueCounterView_defaultValue, 1);
            if (defaultValue < minValue || defaultValue > maxValue) {
                throw new RuntimeException("defaultValue must be in range ( minValue <= defaultValue <= maxValue)");
            }
        }


        if (typedArray.hasValue(R.styleable.ValueCounterView_valueColor)) {
            valueColor = typedArray.getInt(R.styleable.ValueCounterView_valueColor, 1);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_outlineColor)) {
            outlineColor = typedArray.getInt(R.styleable.ValueCounterView_outlineColor, 1);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_cornerRadius)) {
            cornerRadius = (int) typedArray.getDimension(R.styleable.ValueCounterView_cornerRadius, defaultCornerRadius);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_strokeWidth)) {
            strokeWidth = (int) typedArray.getDimension(R.styleable.ValueCounterView_strokeWidth, defaultStrokeWidth);
        }


        if (typedArray.hasValue(R.styleable.ValueCounterView_valueTextSize)) {
            valueTextSize = typedArray.getDimensionPixelSize(R.styleable.ValueCounterView_valueTextSize, 12);
        }


        if (typedArray.hasValue(R.styleable.ValueCounterView_addButtonColor)) {
            int color = typedArray.getInt(R.styleable.ValueCounterView_addButtonColor, 1);
            DrawableCompat.setTint(addButton.getDrawable(), color);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_subButtonColor)) {
            int color = typedArray.getInt(R.styleable.ValueCounterView_subButtonColor, 1);
            DrawableCompat.setTint(subButton.getDrawable(), color);

        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_stepValue)) {
            stepValue = typedArray.getInt(R.styleable.ValueCounterView_stepValue, 1);
        }

        setValue(defaultValue);
        setValueColor(valueColor);
        setValueTextSize(valueTextSize);
        setOutlineColor(outlineColor, cornerRadius, strokeWidth, context);

        typedArray.recycle();
    }

    private void setOutlineColor(int outlineColor, int cornerRadius, int strokeWidth, Context context) {
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(cornerRadius);
        gd.setStroke(strokeWidth, outlineColor);

        leftSeparator.setBackgroundColor(outlineColor);
        rightSeparator.setBackgroundColor(outlineColor);


        rightSeparator.getLayoutParams().height = 0;
        rightSeparator.getLayoutParams().width = strokeWidth;

        leftSeparator.getLayoutParams().height = 0;
        leftSeparator.getLayoutParams().width = strokeWidth;

        rootView.setBackgroundDrawable(gd);
    }

    private void setValueTextSize(int valueTextSize) {
        if (valueTextSize > 0) {
            valueTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, valueTextSize);
        }
    }


    private void setValueColor(int valueColor) {
        valueTextView.setTextColor(valueColor);
    }


    private void init(Context context) {
        rootView = inflate(context, R.layout.value_counter, this);
        valueTextView = (TextView) rootView.findViewById(R.id.valueTextView);

        subButton = rootView.findViewById(R.id.subButton);
        addButton = rootView.findViewById(R.id.addButton);
        leftSeparator = rootView.findViewById(R.id.separator_left);
        rightSeparator = rootView.findViewById(R.id.separator_right);

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementValue();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
            }
        });
    }

    private void incrementValue() {
        int currentVal = getValue();
        if (currentVal < maxValue) {
            setValue(currentVal + stepValue);
        }
    }

    private void decrementValue() {
        int currentVal = getValue();
        if (currentVal > minValue) {
            setValue(currentVal - stepValue);

        }
    }

    public int getValueColor() {
        return valueColor;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getValue() {
        return defaultValue;
    }

    public void setValue(int newValue) {
        int value = newValue;
        if (newValue < minValue) {
            value = defaultValue;
        } else if (newValue > maxValue) {
            value = defaultValue;
        }

        valueTextView.setText(String.valueOf(value));
        this.defaultValue = value;
    }
}

package com.mohammedalaa.valuecounterlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ValueCounterView extends ConstraintLayout {

    View rootView;
    TextView valueTextView;
    TextView labelTextView;
    ImageView subButton;
    ImageView addButton;
    private int minValue = 0;
    private int maxValue = 0;
    private int defaultValue = 0;
    private int valueColor = 0;
    String labelText = "";
    int labelColor = 0;
    int stepValue = 0;

    int valueTextSize = 8;
    int labelTextSize = 8;


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

        if (typedArray.hasValue(R.styleable.ValueCounterView_labelColor)) {
            labelColor = typedArray.getInt(R.styleable.ValueCounterView_labelColor, 1);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_valueTextSize)) {
            valueTextSize = typedArray.getDimensionPixelSize(R.styleable.ValueCounterView_valueTextSize, 8);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_labelTextSize)) {
            labelTextSize = typedArray.getDimensionPixelSize(R.styleable.ValueCounterView_labelTextSize, 8);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_labelText)) {
            labelText = typedArray.getString(R.styleable.ValueCounterView_labelText);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_addButton)) {
            int drawable = typedArray.getResourceId(R.styleable.ValueCounterView_addButton, 1);
            addButton.setBackgroundResource(drawable);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_subButton)) {
            int drawable = typedArray.getResourceId(R.styleable.ValueCounterView_subButton, 1);
            subButton.setBackgroundResource(drawable);
        }

        if (typedArray.hasValue(R.styleable.ValueCounterView_stepValue)) {
            stepValue = typedArray.getInt(R.styleable.ValueCounterView_stepValue, 1);
        }

        setValue(defaultValue);
        setValueColor(valueColor);
        setLabelText(labelText);
        setLabelColor(labelColor);
        setValueTextSize(valueTextSize);
        setLabelTextSize(labelTextSize);

        typedArray.recycle();
    }

    private void setValueTextSize(int valueTextSize) {
        valueTextView.setTextSize(valueTextSize);
    }

    private void setLabelTextSize(int labelTextSize) {
        labelTextView.setTextSize(labelTextSize);
    }

    private void setLabelText(String labelText) {
        labelTextView.setText(labelText);
    }

    private void setLabelColor(int labelColor) {
        labelTextView.setTextColor(labelColor);
    }

    private void setValueColor(int valueColor) {
        valueTextView.setTextColor(valueColor);
    }


    private void init(Context context) {
        rootView = inflate(context, R.layout.value_counter, this);
        valueTextView = (TextView) rootView.findViewById(R.id.valueTextView);
        labelTextView = (TextView) rootView.findViewById(R.id.valueLabel);

        subButton = rootView.findViewById(R.id.subButton);
        addButton = rootView.findViewById(R.id.addButton);

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
        int currentVal = getValue();//Integer.valueOf(valueTextView.getText().toString());
        if (currentVal < maxValue) {
            setValue(currentVal + stepValue);
            //valueTextView.setText(String.valueOf());
        }
    }

    private void decrementValue() {
        int currentVal = getValue();//Integer.valueOf(valueTextView.getText().toString());
        if (currentVal > minValue) {
            setValue(currentVal - stepValue);
            //valueTextView.setText(String.valueOf(currentVal - 1));
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

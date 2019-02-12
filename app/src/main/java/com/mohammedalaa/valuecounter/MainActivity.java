package com.mohammedalaa.valuecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mohammedalaa.valuecounterlib.ValueCounterView;

public class MainActivity extends AppCompatActivity {

    ValueCounterView valueCounterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valueCounterView=(ValueCounterView)findViewById(R.id.valueCounter);
    }

    public void getValue(View view) {
        Toast.makeText(this,valueCounterView.getValue()+"",Toast.LENGTH_SHORT).show();

    }
}

package com.example.user.clickcount;

import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String SELECTED_ITEM_POSITION = "ItemPosition";

    private Button Click;
    private TextView ButtonCount,BackgroundCount;
    private int ButCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Click = (Button) findViewById(R.id.Click);
        ButtonCount = (TextView) findViewById(R.id.ButtonCount);
        BackgroundCount = (TextView) findViewById(R.id.BackgroundCount);

        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButCount++;
                ButtonCount.setText(Integer.toString(ButCount));
            }
        });

    }

    protected void onSaveInstanceState(final Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt(SELECTED_ITEM_POSITION,ButCount);
    }

    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ButCount = savedInstanceState.getInt(SELECTED_ITEM_POSITION);
        ButtonCount.setText(Integer.toString(ButCount));
    }
}

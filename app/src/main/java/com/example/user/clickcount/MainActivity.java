package com.example.user.clickcount;

import android.content.Context;
import android.content.res.Configuration;
import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String SELECTED_ITEM_POSITION = "Count";
    static final String getSelectedItemPosition = "BgCount";

    private Button Click;
    private TextView ButtonCount,BackgroundCount;
    private int ButCount,BackCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            ButCount = savedInstanceState.getInt(SELECTED_ITEM_POSITION);
            BackCount = savedInstanceState.getInt(getSelectedItemPosition);
        }

        setContentView(R.layout.activity_main);
        Click = (Button) findViewById(R.id.Click);
        ButtonCount = (TextView) this.findViewById(R.id.ButtonCount);
        BackgroundCount = (TextView) this.findViewById(R.id.BackgroundCount);

        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButCount++;
                setView(MainActivity.this);
            }
        });


    }

    public void setView(Context ctx) {

        ButtonCount.setText(Integer.toString(ButCount));
        BackgroundCount.setText(Integer.toString(BackCount));
    }

    @Override
    protected void onStop() {
        super.onStop();

        BackCount++;
        setView(MainActivity.this);
    }

    protected void onSaveInstanceState(final Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt(SELECTED_ITEM_POSITION,ButCount);
        outState.putInt(getSelectedItemPosition,BackCount);
    }

    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ButCount = savedInstanceState.getInt(SELECTED_ITEM_POSITION);
        BackCount = savedInstanceState.getInt(getSelectedItemPosition);
        setView(MainActivity.this);
    }
}

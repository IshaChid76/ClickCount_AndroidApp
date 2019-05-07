package com.example.user.clickcount;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.text.DisplayContext;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final static String TAG = "AppActivity";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button Click;
    private TextView ButtonCount,BackgroundCount;
    private int ButCount,BackCount;
    private boolean isActivityChangingConfigurations = false;
    private int activityReferences = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Click = (Button) findViewById(R.id.Click);
        ButtonCount = (TextView) this.findViewById(R.id.ButtonCount);
        BackgroundCount = (TextView) this.findViewById(R.id.BackgroundCount);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor= mPreferences.edit();

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
    protected void onStart() {
        super.onStart();
        if (++activityReferences == 1 && !isActivityChangingConfigurations) {
            // App enters foreground
        }
        ButCount = mPreferences.getInt("butCount",-1);
        BackCount = mPreferences.getInt("backCount",-1);
        setView(MainActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        isActivityChangingConfigurations = this.isChangingConfigurations();

        if (--activityReferences == 0 && !isActivityChangingConfigurations) {
            BackCount++;
            // App enters background
        }

        Log.i("onStop()", Integer.toString(BackCount));
        mEditor.putInt("backCount",BackCount);
        mEditor.putInt("butCount",ButCount);
        mEditor.commit();
    }
}

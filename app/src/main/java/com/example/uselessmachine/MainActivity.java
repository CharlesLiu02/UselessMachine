package com.example.uselessmachine;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch switchUseless;
    private Button buttonSelfDestruct;
    private ConstraintLayout background;
    
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }

    private void setListeners(){
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestructSequence();
            }
        });

        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    startSwitchOffTimer();
                       //  Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startSelfDestructSequence() {
        //Disable the button
        buttonSelfDestruct.setClickable(false);
        //Start a 10 second countdown timer that updates the display every second
        startSelfDestruct();

        //want the button to show the countdown
        //destruct in 10
        //destruct in 9

        //at the end close the activity by calling the finish() method
    }

    private void startSelfDestruct() {
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                buttonSelfDestruct.setText("Destruct in " + (millisUntilFinished + 1000) / 1000);

                if(millisUntilFinished <= 10000 && millisUntilFinished % 2 == 0){
                    background.setBackgroundColor(Color.rgb(255, 0, 0));
                }
                else if(millisUntilFinished <= 10000 && millisUntilFinished % 2 != 0){
                    background.setBackgroundColor(Color.rgb(255, 255, 255));
                }
            }

            @Override
            public void onFinish() {
                finish();

            }
        }.start();
    }

    private void startSwitchOffTimer() {
        new CountDownTimer(5000, 100){


            /**
             * Callback fired on regular interval.
             *
             * @param millisUntilFinished The amount of time until finished.
             */
            @Override
            public void onTick(long millisUntilFinished) {
                if(!switchUseless.isChecked()){
                    //Log.d(TAG, "onTick: canceling");
                    cancel();
                }

            }

            /**
             * Callback fired when the time is up.
             */
            @Override
            public void onFinish() {
                switchUseless.setChecked(false);
                //Log.d(TAG, "onFinish: switch set to false");

            }
        }.start();
    }

    private void wireWidgets() {
        switchUseless = findViewById(R.id.switch_main_useless);
        buttonSelfDestruct = findViewById((R.id.button_main_selfdestruct));
        background = findViewById(R.id.layout_main_backgroundcolor);
    }
}

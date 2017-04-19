package com.example.darcie.educationalgame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private TextView timerTextView;
    private TextView sumTextView;
    GameMath mathsQuestion = new GameMath();
    private SensorManager mSensorManager;
    private ShakeDetect mSensorListener;
    private TextView answerTextEdit;
    private int answer, userAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timerTextView = (TextView) findViewById(R.id.timerTextview);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        answerTextEdit = (TextView) findViewById(R.id.answerEditText);

        sumTextView.setText(R.string.shakeMessage);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeDetect();

        String operator = mathsQuestion.chooseOperator();
        int num1 = mathsQuestion.chooseRandomNum(operator);
        int num2 = mathsQuestion.chooseRandomNum(operator);

        answer = mathsQuestion.calculateSumAnswer(num1, num2, operator);
        sumTextView.setText(mathsQuestion.generateSumString(num1, num2, operator, answer));

        mSensorListener.setOnShakeListener(new ShakeDetect.OnShakeListener() {

            public void onShake() {

                CharSequence userInput = answerTextEdit.getText();

                try {
                    userAnswer = Integer.parseInt(userInput.toString());
                } catch (Exception e){
                    Log.i("Exception", "could not convert");
                }

                if (userAnswer == answer){
                    Toast.makeText(GameActivity.this, "Correct!", Toast.LENGTH_SHORT).show();

                    String operator = mathsQuestion.chooseOperator();
                    int num1 = mathsQuestion.chooseRandomNum(operator);
                    int num2 = mathsQuestion.chooseRandomNum(operator);

                    answer = mathsQuestion.calculateSumAnswer(num1, num2, operator);
                    sumTextView.setText(mathsQuestion.generateSumString(num1, num2, operator, answer));

                    //Clear answer for user to answer next question and focus to save time.
                    answerTextEdit.setText("");
                    answerTextEdit.requestFocus();

                    //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
                else if (userInput.equals("")) {
                    userAnswer = 0;
                }
                else {
                    Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

}
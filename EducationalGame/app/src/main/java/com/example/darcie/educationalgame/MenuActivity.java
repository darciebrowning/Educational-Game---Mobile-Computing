package com.example.darcie.educationalgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button playButton;
    private Button settingsButton;
    private Button highscoresButton;
    GameMath mathe = new GameMath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        playButton = (Button) findViewById(R.id.playButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        highscoresButton = (Button) findViewById(R.id.highScoresButton);

        String operator = mathe.chooseOperator();
        int num1 = mathe.chooseRandomNum(operator);
        int num2 = mathe.chooseRandomNum(operator);
        
        int answer = mathe.calculateSumAnswer(num1, num2,operator);
        mathe.generateSumString(num1, num2, operator, answer);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playActivity = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(playActivity);
            }

        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsActivity = new Intent(MenuActivity.this, SettingsActivity.class);
                startActivity(settingsActivity);
            }
        });

        highscoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent highScoresActivity = new Intent(MenuActivity.this, HighScoresActivity.class);
                startActivity(highScoresActivity);
            }
        });

    }
}

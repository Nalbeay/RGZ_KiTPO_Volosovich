package com.example.rgr_kitpo_volosovich;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView roulette;
    private Random random;
    private int start_deegre = 0;
    private int deegre = 0;
    private static final float FACTOR = 4.86f;
    private MediaPlayer rouletteSound;
    private MediaPlayer buttonClickSound;
    private String[] numbers =
            {"32 Красный","15 Черный","19 Красный","4 Черный",
            "21 Красный","2 Черный","25 Красный","17 Черный", "34 Красный",
            "6 Черный","27 Красный","13 Черный","36 Красный","11 Черный","30 Красный",
            "8 Черный","23 Красный","10 Черный","5 Красный","24 Черный","16 Красный","33 Черный",
            "1 Красный","20 Черный","14 Красный","31 Черный","9 Красный","22 Черный","18 Красный",
            "29 Черный","7 Красный","28 Черный","12 Красный","35 Черный","3 Красный","26 Черный","0"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        rouletteSound = MediaPlayer.create(this, R.raw.roulette_sound);
        buttonClickSound = MediaPlayer.create(this, R.raw.click);
    }

    public void onClickMyButton(View View) {
        buttonClickSound.start();
        start_deegre = deegre % 360;
        deegre = random.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(start_deegre, deegre, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textView.setText("");
                rouletteSound.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText(getResult(360 - (deegre % 360)));
                rouletteSound.stop();
                rouletteSound.reset();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        roulette.startAnimation(rotate);

    }

    private void init() {
        textView = findViewById(R.id.textView);
        roulette = findViewById(R.id.roulette);
        random = new Random();

    }

    private String getResult(int deegre)
    {
        String text = "";

        int factor_x = 1;
        int factor_y = 3;
        for(int i = 0; i < 37; i++)
        {
            if (deegre >= (FACTOR * factor_x) && deegre < (FACTOR * factor_y))
            {
            text = numbers[i];
            }
            factor_x += 2;
            factor_y += 2;
        }
        if(deegre >= (FACTOR * 73) && deegre < 360 || deegre >= 0 && deegre < (FACTOR * 1)) text = numbers[numbers.length - 1];

        return text;
    }
}
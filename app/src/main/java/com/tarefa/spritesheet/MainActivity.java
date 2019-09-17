package com.tarefa.spritesheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.tarefa.spritesheet.animate.SpriteSheet;

public class MainActivity extends AppCompatActivity {

    DisplayMetrics displayMetrics;
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SpriteSheet charac = new SpriteSheet(
                BitmapFactory.decodeResource(getResources(),R.drawable.charac),
                new int[] {4, 4, 4, 4},
                0,
                new Rect(0,0,256,192)
        );

        charac.posX = 100;
        charac.posY = 100;

        SpriteSheet charac2 = new SpriteSheet(
                BitmapFactory.decodeResource(getResources(),R.drawable.charac2),
                new int[] {8, 8},
                0,
                new Rect(0,0,108,140)
        );

        charac2.posX = 100;
        charac2.posY = 120;

        displayMetrics = new DisplayMetrics();

        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        drawView = new DrawView(this, displayMetrics.widthPixels, displayMetrics.heightPixels, new SpriteSheet[] {charac, charac2});

        setContentView(drawView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        drawView.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawView.resume();
    }
}
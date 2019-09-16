package com.tarefa.spritesheet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.constraintlayout.solver.widgets.Rectangle;

import com.tarefa.spritesheet.animate.SpriteSheet;

public class DrawView extends SurfaceView implements Runnable {

    boolean isRunning;
    SurfaceHolder surfaceHolder;
    Thread gameLoop;
    SpriteSheet[] spriteSheets;

    public DrawView(Context context, int width, int height) {

        super(context);

        surfaceHolder = getHolder();
    }

    public DrawView(Context context, int width, int height, SpriteSheet[] spriteSheets) {

        super(context);

        surfaceHolder = getHolder();

        this.spriteSheets = spriteSheets;
    }

    public void resume() {

        isRunning = true;

        gameLoop = new Thread(this);
        gameLoop.start();

    }

    public void stop() {

        isRunning = false;

        try {
            gameLoop.join();
        } catch (InterruptedException ie) {

        }
    }

    @Override
    public void run() {

        while (isRunning) {

            update();
            render();

            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {

            }
        }
    }

    void update() {

    }

    void render() {

        if (!surfaceHolder.getSurface().isValid())
            return;

        Canvas canvas = surfaceHolder.lockCanvas();

        if (spriteSheets == null){
            surfaceHolder.unlockCanvasAndPost(canvas);
            return;
        }

        canvas.drawColor(Color.WHITE);

        for (int i = 0; i < spriteSheets.length; i++) {

                SpriteSheet s = spriteSheets[i];

                Rect tA = new Rect(s.getSrc());
                Rect tB = new Rect(s.getDst());

                canvas.drawBitmap(s.getBitmap(),
                        tA,
                        tB,
                        null
                );

                s.nextFrame();
        }

        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
package com.tarefa.spritesheet.animate;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class SpriteSheet {

    public int posX;
    public int posY;
    private int actualRow;
    private int actualFrame;
    private Bitmap bitmap;
    private int frameCount;
    private int[] frameCountByRow;
    private Rect rect;

    public SpriteSheet(Bitmap bitmap, int[] frameCountByRow, Rect rect)
    {
        this.bitmap = bitmap;
        this.frameCountByRow = frameCountByRow;
        this.rect = rect;
        this.actualRow = 0;
        this.actualFrame = 0;
    }

    public SpriteSheet(Bitmap bitmap, int[] frameCountByRow, int defaultAnimation, Rect rect){
        this.bitmap = bitmap;
        this.frameCountByRow = frameCountByRow;
        this.rect = rect;
        this.actualRow = defaultAnimation;
        this.frameCount = this.frameCountByRow[this.actualRow];
        this.actualFrame = 0;
    }

    public void SetAnimation(int value){
        if (value < 0 || value > frameCountByRow.length)
            return;

        this.actualRow = value;
        frameCount = frameCountByRow[this.actualRow];
        this.actualFrame = 0;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int nextFrame(){
        if (actualFrame >= frameCountByRow[actualRow] - 1)
        {
            actualFrame = 0;
            return actualFrame;
        }

        actualFrame++;
        return  actualFrame;
    }

    public Rect getSrc() {
        return new Rect(rect.right * actualFrame, actualRow * rect.bottom, rect.right, rect.bottom);
    }

    public Rect getDst(){
        return new Rect(posX,posY,rect.right,rect.bottom);
    }
}


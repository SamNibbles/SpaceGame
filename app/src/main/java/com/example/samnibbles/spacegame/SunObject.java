package com.example.samnibbles.spacegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class SunObject extends BasePlanet  {

    //Variables

    public SunObject() {
        //The big 'old sun
        this.pos = new Vector(Constants.WIDTH / 2, Constants.HEIGHT / 2);
        this.color = Color.YELLOW;
        this.radius = 50;
    }
}

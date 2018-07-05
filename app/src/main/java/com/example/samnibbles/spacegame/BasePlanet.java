package com.example.samnibbles.spacegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.Random;

public class BasePlanet {

    //Variables
    protected Bitmap image;
    protected Vector pos;
    protected float radius;
    protected int mass = 50;
    protected int color;
    protected int G = 100;

    public BasePlanet() {

    }

    public Vector attract(Vector objectPos, int objectMass) {
        Vector force = this.pos.sub(objectPos).normalize();
        double distance = this.pos.sub(objectPos).length();
        double strength = G * this.mass * objectMass * distance / Math.pow(Math.abs(distance),3);
        Math.min(Math.max(strength, 10), 5);
        force = force.mul(strength);
        return force;
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle((float)pos.x, (float)pos.y, radius, paint);
    }
}

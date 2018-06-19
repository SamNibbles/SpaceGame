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

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    protected Vector pos;
    protected Vector dim;
    protected float radius;
    protected float mass = 50;
    protected double centreX;
    protected double centreY;
    protected int color;
    protected int G = 50;
    protected double speed = 2;

    public BasePlanet() {

    }

    public Vector attract(PlanetObject planetObject) {
        Vector force = this.pos.sub(planetObject.pos).normalize();
        double distance = this.pos.sub(planetObject.pos).length();
        double strength = G * this.mass * planetObject.mass * distance / Math.pow(Math.abs(distance),3);
        force = force.mul(strength);
        return force;
    }

    public void update() {

    }

    public void onTouchEvent(MotionEvent event) {

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle((float)pos.x, (float)pos.y, radius, paint);
    }
}

package com.example.samnibbles.spacegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PlayerObject {

    //Variables
    private Bitmap image;
    private Vector pos;
    private Vector vel;
    private float radius;
    private float mass = 50;
    private int color;
    private Vector targetPos;

    private float scaleX;
    private float scaleY;

    public PlayerObject(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.pos = new Vector(0, 0);
        this.color = Color.MAGENTA;
        this.radius = 15;
        this.vel = new Vector(0, 0);
        this.targetPos = this.pos;
    }

    public void update() {
        this.vel = this.targetPos.sub(this.pos).normalize().mul(10);
        this.pos = this.pos.add(this.vel);
    }

    public void onTouchEvent(MotionEvent event) {
        this.targetPos = new Vector(event.getX() / scaleX, event.getY() / scaleY);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle((float)this.pos.x, (float)this.pos.y, this.radius, paint);
    }

}

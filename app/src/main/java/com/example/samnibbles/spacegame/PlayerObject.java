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
    private Vector acc;
    private Vector vel;
    private int radius;
    private int mass = 10;
    private int color;
    private Vector targetPos;
    private int attracted = -1;
    private boolean inPursuit = false;

    //GETS AND SETS
    public Vector getPos() {return pos;}
    public void setPos(Vector pos) {this.pos = pos;}

    public int getMass() {return mass;}
    public void setMass(int mass) {this.mass = mass;}

    public int getAttracted() {return attracted;}
    public void setAttracted(int attracted) {this.attracted = attracted;}

    public Vector getVel() {return vel;}
    public void setVel(Vector vel) {this.vel = vel;}

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
        this.acc = new Vector(0,0);
    }


    public void applyForce(Vector force) {
        Vector f = force.div(this.mass);
        this.acc = this.acc.add(f);
    }

    public Vector getTargetUnitVec(){
        return this.targetPos.sub(this.pos).normalize();
    }

    public void update() {
        //If not attracted to a planet move to cursor target selection

        //Constrain accelerations
        this.acc.x = Math.min(Math.max(this.acc.x, -5), 5);
        this.acc.y = Math.min(Math.max(this.acc.y, -5), 5);

        this.vel = this.vel.add(this.acc);

        //When close to target, end pursuit
        /*if (inPursuit) {
            if (Math.abs(this.targetPos.sub(this.pos).x) < 5 && Math.abs(this.targetPos.sub(this.pos).y) < 5) {
                this.vel = new Vector(0, 0);
                this.targetPos = this.pos;
                inPursuit = false;
            }
        }*/

        this.pos = this.pos.add(this.vel);
        acc = new Vector(0,0);
    }

    public void onTouchEvent(MotionEvent event) {
        this.inPursuit = true;
        this.targetPos = new Vector(event.getX() / scaleX, event.getY() / scaleY);
        this.vel = new Vector(0,0);
        this.applyForce(getTargetUnitVec().mul(20));
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle((float)this.pos.x, (float)this.pos.y, this.radius, paint);
    }

}

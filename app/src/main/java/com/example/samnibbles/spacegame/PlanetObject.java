package com.example.samnibbles.spacegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.io.Console;

public class PlanetObject extends BasePlanet {

    //Variables
    private Vector vel;
    private Vector acc;

    public PlanetObject(int dis, int mass, float radius, int color) {
        this.pos = new Vector(Constants.WIDTH / 2 + dis, Constants.HEIGHT / 2 );
        this.acc = new Vector(0, 0);
        this.vel = new Vector(0,5).normalize();

        double force = Math.sqrt((this.G * this.mass) / dis);
        this.vel = this.vel.mul(force);

        this.radius = radius;
        this.color = color;
        this.mass = mass;
    }

    public void applyForce(Vector force) {
        Vector f = force.div(this.mass);
        this.acc = this.acc.add(f);
    }

    public void update() {
        this.vel = this.vel.add(this.acc);
        this.pos = this.pos.add(vel);
        acc = new Vector(0,0);
    }
}

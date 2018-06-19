package com.example.samnibbles.spacegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PlanetHandler {

    //Variables
    private SunObject sunObject;
    private int planetsTotal = 5;
    private PlanetObject planets[] = new PlanetObject[planetsTotal];

    public PlanetHandler() {
        sunObject = new SunObject();
        planets[0] = new PlanetObject(75, 5, 15, Color.GREEN);
        planets[1] = new PlanetObject(150, 5, 15, Color.CYAN);
        planets[2] = new PlanetObject(225, 5, 15, Color.RED);
        planets[3] = new PlanetObject(300, 5, 15, Color.WHITE);
        planets[4] = new PlanetObject(375, 5, 15, Color.BLUE);
    }

    public void update() {
        sunObject.update();
        for (int i = 0; i < planetsTotal; i++) {
            planets[i].update();
            planets[i].applyForce(sunObject.attract(planets[i]));
        }
    }

    public void draw(Canvas canvas) {
        sunObject.draw(canvas);
        for (int i = 0; i < planetsTotal; i++)
            planets[i].draw(canvas);
    }
}

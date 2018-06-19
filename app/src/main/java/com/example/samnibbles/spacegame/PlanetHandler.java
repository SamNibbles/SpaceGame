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
    private int planetNumber = -1;

    public PlanetHandler() {
        sunObject = new SunObject();
        planets[0] = new PlanetObject(75, 50, 30, Color.GREEN);
        planets[1] = new PlanetObject(150, 50, 30, Color.CYAN);
        planets[2] = new PlanetObject(225, 50, 30, Color.RED);
        planets[3] = new PlanetObject(300, 50, 30, Color.WHITE);
        planets[4] = new PlanetObject(375, 150, 30, Color.BLUE);
    }

    public void update() {
        sunObject.update();
        for (int i = 0; i < planetsTotal; i++) {
            planets[i].update();
            planets[i].applyForce(sunObject.attract(planets[i].pos, planets[i].mass));
        }
    }

    public Vector attraction(Vector playerPos, int playerMass) {
        for (int i = 0; i < planetsTotal; i++) {
            if (Math.abs(playerPos.sub(planets[i].pos).x) < 50 && Math.abs(playerPos.sub(planets[i].pos).y) < 50)
                planetNumber = i;
                return planets[i].attract(playerPos, playerMass);
        }
        return new Vector(0, 0);
    }

//    public Boolean collision(Vector playerPos, int playerMass) {
//        for (int i = 0; i < planetsTotal; i++) {
//            if (Math.abs(playerPos.sub(planets[i].pos).x) < 50 && Math.abs(playerPos.sub(planets[i].pos).y) < 50)
//                return true;
//        }
//        return false;
//    }

    public void draw(Canvas canvas) {
        sunObject.draw(canvas);
        for (int i = 0; i < planetsTotal; i++)
            planets[i].draw(canvas);
    }
}

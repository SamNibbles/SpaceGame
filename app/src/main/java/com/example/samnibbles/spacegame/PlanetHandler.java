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
        //Create planets
        sunObject = new SunObject();
        planets[0] = new PlanetObject(75, 50, 30, Color.GREEN);
        planets[1] = new PlanetObject(150, 50, 30, Color.CYAN);
        planets[2] = new PlanetObject(225, 50, 30, Color.RED);
        planets[3] = new PlanetObject(300, 50, 30, Color.WHITE);
        planets[4] = new PlanetObject(375, 50, 30, Color.BLUE);
    }

    public void update() {
        //Not used
        sunObject.update();

        //Updates planets orbit around sun
        for (int i = 0; i < planetsTotal; i++) {
            planets[i].update();
            planets[i].applyForce(sunObject.attract(planets[i].pos, planets[i].mass));
        }
    }

    public boolean inRange(Vector playerPos, int planetNum) {
        //Checks for first planet within range of 100, biased to planet 0 (inner planet)
            if (Math.abs(playerPos.sub(planets[planetNum].pos).x) < 200 && Math.abs(playerPos.sub(planets[planetNum].pos).y) < 200)
                return true;
            else
                return false;

    }

    public Vector attract_force(PlayerObject playerObject){
        Vector total_force = new Vector(0,0);

        for (int i = 0; i < planetsTotal; i++) {
            if (inRange(playerObject.getPos(), i)){
                total_force = total_force.add(planets[i].attract(playerObject.getPos(), playerObject.getMass()));
            }
        }
        //Returns force generated from planet instance attracted function
        return total_force;
    }

    public void draw(Canvas canvas) {
        sunObject.draw(canvas);
        for (int i = 0; i < planetsTotal; i++)
            planets[i].draw(canvas);
    }
}

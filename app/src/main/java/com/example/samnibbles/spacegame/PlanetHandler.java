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

    public int inRange(Vector playerPos) {
        //Checks for first planet within range of 100, biased to planet 0 (inner planet)
        for (int i = 0; i < planetsTotal; i++) {
            if (Math.abs(playerPos.sub(planets[i].pos).x) < 100 && Math.abs(playerPos.sub(planets[i].pos).y) < 100)
                return i; //Returns first planet within range as int
        }
        return -1;
    }

    public Vector attraction(PlayerObject playerObject) {
        //Gets attraction force from planet being orbited
        if (playerObject.isAttracted() != -1) {

            playerObject.setVel(playerObject.getPos().perp(planets[playerObject.isAttracted()].pos));
            double force = Math.sqrt((Constants.GRAVITY * planets[playerObject.isAttracted()].mass) / (planets[playerObject.isAttracted()].pos.sub(playerObject.getPos())).length()); //does stuff
            playerObject.setVel(playerObject.getVel().mul(force));

            playerObject.setVel(playerObject.getVel().add(planets[playerObject.isAttracted()].getVel()));

            return planets[playerObject.isAttracted()].attract(playerObject.getPos(), playerObject.getMass());
        } else
            return new Vector(0,0);
    }

    public void draw(Canvas canvas) {
        sunObject.draw(canvas);
        for (int i = 0; i < planetsTotal; i++)
            planets[i].draw(canvas);
    }
}

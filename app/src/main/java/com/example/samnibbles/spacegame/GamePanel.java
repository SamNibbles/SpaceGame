package com.example.samnibbles.spacegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;

/**
 * Created by Sam Niblett on 26/01/2017.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    //CLASSES
    private MainThread thread;
    private PlanetHandler planetHandler;

    //SCALE CANVAS FOR DIFFERENT SIZED SCREENS
    private float scaleFactorX;
    private float scaleFactorY;

    public GamePanel(Context context)
    {
        super(context);

        getHolder().setFormat(PixelFormat.RGBA_8888);
        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

        //INITIALISE CLASSES
        planetHandler = new PlanetHandler();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try{
                thread.setRunning(false);
                thread.join();
            } catch(InterruptedException e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //INITIALISE CLASSES
        thread = new MainThread(holder, this);

        //SETUP SCALE FACTOR FOR DIFFERENT SIZED SCREENS
        scaleFactorX = getWidth()/(Constants.WIDTH * 1.f);
        scaleFactorY = getHeight() /(Constants.HEIGHT * 1.f);

        //Initialise Class Scale Factors

        //START RUNNING THREAD
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

        }
        //uiHandler.onTouchEvent(event);
        return true;
    }

    public void update() {
        planetHandler.update();
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);

            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            canvas.drawRect(new RectF(0, 0, Constants.WIDTH, Constants.HEIGHT), paint);

            planetHandler.draw(canvas);

            canvas.restoreToCount(savedState);
        }
    }
}

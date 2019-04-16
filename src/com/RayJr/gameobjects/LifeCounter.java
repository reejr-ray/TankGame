package com.RayJr.gameobjects;

import java.awt.*;

public class LifeCounter implements Drawable {
    private static int WIDTH = 10;
    private static int HEIGHT = 10;
    private static int SEPARATION = 13;
    private static int TANK_OFFSET = -3;
    private int count;
    private int x, y;
    private Color color;

    public LifeCounter(int count, int x, int y){
        this.count = count;
        this.x = x;
        this.y = y;
        this.color = Color.blue;
    }

    @Override
    public boolean isDrawable(){ return true; }

    @Override
    public void drawImage(Graphics g){

    }
}

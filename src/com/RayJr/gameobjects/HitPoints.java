package com.RayJr.gameobjects;

import java.awt.*;

public class HitPoints implements Drawable {

    private static int WIDTH = 50;
    private static int HEIGHT = 10;
    private static int SEPARATION = 30;
    private static int TANK_OFFSET = 3;
    private int healthLeft;
    private int damageResistance;
    private int x, y;
    private Color color;

    public HitPoints(int healthLeft, int x, int y){
        this.healthLeft = healthLeft;
        this.damageResistance = 0;
        this.x = x;
        this.y = y;
        this.color = Color.red;
    }

    @Override
    public boolean isDrawable(){ return true; }

    @Override
    public void drawImage(Graphics g){

    }
}

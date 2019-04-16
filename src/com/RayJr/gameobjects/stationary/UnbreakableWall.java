package com.RayJr.gameobjects.stationary;

import com.RayJr.gameobjects.Collidable;
import com.RayJr.gameobjects.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall implements Drawable, Collidable {


    private BufferedImage img;
    private int width;
    private int height;
    private int x, y;

    public UnbreakableWall(BufferedImage img, int coordX, int coordY){
        this.img = img;
        this.x = coordX;
        this.y = coordY;
        this.width = img.getWidth();
        this.height = img.getHeight();
    }

    @Override
    public int getWidth(){ return this.width; }
    @Override
    public int getHeight(){ return this.height;}
    @Override
    public int getCoordX(){ return this.x; }
    @Override
    public int getCoordY(){ return this.y; }

    @Override
    public void drawImage(Graphics g){
        g.drawImage(this.img, this.x, this.y,null);
    }

    @Override
    public boolean isDrawable(){ return true; }
}

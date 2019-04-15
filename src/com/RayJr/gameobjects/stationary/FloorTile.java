package com.RayJr.gameobjects.stationary;

import com.RayJr.gameobjects.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FloorTile implements Drawable {
    private BufferedImage img;
    private int width, height;
    private int x, y; // location of tile
    private int edgeX, edgeY;

    public FloorTile(BufferedImage img, int posX, int posY){
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.x = posX;
        this.y = posY;
        this.edgeX = x + width;
        this.edgeY = y + height;

    }

    public int getWidth(){ return this.width; }
    public int getHeight(){ return this.height; }

    public int getEdgeX(){ return this.edgeX; }
    public int getEdgeY(){ return this.edgeY; }

    @Override
    public void drawImage(Graphics g){
        g.drawImage(this.img, this.x, this.y, null);
    }

    @Override
    public boolean isDrawable(){ return true; }
}

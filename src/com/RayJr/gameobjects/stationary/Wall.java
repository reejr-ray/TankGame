package com.RayJr.gameobjects.stationary;

import com.RayJr.gameobjects.Collidable;

import java.awt.image.BufferedImage;

public abstract class Wall {
    private BufferedImage img;
    private int width;
    private int height;

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getCoordX();
    public abstract int getCoordY();

    public Wall(){

    }


}

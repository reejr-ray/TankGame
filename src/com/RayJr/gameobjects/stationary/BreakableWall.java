package com.RayJr.gameobjects.stationary;

import com.RayJr.gameobjects.Collidable;
import com.RayJr.gameobjects.Drawable;

import java.awt.*;

public class BreakableWall extends Wall implements Drawable, Collidable {

    @Override
    public void drawImage(Graphics g){

    }

    @Override
    public boolean isDrawable(){
        // TODO
       return true;
    }

}

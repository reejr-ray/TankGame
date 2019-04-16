/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RayJr.gameobjects.moveable;

/**
 *
 */
import com.RayJr.gameobjects.Collidable;
import com.RayJr.gameobjects.Drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Rectangle;

public class Bullet implements Drawable, Collidable {

    private int x, y, width, height;
    private int Yspeed, Xspeed;
    private int angle;
    private BufferedImage img;
    private int damage;
    private boolean show;
    
    public Bullet(BufferedImage img, int x, int y, int damage, int Xspeed, int Yspeed, int angle){

        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.damage = damage;
        this.angle = angle;
        this.Xspeed = Xspeed;
        this.Yspeed = Yspeed;
        this.show = true;
    }
         
    public int getDamage(){
        return this.damage;
    }
    public void setShow(boolean s){
        this.show = s;
    }
    public void update(int w, int h){ // TODO - change h and w to something useful
        if(y < h-40 && y > 0 && x > 0 && x < w-40 && show){
            x = x + Xspeed;
            y = y + Yspeed;
        }
        else{
            this.show = false;
        }
    }

    @Override
    public boolean isDrawable(){ return show; }

    @Override
    public void drawImage(Graphics g) {
        if(show) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.img, rotation, null);
        }
     }
}

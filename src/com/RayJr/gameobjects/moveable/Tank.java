package com.RayJr.gameobjects.moveable;

import com.RayJr.MapLayout;
import com.RayJr.TRE;
import com.RayJr.gameobjects.Collidable;
import com.RayJr.gameobjects.Drawable;
import com.RayJr.gameobjects.HitPoints;
import com.RayJr.gameobjects.LifeCounter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author anthony-pc
 */
public class Tank implements Drawable, Collidable {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private int shootingSpeed;
    private int damagePerShot;

    private final int R = 2;
    private final int ROTATIONSPEED = 3;
    private final int MAX_AMMO_COUNT = 30;

    private Vector<Bullet> ammo;
    private int ammoUsed;

    private BufferedImage img;
    private BufferedImage playerBullet;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;

    private boolean show;
    private boolean isPlayer1;
    private LifeCounter life;
    private HitPoints hp;


    public Tank(boolean isPlayer1, int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.playerBullet = null;
        this.ammo = new Vector<>();
        this.ammoUsed = 0;
        this.life = new LifeCounter(4, x, y);
        this.hp = new HitPoints(100, x, y);

        try {
            if (isPlayer1) {
                playerBullet = ImageIO.read(TRE.class.getResource("resources/p1_bullet.png"));
            } else {
                playerBullet = ImageIO.read(TRE.class.getResource("resources/p2_bullet.png"));
            }
            // only if playerBullet is initialized and not null
            for(int i = 0; i < MAX_AMMO_COUNT; i++){
                Bullet b = new Bullet(playerBullet,x, y, damagePerShot, shootingSpeed, shootingSpeed, angle);
                ammo.add(b);
            }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }


        this.isPlayer1 = isPlayer1;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.show = true;

    }


    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void toggleShoot() { this.ShootPressed = true; }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void unToggleShoot() { this.ShootPressed = false; }



    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if(this.ShootPressed){
            this.shoot();
        }


    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }

    private void shoot(){
        /**
         * TODO make the tank shoot on a cooldown
         */
        this.ammo.get(ammoUsed).setShow(true);


        // create a bullet object at a certain speed
        //
    }




    private void checkBorder() {
        // MapLayout map = TRE.getGameMap(); ?????? why is this method static???
        if (x < 30) {
            x = 30;
        }
        if (x >= TRE.SCREEN_WIDTH - 88) {
            x = TRE.SCREEN_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
    }
        if (y >= TRE.SCREEN_HEIGHT - 80) {
            y = TRE.SCREEN_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    @Override
    public boolean isDrawable(){ return show; }

    @Override
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
        for(Bullet b : ammo) {
            if(b.isDrawable())
                //b.update(10,10);
                b.drawImage(g2d);

        }
    }




}

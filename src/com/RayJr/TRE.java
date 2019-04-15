/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RayJr;

import com.RayJr.gameobjects.moveable.Tank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 *
 * @author anthony-pc
 */
public class TRE extends JPanel implements Runnable {


    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;
    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private BufferStrategy bufferStrategy;
    private Tank t1;
    private Tank t2;
    private MapLayout gameMap;

    private boolean running;
    private Thread thread;

    private synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException ignored){

        }
        System.exit(1);
    }

    public synchronized void run(){
        // all "this" will refer to trex, which start() was called on.
        long lastTime = System.nanoTime();
        final double numFPS = 60.0;
        double nanosec = 1000000000 / numFPS; //ns is 10^-9 seconds
        double change = 0;
        while(running){
            /** This time checking block allows for slower computers to run
             * at the same speed as higher spec computers. Instead of allowing the app to run at
             * unlimited fps, it caps that to a maximum of 60.
             */
            long now = System.nanoTime(); // in case computer is running slowly, check time again
            change += (now - lastTime) / nanosec;
            lastTime = now;
            if(change >= 1){
                //tick();
                change--;
            }
            // ---------- IMAGE RENDERING --------------
            this.t1.update();
            this.t2.update();

            this.repaint();
            System.out.println(this.t1);
            try {
                Thread.sleep(1000 / 144);
            } catch(InterruptedException ignored){

            }
            // ------------ END IMAGE RENDERING ----------
        }
        stop();
    }

    private void tick(){
        this.t1.update();
        this.t2.update();

    }

    public static void main(String[] args) {
        Thread x;
        TRE trex = new TRE();
        trex.init();
        trex.start();
       /*
        try {

            while (true) {
                trex.t1.update();
                trex.t2.update();
                trex.repaint();
                System.out.println(trex.t1);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }
        */


    }




    private void init() {
        this.jf = new JFrame("Tank Rotation");
        this.world = new BufferedImage(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.running = false;
        this.gameMap = new MapLayout();
        BufferedImage t1img = null, t2img = null;
        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            t1img = ImageIO.read(this.getClass().getResource("resources/tank1.png"));
            t2img = ImageIO.read(this.getClass().getResource("resources/tank1.png"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        t1 = new Tank(true,200, 200, 0, 0, 0, t1img);
        t2 = new Tank(false, 300, 300, 0, 0, 0, t2img);


        TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);


        this.jf.setSize(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);
        this.gameMap.drawAllTiles(g2);
        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);
        g2.drawImage(world,0,0,null);

    }


}

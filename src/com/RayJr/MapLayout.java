package com.RayJr;

import com.RayJr.gameobjects.stationary.BreakableWall;
import com.RayJr.gameobjects.stationary.FloorTile;
import com.RayJr.gameobjects.stationary.UnbreakableWall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class MapLayout {

    private int x, y;
    private final int PIXELS_PER_CHAR = 32;
    private String bWallPath, ubWallPath, floorPath;
    private ArrayList<BreakableWall> bWall;
    private ArrayList<UnbreakableWall> ubWall;
    private ArrayList<FloorTile> background;
    // private PowerUp mypowerup;

    /** Allows for the parsing of txt files into maps usable by the Tank Game. This is a convient way to make a pseudo
     * map editor. each character in the .txt file, as long as its located in the resources folder, will count as
     * 32 pixels in the game world.
     * @param filename name for the .txt file map
     */
    private void parseMap(String filename){

        //ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(getClass().getResource(filename).getFile());
        if(file.exists()){ // then read it.
            try {
                BufferedImage img;
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line;
                int mapLength = 0, mapHeight = 0;
                while((line = in.readLine()) != null){
                    mapLength = 0; // reset after row is done

                    for(int i = 0; i <= (line.length() - 1); ++i){
                        mapLength = i; // iterate over every char
                        int tempX = mapLength * PIXELS_PER_CHAR; // convert to pixels
                        int tempY = mapHeight * PIXELS_PER_CHAR; // convert to pixels
                        char temp = line.charAt(i);
                        if(Character.toLowerCase(temp) == 'b'){ // if breakable wall
                            try{
                                img = ImageIO.read(this.getClass().getResource(bWallPath));
                                BreakableWall b = new BreakableWall(img,tempX,tempY); // create at corresponding x and y
                                bWall.add(b);
                            }catch(IOException ignored){}
                        } else if(Character.toLowerCase(temp) == 'u') { // if unbreakable wall
                            try{
                                img = ImageIO.read(this.getClass().getResource(ubWallPath));
                                UnbreakableWall ub = new UnbreakableWall(img,tempX,tempY); //create at corresponding x and y
                                ubWall.add(ub);
                            } catch (IOException ignored){}

                        }  // else if(Character.toLowerCase(temp) == 'p') // powerup?
                    }
                    mapHeight++; // only increment height after row and object creation is done,

                }
                mapLength *= PIXELS_PER_CHAR;
                mapHeight *= PIXELS_PER_CHAR;
                this.x = mapLength;
                this.y = mapHeight;
            } catch(FileNotFoundException f){
                System.out.println(f.getMessage());
            } catch(IOException ignored){}
        }
    }

    public MapLayout(){
        String filename = "resources/Wasteland.txt";
        this.bWallPath = "resources/Wall2.gif";
        this.ubWallPath = "resources/Wall1.gif";
        this.floorPath = "resources/background.bmp";
        this.bWall = new ArrayList<>();
        this.ubWall = new ArrayList<>();
        this.background = new ArrayList<>();
        parseMap(filename);
        BufferedImage img;
        try {
            int posX = 0, posY = 0;
            img = ImageIO.read(this.getClass().getResource(floorPath));

            int numTilesX = x / img.getWidth();
            int numTilesY = y / img.getHeight();
            for(int i = 0; i < numTilesX; ++i){

                FloorTile tile = new FloorTile(img, posX, posY);
                background.add(tile);
                for(int k = 1; k < numTilesY; ++k){
                    posY += img.getHeight();
                    tile = new FloorTile(img, posX, posY);
                    background.add(tile);
                }
                posY = 0;
                posX += img.getWidth();
            }
        } catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }

    public void update(){
        /*
        for(BreakableWall b : bWall){
            b.update();
        }
        */
    }
    public void drawAllTiles(Graphics g){
        for(FloorTile f : background)
            f.drawImage(g);
        for(int i = 0; i < bWall.size(); i++){
            BreakableWall b = bWall.get(i);
            if(!b.isDrawable()) {
                bWall.remove(i);
                i--;
            } else
                b.drawImage(g);
        }
        for(UnbreakableWall ub : ubWall)
            ub.drawImage(g);
    }

    public int getX(){ return x;}
    public int getY(){ return y;}

}

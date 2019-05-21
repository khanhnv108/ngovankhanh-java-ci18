package game.renderer;

import game.GameObject;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File; // file || directory
import java.util.ArrayList;

public class Renderer {
    BufferedImage image;
    ArrayList<BufferedImage> images;
    public  int currentImageIndex;
    public int frameCount;

    public Renderer(String url) {
        File source = new File(url);
        if (source.exists() && source.isFile()) {
            image = SpriteUtils.loadImage(url);
        }
        if (source.exists() && source.isDirectory()) {
            images = SpriteUtils.loadImages(url);
            currentImageIndex = 0;
            frameCount = 0;
        }
    }
    /**
     * 1. Draw single image
     * 2. Draw animation
     * @param g
     * @param master
     */

    public void render(Graphics g, GameObject master) {
        if (image != null) {
            // draw single image
            g.drawImage(image, (int)master.position.x, (int)master.position.y, null);
        }
        if (images != null) {
            // draw animation
            BufferedImage currentImage = images.get(currentImageIndex);
            g.drawImage(currentImage, (int) master.position.x, (int) master.position.y, null);
            frameCount++;
            if (frameCount > 6){
                currentImageIndex++;
                if (currentImageIndex >= images.size()) {
                    currentImageIndex = 0;
                }
                frameCount = 0;
            }
        }

    }
}

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
    public boolean isOnce;

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

    public Renderer(String url, boolean isOnce) {
        this(url);
        this.isOnce = isOnce;
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
            drawImage(g, image, master);
        }
        if (images != null) {
            // draw animation
            BufferedImage currentImage = images.get(currentImageIndex);
            drawImage(g, currentImage, master);
            frameCount++;
            if (frameCount > 6){
                currentImageIndex++;
                if (currentImageIndex >= images.size()) {
                    if (isOnce){
                        master.deactive();
                    }
                    currentImageIndex = 0;
                }
                frameCount = 0;
            }
        }
    }

    private void drawImage(Graphics g, BufferedImage image, GameObject master) {
        // image.getWidth()
        // master.anchor.x
        // image.getHeight()
        // master.anchor.y

        g.drawImage(image,(int)(master.position.x - master.anchor.x * image.getWidth()),
                (int)(master.position.y - master.anchor.y * image.getHeight()),
                null );

        // draw hitBox for master
        // useful when developing

        if (master.hitBox != null) {
            g.setColor(Color.GREEN);
            g.drawRect(
                    (int) master.hitBox.left(),
                    (int) master.hitBox.top(),
                    master.hitBox.width,
                    master.hitBox.height
            );
        }
    }
}

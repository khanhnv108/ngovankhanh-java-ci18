import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    public Vector2D position;
    public BufferedImage image;

    public Background() {
        this.position = new Vector2D();
        this.image = SpriteUtils.loadImage("assets/images/background/0.png");
    }

    public void render(Graphics g) {
        g.drawImage(this.image, (int) this.position.x, (int) this.position.y, null);
    }

    public void run() {
        this.position.y ++;
        if (this.position.y >= 0) {
            this.position.y = 0;
        }
    }
}

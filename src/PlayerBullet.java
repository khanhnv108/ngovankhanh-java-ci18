import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBullet {
    public Vector2D position;
    public BufferedImage image;

    public Vector2D position1;
    public BufferedImage image1;

    public Vector2D position2;
    public BufferedImage image2;

    public PlayerBullet() {
        this.position = new Vector2D();
        this.image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");

        this.position1 = new Vector2D();
        this.image1 = SpriteUtils.loadImage("assets/images/player-bullets/a/0.png");

        this.position2 = new Vector2D();
        this.image2 = SpriteUtils.loadImage("assets/images/player-bullets/a/2.png");
    }

    public void render(Graphics g) {
        g.drawImage(this.image, (int) this.position.x, (int) this.position.y, null);
        g.drawImage(this.image1, (int) this.position1.x, (int) this.position1.y, null);
        g.drawImage(this.image2, (int) this.position2.x, (int) this.position2.y, null);


    }

    public void run() {
        this.position.y -= 3;
        this.position1.x --;
        this.position1.y -= 3;
        this.position2.x ++;
        this.position2.y -= 3;

    }
}

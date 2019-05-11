import tklibs.Mathx;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    public Vector2D position;
    public BufferedImage image;
    public static ArrayList<PlayerBullet> playerBullets = new ArrayList<>();

    public Player() {
        this.position = new Vector2D();
        this.image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
    }

    public void render(Graphics g) {
        g.drawImage(this.image,
                (int) this.position.x,
                (int) this.position.y,
                null);
    }

    public void run() {
        this.shoot();
        // player run
        if (KeyEventPress.isUpPress) {
            this.position.y--;
        }
        if (KeyEventPress.isDownPress) {
            this.position.y++;
        }
        if (KeyEventPress.isRightPress) {
            this.position.x++;
        }
        if (KeyEventPress.isLeftPress) {
            this.position.x--;
        }

        //playerX[0,384 - 32]
        this.position.x = Mathx.clamp(this.position.x, 0, 384 - 28);
        //playerY[0,600 - 48]
        this.position.y = Mathx.clamp(this.position.y, 0, 600 - 40);

    }

    private void shoot() {
        if (KeyEventPress.isFirePress) {
            PlayerBullet newBullet = new PlayerBullet();
            Player.playerBullets.add(newBullet);
            newBullet.position.set(this.position);
        }
    }
}

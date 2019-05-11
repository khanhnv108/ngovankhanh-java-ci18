import tklibs.Mathx;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    public Vector2D position;
    public BufferedImage image;
    ArrayList<PlayerBullet> playerBullets;

    public Player() {
        this.position = new Vector2D();
        this.image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        playerBullets = new ArrayList<>();
    }

    public void render(Graphics g) {
        g.drawImage(this.image, (int) this.position.x, (int) this.position.y, null);
        for (int i = 0; i < playerBullets.size(); i++) {
            PlayerBullet bullet = playerBullets.get(i);
            bullet.render(g);
        }

    }

    int count = 0;

    public void run() {
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
        // gioi han di chuyen
        //playerX[0,384 - 32]
        this.position.x = Mathx.clamp(this.position.x, 0, 384 - 28);
        //playerY[0,600 - 48]
        this.position.y = Mathx.clamp(this.position.y, 0, 600 - 40);

        //ban dan
        count++;
        if (KeyEventPress.isFirePress && count > 20) {
            PlayerBullet bullet = new PlayerBullet();
            bullet.position.set(this.position.x, this.position.y);
            playerBullets.add(bullet);

            PlayerBullet bullet2 = new PlayerBullet();
            bullet2.position.set(this.position.x - 10, this.position.y);
            playerBullets.add(bullet2);

            PlayerBullet bullet3 = new PlayerBullet();
            bullet3.position.set(this.position.x + 10, this.position.y);
            playerBullets.add(bullet3);

            count = 0;
        }

        // playerBullet run()
        for (int i = 0; i < playerBullets.size(); i++) {
            PlayerBullet bullet = playerBullets.get(i);
            bullet.run();
        }

    }

//    private void shoot() {
//        if (KeyEventPress.isFirePress) {
//            PlayerBullet newBullet = new PlayerBullet();
//            Player.playerBullets.add(newBullet);
//            newBullet.position.set(this.position);
//        }
//    }


}

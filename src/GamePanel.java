import tklibs.Mathx;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    Player player;
    Background background;

    public GamePanel() {
        // player position
        this.player = new Player();
        this.player.position.set(200,500);
        // background position
        this.background = new Background();
        this.background.position.set(0,600-3109);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.background.render(g);
        this.player.render(g);
//        for (PlayerBullet bullet: Player.playerBullets) {
//            bullet.render(g);
//        }
    }

    public void gameLoop() {
        long lastTime = 0;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime > 1000 / 60) {
                // run logic
                this.runAll();

                // render
                this.repaint();
                lastTime = currentTime;
            }
        }
    }

    public void runAll() {
        // player run
        this.player.run();
        // background run
        this.background.run();
        // bullet run
//        for (PlayerBullet bullet: Player.playerBullets) {
//            bullet.run();
//        }
    }
}

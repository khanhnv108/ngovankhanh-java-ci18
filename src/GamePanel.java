import tklibs.Mathx;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    BufferedImage playerImage;
    Vector2D playerPosition;
//    double playerX;
//    double playerY;

    BufferedImage backgroundImage;
    Vector2D backgroundPosition;
//    int backgroundX;
//    int backgroundY;

    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
//        playerX = 200;
//        playerY = 500;
        playerPosition = new Vector2D(200, 500);
        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
//        backgroundX = 0;
//        backgroundY = 600 - 3109;
        backgroundPosition = new Vector2D(0, 600 - 3109);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, (int) backgroundPosition.x, (int) backgroundPosition.y, null);
        g.drawImage(playerImage, (int) playerPosition.x, (int) playerPosition.y, null);
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
        if (KeyEventPress.isUpPress) {
            playerPosition.y--;
        }
        if (KeyEventPress.isDownPress) {
            playerPosition.y++;
        }
        if (KeyEventPress.isRightPress) {
            playerPosition.x++;
        }
        if (KeyEventPress.isLeftPress) {
            playerPosition.x--;
        }

        //playerX[0,384 - 32]
        playerPosition.x = Mathx.clamp(playerPosition.x, 0, 384 - 28);
        //playerY[0,600 - 48]
        playerPosition.y = Mathx.clamp(playerPosition.y, 0, 600 - 40);

        // background run
        backgroundPosition.y += 10;
        if (backgroundPosition.y >= 0) {
            backgroundPosition.y = 0;
        }
    }
}

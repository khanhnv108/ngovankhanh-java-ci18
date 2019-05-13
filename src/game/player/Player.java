package game.player;

import game.GameObject;
import game.KeyEventPress;
import tklibs.Mathx;
import tklibs.SpriteUtils;

import java.awt.*;
import java.util.ArrayList;

public class
Player extends GameObject {

    public Player() {
        this.image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        position.set(200,500);
    }

    int count = 0; // dem so khung hinh

    @Override
    public void run() {
        this.move();
        this.limitPosition();
        this.fire();
        System.out.println(GameObject.objects.size());
    }

    private void fire() {
        count++;
        if (KeyEventPress.isFirePress && count > 20) {
//            PlayerBullet bullet = new PlayerBullet();
            PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
            bullet.position.set(this.position.x, this.position.y);

            PlayerBullet bullet2 = GameObject.recycle(PlayerBullet.class);
            bullet2.position.set(this.position.x - 10, this.position.y);

            PlayerBullet bullet3 = GameObject.recycle(PlayerBullet.class);
            bullet3.position.set(this.position.x + 10, this.position.y);

            count = 0;
        }
    }

    private void limitPosition() {
        //playerX[0,384 - 32]
        this.position.x = Mathx.clamp(this.position.x, 0, 384 - 28);
        //playerY[0,600 - 48]
        this.position.y = Mathx.clamp(this.position.y, 0, 600 - 40);
    }

    private void move() {
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
    }








//    private void shoot() {
//        if (game.KeyEventPress.isFirePress) {
//            game.player.PlayerBullet newBullet = new game.player.PlayerBullet();
//            game.player.Player.playerBullets.add(newBullet);
//            newBullet.position.set(this.position);
//        }
//    }


}

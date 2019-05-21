package game.player;

import game.GameObject;
import game.item.Item;
import game.KeyEventPress;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.Mathx;

import java.awt.*;

public class
Player extends GameObject {
    public int hp;
    public boolean immune;

    public Player() {
//        this.image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        renderer = new Renderer("assets/images/players/straight");
        position.set(200,500);
        hitBox = new BoxCollider(this, 32, 48);
        hp = 3;
        immune = false;
    }

    int renderCount = 0;
    @Override
    public void render(Graphics g) {
        if (immune) {
            renderCount++;
            if (renderCount % 3 == 0) {
                super.render(g);
            }
        } else {
            super.render(g);
        }
    }

    int count = 0; // dem so khung hinh

    @Override
    public void run() {
        this.move();
        this.limitPosition();
        this.fire();
        this.checkImmune();
        this.checkItem();
    }

    //todo
    private void checkItem() {
        Item item = GameObject.findIntersects(Item.class, hitBox);

        if (item != null) {
            this.hp = 5;
            item.deactive();
        }
    }

    int immuneCount = 0;
    private void checkImmune() {
        if(immune) {
            immuneCount++;
            if (immuneCount > 120) {
                immune = false;
            }
        }else {
            immuneCount = 0;
        }
    }

    public void takeDamage(int damage) {
        if (!immune) {
            hp -= damage;
            if (hp <= 0) {
                hp = 0;
                this.deactive();
            } else {
                // roi vao trang thai bat tu
                immune = true;
            }
        }
    }

    private void fire() {
        count++;
        if (KeyEventPress.isFirePress && count > 20) {
//            PlayerBullet bullet = new PlayerBullet();
            PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
            bullet.position.set(this.position.x, this.position.y);
            bullet.velocity.setAngle(Math.toRadians(-90));

            PlayerBullet bullet2 = GameObject.recycle(PlayerBullet.class);
            bullet2.position.set(this.position.x - 10, this.position.y);
            bullet2.velocity.setAngle(Math.toRadians(-95));


            PlayerBullet bullet3 = GameObject.recycle(PlayerBullet.class);
            bullet3.position.set(this.position.x + 10, this.position.y);
            bullet3.velocity.setAngle(Math.toRadians(-85));

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

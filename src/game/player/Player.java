package game.player;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.Vector2D;
import game.explosion.Explosion;
import game.physics.BoxCollider;
import game.player.item.Item;

import game.renderer.Renderer;
import tklibs.Mathx;

import java.awt.*;
import java.util.Set;


public class Player extends GameObject {
    public int hp;
    public boolean immune;
    public int numberBullet;
    public Sphere sphereLeft;
    public Sphere sphereRight;

    public Player() {
//        this.image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        renderer = new Renderer("assets/images/players/straight");
        position.set(200, 500);
        hitBox = new BoxCollider(this, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        hp = 1200;
        immune = false;
        numberBullet = 3;
        sphereLeft = GameObject.recycle(Sphere.class);
        sphereRight = GameObject.recycle(Sphere.class);
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
        super.run();
        this.move();
        this.limitPosition();
        this.fire();
        this.checkImmune();
        this.checkItem();
        this.setSpherePosition();
        // todo demo event
        this.sayHello();
    }

    private void sayHello() {
        // fire
//        if (Settings.mouseClicked) {
//            System.out.println("Hello");
//            Settings.mouseClicked = false;
//        }
        // move
        Vector2D toMouse = Settings.mousePosition.clone();
        toMouse.substract(this.position.x, this.position.y);
        if (toMouse.getLength() < 3) {
            this.velocity.set(0, 0);
        } else {
//            this.velocity.set(0, 0.5);
//            this.velocity.setAngle(toMouse.getAngle());
            this.velocity.set(toMouse.x,toMouse.y);
            this.velocity.setLength(3);
        }
    }

    private void setSpherePosition() {
        sphereLeft.position.set(position.x - 25, position.y +10);
        sphereRight.position.set(position.x + 25, position.y +10);
    }

    //todo
    private void checkItem() {
        Item item = GameObject.findIntersects(Item.class, hitBox);
        if (item != null) {
            item.deactive();
            item.powerUp(this);
        }
    }

    int immuneCount = 0;

    private void checkImmune() {
        if (immune) {
            immuneCount++;
            if (immuneCount > 120) {
                immune = false;
            }
        } else {
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

//            PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
//            bullet.position.set(this.position.x, this.position.y);
//            bullet.velocity.setAngle(Math.toRadians(-90));
//
//            PlayerBullet bullet2 = GameObject.recycle(PlayerBullet.class);
//            bullet2.position.set(this.position.x - 10, this.position.y);
//            bullet2.velocity.setAngle(Math.toRadians(-95));
//
//
//            PlayerBullet bullet3 = GameObject.recycle(PlayerBullet.class);
//            bullet3.position.set(this.position.x + 10, this.position.y);
//            bullet3.velocity.setAngle(Math.toRadians(-85));
            double fromX = this.position.x - 10;
            double toX = this.position.x + 10;
            double stepX = (toX - fromX) / (numberBullet - 1);
            double fromAngle = -135; //-95
            double toAngle = -45;    // -85
            double stepAngle = (toAngle - fromAngle) / (numberBullet - 1);
            for (int i = 0; i < numberBullet; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                //setPosition
                //setVelocity
                bullet.position.set(fromX + (stepX *i),this.position.y);
                bullet.velocity.setAngle(Math.toRadians(fromAngle + (stepAngle * i)));
            }

            count = 0;
        }
    }

    private void limitPosition() {
        //playerX[0,384 - 32]
        this.position.x = Mathx.clamp(this.position.x, 0, Settings.BACKGROUND_WIDTH - 28);
        //playerY[0,600 - 48]
        this.position.y = Mathx.clamp(this.position.y, 0, Settings.GAME_HEIGHT - 40);
    }

    private void move() {
        if (KeyEventPress.isUpPress) {
            this.position.y-= 3;
        }
        if (KeyEventPress.isDownPress) {
            this.position.y+= 3;
        }
        if (KeyEventPress.isRightPress) {
            this.position.x+= 3;
        }
        if (KeyEventPress.isLeftPress) {
            this.position.x-= 3;
        }
    }

    @Override
    public void deactive() {
        super.deactive();
        Explosion explosion = GameObject.recycle(Explosion.class);
        explosion.position.set(this.position.x, this.position.y);

        sphereRight.deactive();
        sphereLeft.deactive();
    }

    //    private void shoot() {
//        if (game.KeyEventPress.isFirePress) {
//            game.player.PlayerBullet newBullet = new game.player.PlayerBullet();
//            game.player.Player.playerBullets.add(newBullet);
//            newBullet.position.set(this.position);
//        }
//    }


}

package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import tklibs.SpriteUtils;

public class Enemy extends GameObject {
    public int hp;

    public Enemy() {
        image = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
        position.set(0, -50);
        velocity.set(0, 2);
        velocity.setAngle(Math.toRadians(25));
        hitBox = new BoxCollider(this,28,28);
        hp = 5;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }

    @Override
    public void run() {
        super.run(); // velocity

        //Todo:continue
        this.limitPosition();
        this.fire();
    }

    int count = 0;
    private void fire() {
        count++;
        if (count > 20) {
            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            bullet.position.set(this.position.x, this.position.y);
            bullet.velocity.setAngle(Math.toRadians(-90));

            count = 0;
        }
    }

    private void limitPosition() {
        if (this.onGoingRight() && this.outOfBoundRight()) {
            this.reverseVelocityX();
        }
        if (this.onGoingLeft() && this.outOfBoundLeft()) {
            this.reverseVelocityX();
        }
        this.deactiveIfNeeded();
    }

    @Override
    public void reset() {
        super.reset(); // active = true
        position.set(0, -50);
        velocity.set(0, 1.5);
        velocity.setAngle(Math.toRadians(25));
        hp = 5;
    }

    private void deactiveIfNeeded() {
        if (position.y > 600) {
            this.deactive();
        }
    }

    private boolean outOfBoundLeft() {
        return position.x < 0;
    }

    private boolean onGoingLeft() {
        return position.x < 0;
    }

    private void reverseVelocityX() {
        velocity.x = -velocity.x;
    }

    private boolean outOfBoundRight() {
        return position.x > 384-30;
    }

    private boolean onGoingRight() {
        return velocity.x > 0;
    }
}

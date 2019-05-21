package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;


public class Enemy extends GameObject {
    public int hp;
    public int damage;

    public Enemy() {
//        image = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
//        renderer = new Renderer("assets/images/enemies/level0/pink/0.png");
        renderer = new Renderer("assets/images/enemies/level0/pink");
        position.set(0, -50);
        velocity.set(0, 3);
        velocity.setAngle(Math.toRadians(25));
        hitBox = new BoxCollider(this,28,28);
        hp = 5;
        damage = 1;

    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }

    int count = 0;
    @Override
    public void run() {
        super.run(); // velocity
        this.fire();
        this.checkPlayer();

        //Todo:continue
        this.move();
    }

    private void fire() {
        count++;
        if (count > 20) {
            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            bullet.position.set(position.x, position.y);
            bullet.velocity.setAngle(Math.toRadians(90));
            count = 0;
        }
    }

    private void checkPlayer() {
        Player player = GameObject.findIntersects(Player.class, this.hitBox);
        if (player != null) {
            player.takeDamage(damage);
            this.deactive();
        }
    }


    private void move() {
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
        velocity.set(0, 3);
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

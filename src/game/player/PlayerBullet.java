package game.player;

import game.GameObject;
import game.enemy.Enemy;
import game.physics.BoxCollider;
import game.renderer.Renderer;



public class    PlayerBullet extends GameObject {
    public int damage;

    public PlayerBullet() {
//        this.image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        renderer = new Renderer("assets/images/player-bullets/a/1.png");
//        velocity = new Vector2D(0, -3);
        velocity.set(0, 5);
        hitBox = new BoxCollider(this, 24,24);
        damage = 1;

    }

    @Override
    public void run() {
        super.run(); // position.add(velocity.x, velocity.y)
        this.deactiveIfNeeded();
        this.checkEnemy();
    }

    private void checkEnemy() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, hitBox);

        if (enemy != null) {
//            enemy.deactive();
            enemy.takeDamage(damage);
            this.deactive();
        }
    }

    private void deactiveIfNeeded() {
        if (position.y < -30) {
            this.deactive();
        }
    }
}

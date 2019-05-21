package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import game.renderer.Renderer;

public class EnemyBullet extends GameObject {
    public int damage;

    public EnemyBullet() {
        renderer = new Renderer("assets/images/enemies/bullets/pink.png");
        velocity.set(0, 3);
        hitBox = new BoxCollider(this,16,16);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        this.deactiveIfNeeded();
        this.checkPlayer();
    }

        // todo:continue
    private void checkPlayer() {

    }

    private void deactiveIfNeeded() {
        if (position.y > 600) {
            this.deactive();
        }
    }
}

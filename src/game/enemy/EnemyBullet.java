package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {
    public int damage;

    public EnemyBullet() {
        this.image = SpriteUtils.loadImage("assets/images/enemies/bullets/pink.png");
        velocity.set(0,3);
        hitBox = new BoxCollider(this,16,16);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
//        this.deactiveIfNeeded();
//        this.checkPlayer();
    }
}

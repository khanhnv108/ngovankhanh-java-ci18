package game.item;

import game.GameObject;
import game.physics.BoxCollider;
import game.renderer.Renderer;

public class Item extends GameObject {
    public int hpItem;
    public Item() {
        renderer = new Renderer("assets/images/items/power-up-blue.png");
        velocity.set(0,3);
        hitBox = new BoxCollider(this ,12,12);
        position.set(200,-20);
        velocity.set(0,3);
        velocity.setAngle(Math.toRadians(90));
        hpItem = 10;
    }

    @Override
    public void run() {
        super.run();
        this.deactiveIfNeeded();

    }

    private void deactiveIfNeeded() {
        if (position.y > 612) {
            this.deactive();
        }
    }
}

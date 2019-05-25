package game.player.item;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.player.Player;

public class Item extends GameObject {
    public Item() {
        hitBox = new BoxCollider(this,12, 12);
    }

    public void powerUp(Player player) {

    }

    @Override
    public void run() {
        super.run();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if (this.position.y > Settings.GAME_HEIGHT) {
            this.deactive();
        }
    }
}

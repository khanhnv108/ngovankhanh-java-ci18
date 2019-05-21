package game.item;

import game.GameObject;

public class ItemSummoner extends GameObject {
    public ItemSummoner() {}

    int count = 0;

    @Override
    public void run() {
        count++;
        if (count > 360) {
            Item item = GameObject.recycle(Item.class);
            count = 0;
        }
    }
}

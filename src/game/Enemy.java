package game;

import tklibs.Mathx;
import tklibs.SpriteUtils;

public class Enemy extends GameObject {

    public  Enemy() {
        this.image = SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png");
        position.set(0,0);
    }

    @Override
    public void run() {
        this.position.x++;
        this.position.x = Mathx.clamp(this.position.x, 0, 384 - 34);

    }
}

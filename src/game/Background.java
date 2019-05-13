package game;

import tklibs.SpriteUtils;

public class Background extends GameObject {

    public Background() {
        this.image = SpriteUtils.loadImage("assets/images/background/0.png");
        position.set(0, 600-3109);
    }

    @Override
    public void run() {
        this.position.y ++;
        if (this.position.y >= 0) {
            this.position.y = 0;
        }
    }

}

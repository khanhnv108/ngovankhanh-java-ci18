package game;

import game.renderer.Renderer;


public class Background extends GameObject {

    public Background() {
//        this.image = SpriteUtils.loadImage("assets/images/background/0.png");
        renderer = new Renderer("assets/images/background/0.png");
        position.set(0, Settings.GAME_HEIGHT-Settings.BACKGROUND_HEIGHT);
        anchor.set(0,0);
    }

    @Override
    public void run() {
        this.position.y ++;
        if (this.position.y >= 0) {
            this.position.y = 0;
        }
    }

}

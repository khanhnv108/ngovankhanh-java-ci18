package game;

import game.player.Player;
import game.player.PlayerBullet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


// class Class
// class Type
// up casting

public class GameObject {  //player, Background, PlayerBullet..
    // quan ly doi tuong( static)
    public static ArrayList<GameObject> objects = new ArrayList<>();

    public static <E extends GameObject> E recycle(Class<E> cls) {
        // 1. tim phan tu bi deactive >> reset phan tu >> tra ve
        // 2. neu khong tim thay phan tu bi deactive >> tao moi >> tra ve
        E object = findInactive(cls);
        if (object != null) {
            object.reset();
            return object;
        }
        try {
            object = cls.getConstructor().newInstance();
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static <E extends GameObject> E findInactive(Class<E> cls) {
        // cls ~ Player.class() || Background.clas()
        // E ~ Player || Background..
//        cls.getField();
//        cls.getConstructor();
//        cls.getMethod();
        // TODO
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            // object ~ cls
            // object.active == false
            if (cls.isAssignableFrom(object.getClass()) && !object.active) {
                return (E) object;
            }
        }
        return null;
    }

    // dinh nghia doi tuong
    public BufferedImage image; // = null
    public Vector2D position;
    public Boolean active;

    public GameObject() {
        objects.add(this);
//        System.out.println(objects.size());
        position = new Vector2D(); // (0, 0)
        active = true;
    }

    public void render(Graphics g) {
        if (image != null) {
            g.drawImage(image, (int) this.position.x, (int) this.position.y, null);
        }
    }

    public void run() {
    }

    public void deactive() {
        active = false;
    }

    public void reset() {
        active = true;
    }
}

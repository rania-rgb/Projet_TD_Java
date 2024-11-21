
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.*;

public class Projectile extends Sprite  {//
    // La classe Projectile hérite de Sprite toutes ses fonctionnalités, en ajoutant d'autres qui sont
    // la flèche tirée par le héros dans le jeu.
    //Elle représente les objets mobiles et éphémères. Elle vient après le SolidSprite dans l'héritage.
    private double speedX = 0.0; // Les attributs speedX et speedY : la vitesse du projectile sur les axes X et Y.
    private double speedY = 0.0;
    private boolean active = true; // Permets de savoir si le projectile est active dans le jeu

    public Projectile(double x, double y) {
        super(x, y, null, 5.0, 10.0); // and no image
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
            g.fillOval((int) x, (int) y, (int) width, (int) height);
            // Add a fire trail effect
            g.setColor(Color.ORANGE);
            g.fillOval((int) x - 5, (int) y + 2, (int) width - 2, (int) height - 2);

            g.setColor(Color.YELLOW);
            g.fillOval((int) x - 10, (int) y + 4, (int) width - 4, (int) height - 4);
        }



    public void update() {
        x += speedX;
        y += speedY;

    }
    public void setSpeed(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    // Set the X position of the projectile
    public void setX(double x) {
        this.x = x;
    }

    // Set the Y position of the projectile
    public void setY(double y) {
        this.y = y;
    }


    // Check if the projectile is active
    public boolean isActive() {
        return active; // Return the active state of the projectile
    }
    public Rectangle2D.Double getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}

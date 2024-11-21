import java.awt.*;
import java.awt.geom.Rectangle2D;
public class Sprite implements Displayable{ //La classe Sprite implémente Displayable, ce qui permet au sprite d'être affiché à l'écran.
    //Cette  classe permet de désigner notre personnage(héro) fournissant ses caractéristiques.
    //La classe Sprite est la base commune de l'hiérarchie de classes.
    protected double x; //Position actuelle du sprite
    protected double y; //Position actuelle du sprite
    protected final Image image;
    protected final double width;//largeur du sprite
    protected final double height; //hauteur du sprite

    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,(int)x,(int)y,null);
    }
    // Méthode getHitBox();
    public Rectangle2D.Double getHitBox() {
        //Détection de collision, gestion de limites. L'environnement qui ne doit pas dépasser est codé sur playground
        //Dans notre jeu, cette méthode retourne le hitbox du héros, qui est utilisée pour vérifier les collisions avec des obstacles comme les arbres ou la roche,
        // empêchant ainsi le héros de les traverser.
        return new Rectangle2D.Double(x, y, width, height);
    }
}

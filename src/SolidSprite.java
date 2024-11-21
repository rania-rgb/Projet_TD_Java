import java.awt.*;

//SolidSprite hérite de Sprite, juste après Sprite dans l'hiérarchie. Elle répresente les objet statiques
public class SolidSprite extends Sprite{
    // Appelle le constructeur de Sprite avec super()
    public SolidSprite(double x, double y, Image image, double width, double height) { // Initialise les propriétés via le constructeur de Sprite
        super(x, y, image, width, height);
    }
}
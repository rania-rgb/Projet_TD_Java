import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends Sprite {

    // DynamicSprite hérite de Sprite, et vient après le projectie dans l'héritage.  Représente un sprite dynamique comme le héros, capable de se déplacer,
    // tirer des projectiles  et interagir avec l'environnement.
    private Direction direction = Direction.EAST;
    private double speed = 5;
    private double timeBetweenFrame = 250; // Temps entre deux frames d'animation (en ms)
    private boolean isWalking = true; // Indique si le sprite est en mouvement
    private final int spriteSheetNumberOfColumn = 10; // Nombre de colonnes dans la spritesheet (animation)
    private Projectile arrow;
    private boolean isArrowFollowingHero = true; // Indique si la flèche suit le héros (avant tir)

    // Constructeur : initialise la position, l'image, les dimensions du sprite et la flèche associée
    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
        this.arrow = new Projectile(x, y); // Initialise la flèche
    }

    // Vérifie si le déplacement est possible en fonction de l'environnement (évite les collisions)
    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double moved = new Rectangle2D.Double();

        // Calcule la future position en fonction de la direction
        switch (direction) {
            case EAST -> moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case WEST -> moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case NORTH -> moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case SOUTH -> moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
        }

        // Vérifie les collisions avec les objets statiques (SolidSprite)
        for (Sprite s : environment) {
            if (s instanceof SolidSprite solid) {
                if (solid.getHitBox().intersects(moved)) {
                    return false; // Collision détectée
                }
            }
        }

        return true; // Aucun obstacle
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public void move() {
        if (isWalking) {
            switch (direction) {
                case EAST -> this.x += speed;
                case WEST -> this.x -= speed;
                case NORTH -> this.y -= speed;
                case SOUTH -> this.y += speed;
            }
        }
        if (isArrowFollowingHero && arrow != null) {
            updateArrowPosition(); // Maintient la flèche attachée au héros
        }
    }

    // Met à jour la position de la flèche pour qu'elle suive le héros
    private void updateArrowPosition() {
        double arrowOffsetX = (this.width / 2) - 25; // Décalage horizontal pour centrer
        double arrowOffsetY = (this.height / 2) - 25; // Décalage vertical pour centrer
        arrow.setX(this.x + arrowOffsetX);
        arrow.setY(this.y + arrowOffsetY);
    }


    public void stopWalking() {
        isWalking = false;
    }


    public void startWalking() {
        isWalking = true;
    }

    // Déplace le sprite si aucun obstacle n'est détecté
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }

    // Tire la flèche dans la direction actuelle
    public void shoot() {
        if (isArrowFollowingHero) {
            double arrowSpeedX = 0, arrowSpeedY = 0;

            // Définit la vitesse de la flèche en fonction de la direction
            switch (direction) {
                case EAST -> arrowSpeedX = 10;
                case WEST -> arrowSpeedX = -10;
                case NORTH -> arrowSpeedY = -10;
                case SOUTH -> arrowSpeedY = 10;
            }

            arrow.setSpeed(arrowSpeedX, arrowSpeedY); // Définit la vitesse
            isArrowFollowingHero = false; // Détache la flèche du héros
        }
    }

    // Met à jour la position et l'état de la flèche
    public void updateArrow() {
        arrow.update();

        // Si la flèche n'est plus active, elle revient au héros
        if (!arrow.isActive()) {
            isArrowFollowingHero = true;
            arrow.setSpeed(0, 0);
            resetArrow();
        }
    }

    // Réinitialise la flèche pour qu'elle suive à nouveau le héros
    public void resetArrow() {
        isArrowFollowingHero = true;
        arrow.setSpeed(0, 0);
        arrow.setX(x + (width / 2) - 5);
        arrow.setY(y + (height / 2) - 5);
    }

    @Override
    public void draw(Graphics g) {
        // Calcul de l'index pour l'animation
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);


        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height),
                null);

        arrow.draw(g);
    }
}










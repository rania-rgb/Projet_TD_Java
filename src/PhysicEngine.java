import java.lang.reflect.*;
import java.util.ArrayList;

// PhysicEngine : Cette classe représente le moteur physique du jeu.
// Elle implémente l'interface Engine, ce qui impose la méthode update().

public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList; // Liste des sprites dynamiques à déplacer.
    private ArrayList<Sprite> environment; // Liste des éléments statiques ou obstacles de l'environnement.

    // Constructeur : Initialise les listes des sprites dynamiques et de l'environnement.
    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    // Définit l'environnement du jeu (obstacles, limites, etc.) à partir d'une liste de sprites.
    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    // Ajoute un sprite dynamique à la liste des sprites gérés par le moteur physique,
    // si celui-ci n'est pas déjà présent.
    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite)) {
            movingSpriteList.add(sprite);
        }
    }

    @Override
    public void update() {
        // Met à jour les positions des sprites dynamiques en vérifiant s'ils peuvent se déplacer dans l'environnement sans collision.
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment);
        }
    }
}
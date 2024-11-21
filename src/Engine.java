public interface Engine {
    void update();  // Method signature
}
// On peut pas instancier une interface. On ne peut pas créer un objet à partir de l'interface engine, c'est un modèle que le jeu suit en fait.
//Par contre on peut implémenter l'interface dans une classe si on a une implémentation concrète, qui est ici faite par la methode update, donc
//Une mise à jour d'un état du jeu
//Oui, une classe peut implémenter plusieurs interfaces. C'est le principe de l'héritage.

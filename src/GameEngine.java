
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Toolkit;

    public class GameEngine implements Engine, KeyListener {
        // implements Engine : Permet à la classe de se conformer à l'interface Engine, qui comprend la méthode update.
        // implements KeyListener : Permet à la classe de gérer les événements clavier ( détection des touches
        // pressées ou relâchées) en implémentant les méthodes keyPressed, keyReleased, et keyTyped.

        // Différence avec extends :
// - implements est utilisé pour adopter une ou plusieurs interfaces (comme un contrat) sans hériter des attributs ou méthodes d'une classe.
// - extends est utilisé pour hériter des attributs et méthodes d'une classe parent (héritage), et on ne peut hériter que d'une seule classe.
        DynamicSprite hero;

        public GameEngine(DynamicSprite hero) {
            this.hero = hero; //Le co

        }


        @Override
        public void update() {
            hero.updateArrow(); //Mise à jour de la flèche si elle est déjà tirée

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) { //Quand une touche est pressée
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP :
                    hero.setDirection(Direction.NORTH);
                    hero.startWalking();
                    break;
                case KeyEvent.VK_DOWN:
                    hero.setDirection(Direction.SOUTH);
                    hero.startWalking();
                    break;
                case KeyEvent.VK_LEFT:
                    hero.setDirection(Direction.WEST);
                    hero.startWalking();
                    break;
                case KeyEvent.VK_RIGHT:
                    hero.setDirection(Direction.EAST);
                    hero.startWalking();
                    break;
                case KeyEvent.VK_SPACE: // Shoot la flèche
                    hero.stopWalking();
                    hero.shoot();
                    break;
                case KeyEvent.VK_R: // Reset la flèche manuellement en touchant sur r
                    hero.resetArrow();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Quand on relâche la touche, l'héro s'arrête
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> {
                    hero.stopWalking();
                }
            }
        }


    }


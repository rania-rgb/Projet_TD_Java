public enum Direction {

    NORTH(2),
    SOUTH(0),
    EAST(3),
    WEST(1);


    private final int frameLineNumber;
    // retourne l'index de la ligne dans la spritesheet correspondant à la direction actuelle .
    // Cela permet de sélectionner la bonne animation pour la direction du sprite.

    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }
    public int getFrameLineNumber() {
        return frameLineNumber;
    }


    @Override
    public String toString() {
        return name() + " (Frame Line: " + frameLineNumber + ")";
    }
}

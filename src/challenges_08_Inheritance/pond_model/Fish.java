package challenges_08_Inheritance.pond_model;

import java.util.List;

public class Fish extends Weed {
    public Fish(int x, int y) {
        super(x, y);
    }

    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    /**
     * Move one step to the right if the target cell is free (no Stone or Weed).
     * This is a minimal deterministic move that satisfies the assignment.
     */
    public void moveRightIfFree(List<Stone> obstacles, int width, int height) {
        int newX = getX() + 1;
        int newY = getY();

        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            // out of bounds -> do not move
            System.out.println("Fish cannot move out of bounds to (" + newX + "," + newY + ")");
            return;
        }

        for (Stone s : obstacles) {
            if (s.getX() == newX && s.getY() == newY) {
                System.out.println("Fish cannot move to (" + newX + "," + newY + ") - occupied");
                return;
            }
        }

        setPosition(newX, newY);
        System.out.println("Fish moved to (" + newX + "," + newY + ")");
    }
}

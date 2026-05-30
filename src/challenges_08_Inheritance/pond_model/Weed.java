package challenges_08_Inheritance.pond_model;

public class Weed extends Stone {
    protected boolean alive = true;

    public Weed(int x, int y) {
        super(x, y);
    }

    public void grow() {
        if (!alive) return;
        alive = false;
    }

    public boolean isAlive() { return alive; }
}

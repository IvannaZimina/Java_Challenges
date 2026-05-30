/*
Create a pond model with stones, weeds and fishes. A Stone only has coordinates (x , y) and stay at its place indefinitely. A Weed extends Stone and can also grow and die. A Fish extends Weed and can also move. Create a couple of objects of each class and a loop for the lifecycle of the living objects. Make sure a Fish does not move to a place where there is already a weed or a stone.
You may want to use an instanceof operator for completing the task:
https://www.w3schools.com/java/ref_keyword_instanceof.asp
Those, who want an optional extra challenge, may create a 2-dimensional array and simulate a real pond ecosystem with growing weed and fish, which is eating weed. May even add big fishes, which eat small fishes.
https://saask.ee/model/
*/

package challenges_08_Inheritance.pond_model;

public class PondModel {
	public static void main(String[] args) throws InterruptedException {
		final int WIDTH = 6;
		final int HEIGHT = 6;

		java.util.List<Stone> stones = new java.util.ArrayList<>();
		java.util.List<Weed> weeds = new java.util.ArrayList<>();
		java.util.List<Fish> fishes = new java.util.ArrayList<>();

		// place some stones
		stones.add(new Stone(1, 1));
		stones.add(new Stone(2, 4));

		// place some weeds
		weeds.add(new Weed(0, 2));
		weeds.add(new Weed(4, 4));

		// place some fishes
		fishes.add(new Fish(3, 2));
		fishes.add(new Fish(2, 2));

		// simulate lifecycle for a number of steps (deterministic, simple rules)
		for (int step = 1; step <= 6; step++) {
			System.out.println("--- Step " + step + " ---");
			// weeds grow or may die deterministically
			java.util.Iterator<Weed> wit = weeds.iterator();
			while (wit.hasNext()) {
				Weed w = wit.next();
				w.grow();
				if (!w.isAlive()) {
					System.out.println("Weed at (" + w.getX() + "," + w.getY() + ") died");
					wit.remove();
				} else {
					System.out.println("Weed at (" + w.getX() + "," + w.getY() + ") grew");
				}
			}

			// fishes act: minimal deterministic move — try move right if free
			// prepare a combined obstacle list (stones + weeds)
			java.util.List<Stone> obstacles = new java.util.ArrayList<>();
			obstacles.addAll(stones);
			obstacles.addAll(weeds);
			for (Fish f : fishes) {
				f.moveRightIfFree(obstacles, WIDTH, HEIGHT);
			}
		}
	}    
}


/*
Advance the pond model, which we had in the task 2 of the challenge 8, using a 2D array.
Make the pond a 2D array, which is filled with various objects.
The array cell can be either a null, or a Stone, or a Weed, or a Fish.
A Stone does not do anything.
A Weed can grow, die and occasionally produce another Weed in a nearby cell, if there is any empty cell around.
A Fish can do all that a Weed can do, and move and eat Weed, if there is one in a nearby cell.
If a Fish does not eat for a certain number of cycles, it dies from starvation.
If there is a Weed near the Fish, the Fish eats it.
If there is no Weed around, the Fish makes a random possible move.
A Fish can not go beyond pond boundaries. You may lay pond boundaries with Stones, if you prefer.
If an object dies, the pond cell contents becomes null. Print pond contents each cycle, so you can see the life dynamics there.
Ideally, the pond should become a stable ecosystem, which exists indefinitely.
*/

package challenges_13_2D_arrays;

public class PondModel {
	// Pond grid: 2D array of PondObject (can be null, Stone, Weed, Fish)
	private PondObject[][] pond;
	private int rows;
	private int cols;

	// Random helper
	private java.util.Random rnd = new java.util.Random();

	// Create a pond of given size and randomly populate it with objects
	public PondModel(int rows, int cols, double stoneProb, double weedProb, double fishProb) {
		this.rows = rows;
		this.cols = cols;
		pond = new PondObject[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				double p = rnd.nextDouble();
				if (p < stoneProb) pond[r][c] = new Stone();
				else if (p < stoneProb + weedProb) pond[r][c] = new Weed();
				else if (p < stoneProb + weedProb + fishProb) pond[r][c] = new Fish();
				else pond[r][c] = null; // empty cell
			}
		}
	}

	// Advance the pond by one cycle: each object performs its action once.
	public void advanceOneCycle() {
		// Reset acted flags so each object acts at most once per cycle
		for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++) {
			if (pond[r][c] != null) pond[r][c].setActed(false);
		}

		// Iterate and let objects act. We iterate row-major and let objects move by
		// updating the pond array directly. acted flag prevents double actions.
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				PondObject obj = pond[r][c];
				if (obj != null && !obj.hasActed()) {
					obj.perform(this, r, c);
				}
			}
		}
	}

	// Utility: get neighbors coordinates (8-directional)
	public java.util.List<int[]> neighbors(int r, int c) {
		java.util.List<int[]> list = new java.util.ArrayList<>();
		for (int dr = -1; dr <= 1; dr++) {
			for (int dc = -1; dc <= 1; dc++) {
				if (dr == 0 && dc == 0) continue;
				int nr = r + dr, nc = c + dc;
				if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) list.add(new int[] {nr, nc});
			}
		}
		return list;
	}

	// Accessors used by PondObject implementations
	public PondObject get(int r, int c) { return pond[r][c]; }
	public void set(int r, int c, PondObject obj) { pond[r][c] = obj; }
	public int getRows() { return rows; }
	public int getCols() { return cols; }

	// Print pond contents to console. Symbols: S=Stone, W=Weed, F=Fish, .=empty
	public void printPond() {
		for (int r = 0; r < rows; r++) {
			StringBuilder sb = new StringBuilder();
			for (int c = 0; c < cols; c++) {
				PondObject p = pond[r][c];
				if (p == null) sb.append('.');
				else sb.append(p.symbol());
			}
			System.out.println(sb.toString());
		}
	}

	// Demo: run a simple simulation for N cycles
	public static void main(String[] args) throws InterruptedException {
		// Create a pond 12x30 with some probabilities (from task: random initial fill)
		PondModel model = new PondModel(12, 30, 0.05, 0.15, 0.08);
		for (int cycle = 1; cycle <= 100; cycle++) {
			System.out.println("Cycle " + cycle);
			model.printPond();
			model.advanceOneCycle();
			Thread.sleep(180); // small pause so output is readable
			System.out.println();
		}
	}
}

// Base class for pond objects. Each object has an acted flag to avoid multiple actions per cycle.
abstract class PondObject {
	private boolean acted = false;

	// Called by simulation to perform object's action at position (r,c).
	public abstract void perform(PondModel pond, int r, int c);

	// Single-character symbol for printing
	public abstract char symbol();

	public boolean hasActed() { return acted; }
	public void setActed(boolean v) { acted = v; }
}

// Stone: does nothing. Immutable rock. (from task: Stone does not do anything)
class Stone extends PondObject {
	@Override
	public void perform(PondModel pond, int r, int c) {
		// Stone never acts but mark as acted so it is not reprocessed this cycle
		setActed(true);
	}

	@Override
	public char symbol() { return 'S'; }
}

// Weed: can grow, die, and occasionally produce another Weed in a nearby empty cell.
class Weed extends PondObject {
	private int age = 0; // simple age counter
	private java.util.Random rnd = new java.util.Random();

	@Override
	public void perform(PondModel pond, int r, int c) {
		if (hasActed()) return;
		setActed(true);
		age++;
		// Small chance to die each cycle
		if (rnd.nextDouble() < 0.03 || age > 40) { // die by chance or old age
			pond.set(r, c, null);
			return;
		}
		// Small chance to spread into a random empty neighbor
		if (rnd.nextDouble() < 0.15) {
			java.util.List<int[]> nbs = pond.neighbors(r, c);
			java.util.Collections.shuffle(nbs, rnd);
			for (int[] p : nbs) {
				if (pond.get(p[0], p[1]) == null) {
					pond.set(p[0], p[1], new Weed());
					break;
				}
			}
		}
	}

	@Override
	public char symbol() { return 'W'; }
}

// Fish: can move, eat Weed, and starve if it doesn't eat for several cycles.
class Fish extends PondObject {
	private int hunger = 0; // cycles since last meal
	private static final int STARVE_LIMIT = 6; // if hunger exceeds, fish dies
	private java.util.Random rnd = new java.util.Random();

	@Override
	public void perform(PondModel pond, int r, int c) {
		if (hasActed()) return;
		// First look for neighbouring Weed to eat
		java.util.List<int[]> nbs = pond.neighbors(r, c);
		// shuffle neighbors to randomize choices
		java.util.Collections.shuffle(nbs, rnd);
		for (int[] p : nbs) {
			PondObject obj = pond.get(p[0], p[1]);
			if (obj instanceof Weed) {
				// Move to weed cell and eat it
				pond.set(p[0], p[1], this);
				pond.set(r, c, null);
				setActed(true);
				hunger = 0; // ate
				return;
			}
		}
		// No weed found: try to move to a random empty neighbor
		java.util.List<int[]> empties = new java.util.ArrayList<>();
		for (int[] p : nbs) if (pond.get(p[0], p[1]) == null) empties.add(p);
		if (!empties.isEmpty()) {
			int[] dest = empties.get(rnd.nextInt(empties.size()));
			pond.set(dest[0], dest[1], this);
			pond.set(r, c, null);
			setActed(true);
			hunger++;
		} else {
			// cannot move and didn't eat
			setActed(true);
			hunger++;
		}
		// Starvation check
		if (hunger > STARVE_LIMIT) {
			// fish dies
			// find current location of this fish to remove it. It may have moved.
			// scan neighbors and self
			boolean removed = false;
			for (int rr = Math.max(0, r-1); rr <= Math.min(pond.getRows()-1, r+1); rr++) {
				for (int cc = Math.max(0, c-1); cc <= Math.min(pond.getCols()-1, c+1); cc++) {
					if (pond.get(rr, cc) == this) {
						pond.set(rr, cc, null);
						removed = true;
						break;
					}
				}
				if (removed) break;
			}
			if (!removed) {
				// fallback: try whole pond (rare)
				for (int rr = 0; rr < pond.getRows(); rr++) {
					for (int cc = 0; cc < pond.getCols(); cc++) {
						if (pond.get(rr, cc) == this) pond.set(rr, cc, null);
					}
				}
			}
		}
	}

	@Override
	public char symbol() { return 'F'; }
}


// ===== output =====
// Cycle 1
// ..............................
// Cycle 100
// ......W..WWWWSWWWWW.SWW...S.S.
// .....WW..WWWSWWWWWWWWWWWW.....
// ......WWWWSWWWWWWWWWWWWWWW....
// ...S..W.WWWWWW.WWWWWWWWWWW.S..
// ...S..WSWWW.WWWW.WW.WWWWWW....
// .......WWWWWWWW.W.WWWWWWWW....
// .S.....WWWWWWWWWWWWWWSWWWW...S
// .SSS..S.WWWWWWWWWWWWWWWW......
// ........WWWWWWWWWWWWWWWWW.....
// .......W.SWWWWWWW..SWWW.......
// ...........W.WWWWWWWWWWWW.....
// ...........SW.WWWS.WWWWW.....S

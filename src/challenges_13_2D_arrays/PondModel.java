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
    
}

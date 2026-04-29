/*
 * Complete the following tasks:
 * Change QueryList to extend ArrayList, removing the items field.
 * Add a student id field to the Student class, and Implement a way to compare
 * Students, so that students are naturally ordered by a student id.
 * Implement at least one other mechanism for comparing Students by course or
 * year, or for ScoredStudents, by percent complete.
 * Override the matchFieldValue method on the ScoredStudent class, so that you
 * return students, not matched on percent complete equal to a value, but on
 * percent less than or equal to a submitted value. Note: an ScoredStudent
 * should be searchable by the same fields as Student as well.
 * Run your code for 25 random students, selecting students who are less than or
 * equal to 50% done their course, and print out the list, sorted in at least
 * two ways, first by using List.sort with the Comparator.naturalOrder()
 * comparator, and then using your own Comparator, so first by student id, as
 * well as one of the other ways you selected.
 */

package challenges_19_comparators;

public class Comparators {
    
}
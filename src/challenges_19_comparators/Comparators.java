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

// Implements Student and ScoredStudent, QueryList extends ArrayList, and example sorting/filtering
package challenges_19_comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

// Comparator and student examples
public class Comparators {

	public static void main(String[] args) {
		// Create a QueryList and populate with random ScoredStudents
		QueryList students = new QueryList();
		Random rnd = new Random(42);
		String[] courses = { "Math", "CS", "History", "Biology" };

		for (int i = 0; i < 25; i++) {
			int id = 1000 + i;
			String name = "Student" + i;
			String course = courses[rnd.nextInt(courses.length)];
			int year = 1 + rnd.nextInt(4);
			int percent = rnd.nextInt(101); // 0..100
			students.add(new ScoredStudent(id, name, course, year, percent));
		}

		// Select students with percent <= 50
		List<Student> selected = students.matchFieldValue("percent", "50");

		System.out.println("Selected (percent <= 50): \n");

		// Sort using natural order (by student id)
		Collections.sort(selected, Comparator.naturalOrder());
		System.out.println("-- Sorted by natural order (student id):");
		for (Student s : selected) {
			System.out.println(s);
		}

		System.out.println();

		// Sort by course then year using a custom comparator
		selected.sort(Comparator.comparing(Student::getCourse).thenComparing(Student::getYear));
		System.out.println("-- Sorted by course, then year:");
		for (Student s : selected) {
			System.out.println(s);
		}
	}

}

// QueryList extends ArrayList and provides a simple field matching method
class QueryList extends ArrayList<Student> {
	// Match field equals (or special handling for percent)
	public List<Student> matchFieldValue(String field, String value) {
		List<Student> result = new ArrayList<>();
		for (Student s : this) {
			if (field.equalsIgnoreCase("id") && String.valueOf(s.getId()).equals(value)) {
				result.add(s);
			} else if (field.equalsIgnoreCase("name") && s.getName().equalsIgnoreCase(value)) {
				result.add(s);
			} else if (field.equalsIgnoreCase("course") && s.getCourse().equalsIgnoreCase(value)) {
				result.add(s);
			} else if (field.equalsIgnoreCase("year") && String.valueOf(s.getYear()).equals(value)) {
				result.add(s);
			} else if (field.equalsIgnoreCase("percent") && s instanceof ScoredStudent) {
				// For percent, select students with percent <= provided value
				int v = Integer.parseInt(value);
				if (((ScoredStudent) s).getPercent() <= v) {
					result.add(s);
				}
			}
		}
		return result;
	}
}

// Student class, comparable by student id
class Student implements Comparable<Student> {
	private int id;
	private String name;
	private String course;
	private int year;

	Student(int id, String name, String course, int year) {
		this.id = id;
		this.name = name;
		this.course = course;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCourse() {
		return course;
	}

	public int getYear() {
		return year;
	}

	@Override
	public int compareTo(Student other) {
		return Integer.compare(this.id, other.id);
	}

	@Override
	public String toString() {
		return String.format("%d: %s, %s year %d", id, name, course, year);
	}
}

// ScoredStudent extends Student with percentComplete and overrides match behaviour
class ScoredStudent extends Student {
	private int percent;

	ScoredStudent(int id, String name, String course, int year, int percent) {
		super(id, name, course, year);
		this.percent = percent;
	}

	public int getPercent() {
		return percent;
	}

	@Override
	public String toString() {
		return String.format("%d: %s, %s year %d, percent=%d", getId(), getName(), getCourse(), getYear(), percent);
	}
}
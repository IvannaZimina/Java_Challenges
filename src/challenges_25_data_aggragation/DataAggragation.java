/* Create a new class with a main method that does the following:
Copy the two courses, jac and pbc passing an additional argument for the lecture count. 50 for pbc and 100 for jac.
Add a third course, titled "Creating Games in Java". You don't have to pass a lecture count for this one.
Use Stream.generate or Stream.iterate to generate 5000 random students and create a list of these.
Use the getPercentComplete method, to calculate the average percentage completed for all students for just the Java Advanced Class, using the reduce terminal operation.
Use this result, multiplying it by 1.25, to collect a group of students (either as a list, or a set).  These would be the students who've completed more than three quarters of that average percentage. 
Sort by the longest enrolled students who are still active, because you're going to offer your new course to 10 of these students, for a trial run.
Add the new course to these ten students.  
Make one change to the Student's getRandomStudent method, using a minimum lecture of 30. 
*/

package challenges_25_data_aggragation;

import challenges_24_terminal_operations.Course;
import challenges_24_terminal_operations.Student;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Implements the task described in the comment inside the file.
public class DataAggragation {
    public static void main(String[] args) {
	// Copy two courses jac and pbc passing lecture counts: 100 for jac, 50 for pbc
	Course jac = new Course("JAC", "Java Advanced Class", 100);
	Course pbc = new Course("PBC", "Programming Basics Course", 50);
	// Add third course without lecture count per task
	Course games = new Course("CGJ", "Creating Games in Java");

	Course[] courses = new Course[] { jac, pbc, games };

	// Use Stream.generate to generate 5000 random students
	List<Student> students = Stream.generate(() -> Student.getRandomStudent(courses))
		.limit(5000)
		.collect(Collectors.toList());

	// Use getPercentComplete to calculate average percentage for Java Advanced Class using reduce
	List<Double> percents = students.stream()
		.map(s -> s.getPercentComplete(jac.getCode()))
		.collect(Collectors.toList());

	double sum = percents.stream().reduce(0.0, Double::sum);
	long count = percents.size();
	double avg = count == 0 ? 0.0 : sum / count;
	System.out.println("Average % complete for JAC: " + avg);

	// Multiply by 1.25 and collect students who've completed more than three quarters of that average percentage
	double adjusted = avg * 1.25;
	double threshold = adjusted * 0.75; // as specified: three quarters of the adjusted value

	List<Student> selected = students.stream()
		.filter(s -> s.getPercentComplete(jac.getCode()) > threshold)
		.collect(Collectors.toList());
	System.out.println("Selected students count: " + selected.size());

	// Sort by the longest enrolled students who are still active (active = monthsSinceActive <= 6)
	List<Student> candidates = selected.stream()
		.filter(s -> s.getMonthsSinceActive() <= 6)
		.sorted((a, b) -> Integer.compare(b.getYearsSinceEnrolled(), a.getYearsSinceEnrolled()))
		.collect(Collectors.toList());

	// Offer new course to 10 of these students and add the new course
	candidates.stream().limit(10).forEach(s -> {
	    s.addCourse(games, YearMonth.now());
	});

	System.out.println("Added new course to top " + Math.min(10, candidates.size()) + " candidates.");
    }
}

// ====== output ======
// Average % complete for JAC: 43.5374
// Selected students count: 2821
// Added new course to top 10 candidates.

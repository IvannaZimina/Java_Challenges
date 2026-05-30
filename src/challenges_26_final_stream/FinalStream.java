/*
In this challenge, you'll again use Streams with the Student Engagement Code.
Before you start, first change the getRandomStudent method on Student, to select a random number and random selection of courses. 
Every student should be enrolled and have activity in at least one course.
Set up three or four courses, using the lecture count version of the constructor on several of these, to pass lecture counts greater than 40.
Generate a list of 10,000 students who've enrolled in the past 4 years.
Pass the Supplier Functional Interface code three or four courses. 
Next, answer the following questions.
How many of the students are enrolled in each course?
How many students are taking 1, 2, or 3 courses?
Determine the average percentage complete, for all courses, for this group of students. Hint, try using Collectors.averagingDouble to get this information.
For each course, get activity counts by year, using the last activity year field.
Think about how you'd go about answering these questions, using some of the Stream operations you've learned, especially the collect terminal operation in conjunction with the Collectors helper class methods.

*/

package challenges_26_final_stream;

import challenges_24_terminal_operations.Course;
import challenges_24_terminal_operations.CourseEngagement;
import challenges_24_terminal_operations.Student;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Final stream challenge implementation
public class FinalStream {
    public static void main(String[] args) {
	// Setup 4 courses, some with lecture counts > 40
	Course c1 = new Course("ADVJAVA", "Advanced Java", 60);
	Course c2 = new Course("DSALGO", "Data Structures & Algorithms", 45);
	Course c3 = new Course("WEBDEV", "Web Development", 30);
	Course c4 = new Course("DBSYS", "Databases", 50);
	Course[] courses = new Course[] { c1, c2, c3, c4 };

	// Generate students who've enrolled in past 4 years: collect 10,000
	List<Student> students = Stream.generate(() -> Student.getRandomStudent(courses))
		.filter(s -> s.getYearsSinceEnrolled() <= 4)
		.limit(10000)
		.collect(Collectors.toList());

	// How many of the students are enrolled in each course?
	Map<String, Long> enrolledPerCourse = Stream.of(courses)
		.collect(Collectors.toMap(Course::getCode, c -> students.stream().filter(s -> s.getEngagements().containsKey(c.getCode())).count()));
	System.out.println("Enrolled per course: " + enrolledPerCourse);

	// How many students are taking 1, 2, or 3+ courses?
	Map<Integer, Long> byCourseCount = students.stream()
		.collect(Collectors.groupingBy(s -> Math.min(3, s.getEngagements().size()), Collectors.counting()));
	System.out.println("Students by course count (1,2,3+): " + byCourseCount);

	// Determine average percentage complete, for all courses, for this group of students.
	Double avgPercent = students.stream()
		.flatMap(s -> s.getEngagements().values().stream())
		.collect(Collectors.averagingDouble(CourseEngagement::getPercentComplete));
	System.out.println("Average percent complete across all courses: " + avgPercent);

	// For each course, get activity counts by year, using the last activity year field.
	Map<String, Map<Integer, Long>> activityByCourseYear = students.stream()
		.flatMap(s -> s.getEngagements().values().stream())
		.collect(Collectors.groupingBy(e -> e.getCourse().getCode(),
			Collectors.groupingBy(e -> e.getLastActivity().getYear(), Collectors.counting())));
	System.out.println("Activity counts by course and year: " + activityByCourseYear);
    }
}

/* ===== output ======
Enrolled per course: {WEBDEV=6263, DBSYS=6261, ADVJAVA=6151, DSALGO=6244}
Students by course count (1,2,3+): {1=2526, 2=2491, 3=4983}
Average percent complete across all courses: 84.71693620664286
Activity counts by course and year: {WEBDEV={2021=825, 2022=1216, 2023=1296, 2024=1207, 2025=1231, 2026=488}, DBSYS={2021=840, 2022=1286, 2023=1198, 2024=1191, 2025=1232, 2026=514}, ADVJAVA={2021=820, 2022=1212, 2023=1185, 2024=1225, 2025=1184, 2026=525}, DSALGO={2021=819, 2022=1252, 2023=1233, 2024=1173, 2025=1221, 2026=546}}
*/
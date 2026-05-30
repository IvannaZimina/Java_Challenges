package challenges_24_terminal_operations;

import java.time.Year;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

// Student with demographic data and a map of CourseEngagements
public class Student {
    public enum Gender { MALE, FEMALE, OTHER }

    private final String id;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageAtEnrollment;
    private final Gender gender;
    private final boolean hasProgrammingExperience;
    private final Map<String, CourseEngagement> engagements = new HashMap<>();

    public Student(String id, String countryCode, int yearEnrolled, int ageAtEnrollment, Gender gender, boolean hasProgrammingExperience) {
        this.id = id;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageAtEnrollment = ageAtEnrollment;
        this.gender = gender;
        this.hasProgrammingExperience = hasProgrammingExperience;
    }

    public String getId() { return id; }
    public String getCountryCode() { return countryCode; }
    public int getYearEnrolled() { return yearEnrolled; }
    public int getAgeAtEnrollment() { return ageAtEnrollment; }
    public Gender getGender() { return gender; }
    public boolean isHasProgrammingExperience() { return hasProgrammingExperience; }

    public Map<String, CourseEngagement> getEngagements() { return engagements; }

    // from task: add getter methods for calculated fields, like `getYearsSinceEnrolled` and `getAge`.
    public int getYearsSinceEnrolled() {
        return Year.now().getValue() - yearEnrolled;
    }

    public int getAge() {
        return ageAtEnrollment + getYearsSinceEnrolled();
    }

    // Returns months since activity for a given course code
    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement e = engagements.get(courseCode);
        if (e == null) return Integer.MAX_VALUE;
        return e.getMonthsSinceActive();
    }

    // from task: Add an overloaded version of `getMonthsSinceActive`, to get the least number of inactive months, from all courses.
    public int getMonthsSinceActive() {
        return engagements.values().stream()
                .mapToInt(CourseEngagement::getMonthsSinceActive)
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    public double getPercentComplete(String courseCode) {
        CourseEngagement e = engagements.get(courseCode);
        if (e == null) return 0.0;
        return e.getPercentComplete();
    }

    // Add course with specified activity date
    public void addCourse(Course course, YearMonth activity) {
        CourseEngagement e = new CourseEngagement(course, activity);
        e.watchLecture(0, activity);
        engagements.put(course.getCode(), e);
    }

    // Add course defaulting to now
    public void addCourse(Course course) {
        addCourse(course, YearMonth.now());
    }

    // Include the method `watchLecture`, that takes a course code, a lecture number and an activity year and month,
    // and calls the method of the same name, on the course engagement record.
    public void watchLecture(String courseCode, int lectureNumber, YearMonth activity) {
        CourseEngagement e = engagements.get(courseCode);
        if (e != null) {
            e.watchLecture(lectureNumber, activity);
        }
    }

    @Override
    public String toString() {
        return "Student{" + id + ", country=" + countryCode + ", age=" + getAge() + ", yearsEnrolled=" + getYearsSinceEnrolled() + ", gender=" + gender + "}";
    }

    // from task: create a static factory method on this class, `getRandomStudent`, that will return a new instance of Student, with random data
    public static Student getRandomStudent(Course... courses) {
        Random rnd = new Random();
        String id = UUID.randomUUID().toString().substring(0, 8);
        String[] countries = {"US","UK","EE","LV","LT","DE","FR","ES","IT"};
        String country = countries[rnd.nextInt(countries.length)];
        int yearEnrolled = 2005 + rnd.nextInt(19); // 2005..2023
        int ageAtEnrollment = 18 + rnd.nextInt(30); // 18..47
        Gender gender = rnd.nextBoolean() ? Gender.MALE : Gender.FEMALE;
        boolean hasProg = rnd.nextBoolean();

        Student s = new Student(id, country, yearEnrolled, ageAtEnrollment, gender, hasProg);

        // For each course, call watchLecture with a random lecture number, and activity year and month
        for (Course c : courses) {
            // choose a random months offset up to 60 months ago for activity
            int monthsAgo = rnd.nextInt(61);
            YearMonth activity = YearMonth.now().minusMonths(monthsAgo);
            s.addCourse(c, activity);
            int lecture = rnd.nextInt(c.getLectureCount() + 1);
            s.watchLecture(c.getCode(), lecture, activity);
        }

        return s;
    }
}

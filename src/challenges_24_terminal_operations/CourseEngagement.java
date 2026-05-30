package challenges_24_terminal_operations;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

// CourseEngagement holds student's engagement with a single course
public class CourseEngagement {
    private final Course course;
    private final YearMonth enrollmentDate;
    private String engagementType; // simple string for type
    private int lastLecture;
    private YearMonth lastActivity;

    public CourseEngagement(Course course, YearMonth enrollmentDate) {
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.engagementType = "unknown";
        this.lastLecture = 0;
        this.lastActivity = enrollmentDate;
    }

    public Course getCourse() {
        return course;
    }

    public YearMonth getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getEngagementType() {
        return engagementType;
    }

    public int getLastLecture() {
        return lastLecture;
    }

    public YearMonth getLastActivity() {
        return lastActivity;
    }

    // from task: The `getMonthsSinceActive` method should return the months elapsed, since the last course activity.
    // Returns number of months since last activity.
    public int getMonthsSinceActive() {
        return (int) ChronoUnit.MONTHS.between(lastActivity, YearMonth.now());
    }

    // from task: The `getPercentComplete` method should use the last lecture, and the lecture count on course, to return a percentage complete.
    public double getPercentComplete() {
        if (course.getLectureCount() <= 0) return 0.0;
        return 100.0 * ((double) lastLecture / (double) course.getLectureCount());
    }

    // The watchLecture method updates the last lecture and last activity date.
    // It takes a lecture number, and an activity YearMonth.
    public void watchLecture(int lectureNumber, YearMonth activity) {
        // update last lecture (cap at course lecture count)
        if (lectureNumber > this.lastLecture) {
            this.lastLecture = Math.min(lectureNumber, course.getLectureCount());
        }
        this.lastActivity = activity;
    }

    @Override
    public String toString() {
        return "Engagement(" + course.getCode() + ", lastLecture=" + lastLecture + ", lastActivity=" + lastActivity + ")";
    }
}

package challenges_24_terminal_operations;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Runner that creates a stream of Students and answers the questions from the task
public class TerminalOperations {
    public static void main(String[] args) {
        // Create some sample courses
        Course c1 = new Course("CS101", "Intro to Java", 20);
        Course c2 = new Course("DS200", "Data Structures", 25);
        Course c3 = new Course("WD300", "Web Development", 15);
        Course[] courses = new Course[] { c1, c2, c3 };

        // Create a source for a stream of Students using the Student.getRandomStudent supplier
        // Use a large enough number to get a variety of Student data.
        List<Student> students = Stream.generate(() -> Student.getRandomStudent(courses))
                .limit(500)
                .collect(Collectors.toList());

        // How many male and female students are in the group.
        Map<Student.Gender, Long> byGender = students.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));
        System.out.println("By gender: " + byGender);

        // How many students fall into the three age ranges, less than age 30, between 30 and 40, over 40 years old.
        Map<String, Long> byAgeRange = students.stream()
                .collect(Collectors.groupingBy(s -> {
                    int age = s.getAge();
                    if (age < 30) return "<30";
                    if (age <= 40) return "30-40";
                    return ">40";
                }, Collectors.counting()));
        System.out.println("By age range: " + byAgeRange);

        // Use summaryStatistics on the student's age
        IntSummaryStatistics stats = students.stream().mapToInt(Student::getAge).summaryStatistics();
        System.out.println("Age stats: count=" + stats.getCount() + ", min=" + stats.getMin() + ", max=" + stats.getMax() + ", avg=" + stats.getAverage());

        // What countries are the students from? Print a distinct list of the country codes.
        Set<String> countries = students.stream().map(Student::getCountryCode).distinct().collect(Collectors.toSet());
        System.out.println("Countries: " + countries);

        // Are there students that are still active and have been enrolled for more than 7 years?
        // We'll treat "still active" as having activity within last 6 months.
        boolean anyActiveLongEnrolled = students.stream()
                .anyMatch(s -> s.getYearsSinceEnrolled() > 7 && s.getMonthsSinceActive() <= 6);
        System.out.println("Any student active and enrolled >7 years? " + anyActiveLongEnrolled);

        // Next, select 5 of the students above and print their information out.
        System.out.println("Sample 5 students:");
        students.stream().limit(5).forEach(s -> {
            System.out.println(s);
            // print engagements
            s.getEngagements().values().forEach(e -> System.out.println("  " + e + ", monthsSinceActive=" + e.getMonthsSinceActive() + ", pct=" + String.format("%.1f", e.getPercentComplete()) + "%"));
        });
    }
}


// ===== output =====
/*
By gender: {FEMALE=249, MALE=251}
By age range: {<30=36, 30-40=136, >40=328}
Age stats: count=500, min=21, max=67, avg=44.664
Countries: [EE, DE, UK, LT, LV, IT, FR, ES, US]
Any student active and enrolled >7 years? true
Sample 5 students:
Student{10cd458a, country=LT, age=33, yearsEnrolled=5, gender=MALE}
  Engagement(CS101, lastLecture=9, lastActivity=2023-04), monthsSinceActive=37, pct=45,0%
  Engagement(DS200, lastLecture=6, lastActivity=2023-05), monthsSinceActive=36, pct=24,0%
  Engagement(WD300, lastLecture=4, lastActivity=2025-07), monthsSinceActive=10, pct=26,7%
Student{740bbf36, country=UK, age=41, yearsEnrolled=4, gender=FEMALE}
  Engagement(CS101, lastLecture=15, lastActivity=2026-05), monthsSinceActive=0, pct=75,0%
  Engagement(DS200, lastLecture=0, lastActivity=2024-12), monthsSinceActive=17, pct=0,0%
  Engagement(WD300, lastLecture=11, lastActivity=2025-01), monthsSinceActive=16, pct=73,3%
Student{14320ab1, country=LV, age=56, yearsEnrolled=17, gender=MALE}
  Engagement(CS101, lastLecture=2, lastActivity=2025-12), monthsSinceActive=5, pct=10,0%
  Engagement(DS200, lastLecture=14, lastActivity=2026-03), monthsSinceActive=2, pct=56,0%
  Engagement(WD300, lastLecture=9, lastActivity=2025-05), monthsSinceActive=12, pct=60,0%
Student{3bbb8004, country=UK, age=42, yearsEnrolled=5, gender=MALE}
  Engagement(CS101, lastLecture=6, lastActivity=2022-05), monthsSinceActive=48, pct=30,0%
  Engagement(DS200, lastLecture=1, lastActivity=2024-01), monthsSinceActive=28, pct=4,0%
  Engagement(WD300, lastLecture=1, lastActivity=2023-04), monthsSinceActive=37, pct=6,7%
Student{2286ae81, country=ES, age=37, yearsEnrolled=10, gender=MALE}
  Engagement(CS101, lastLecture=2, lastActivity=2021-08), monthsSinceActive=57, pct=10,0%
  Engagement(DS200, lastLecture=4, lastActivity=2022-08), monthsSinceActive=45, pct=16,0%
  Engagement(WD300, lastLecture=9, lastActivity=2024-02), monthsSinceActive=27, pct=60,0%
*/
package challenges_24_terminal_operations;

public final class Course {
    private final String code;
    private final String title;
    private final int lectureCount;

    // Simple immutable Course class with getters
    public Course(String code, String title, int lectureCount) {
        this.code = code;
        this.title = title;
        this.lectureCount = lectureCount;
    }

    // Overloaded constructor: allow creating a course without specifying lecture count
    // from task: You don't have to pass a lecture count for this one.
    public Course(String code, String title) {
        this(code, title, 0);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getLectureCount() {
        return lectureCount;
    }

    @Override
    public String toString() {
        return code + " - " + title + " (" + lectureCount + " lectures)";
    }
}

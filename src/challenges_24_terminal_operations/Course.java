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

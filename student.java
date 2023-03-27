public class Student {
    private String name;
    private String gradeLevel;
    private String strand;
    private Course COURSES;

    public Student(String name, String gradeLevel, String strand, COURSES COURSES) {
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.strand = strand;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public String getStrand() {
        return strand;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(COURSES COURSES) {
        this.course = course;
    }
}

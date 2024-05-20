package Software;
import java.util.List;
public class Student {
        private static final int ID_LENGTH = 5; // Fixed length for student ID

        private String id;
        private String fullName;
        private List<Course> courses;

        public Student(String id, String fullName, List<Course> courses) {
            if (id.length() != ID_LENGTH) {
                throw new IllegalArgumentException("Student ID must be exactly " + ID_LENGTH + " characters long.");
            }

            this.id = id;
            this.fullName = fullName;
            this.courses = courses;
        }

        // Getters and setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public List<Course> getCourses() {
            return courses;
        }

        public void setCourses(List<Course> courses) {
            this.courses = courses;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ").append(id).append(", Full Name: ").append(fullName).append(", Courses: ");
            for (Course course : courses) {
                sb.append(course.getName()).append("; ");
            }
            return sb.toString().trim();
        }
    }


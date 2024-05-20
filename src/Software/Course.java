package Software;

public class Course {
        private String id;
        private String name;

        public Course(String id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        // Example of some software engineering courses
        public static Course[] getSoftwareEngineeringCourses() {
            return new Course[]{
                    new Course("SE101", "Introduction to Software Engineering"),
                    new Course("SE201", "Software Requirements Engineering"),
                    new Course("SE301", "Software Design and Architecture"),
                    new Course("SE401", "Software Testing and Quality Assurance"),
                    new Course("SE501", "Operating Systems")
            };
        }
    }


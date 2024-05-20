package Software;
import java.util.*;
public class Main {

        private static List<Student> students = new ArrayList<>();

        public static void main(String[] args) {
            String data = "";

            Scanner scanner = new Scanner(System.in);
            char choice;

            do {
                System.out.println("MENU:");
                System.out.println("a. Create a student");
                System.out.println("b. Search students");
                System.out.println("c. Delete a student");
                System.out.println("d. Update a student");
                System.out.println("e. Print full names of students");
                System.out.println("f. Print courses of a student");
                System.out.println("g. Send information");
                System.out.println("h. Save list of students to file");
                System.out.println("i. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.next().charAt(0);
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 'a' -> createStudent(scanner);
                    case 'b' -> searchStudents(scanner);
                    case 'c' -> deleteStudent(scanner);
                    case 'd' -> updateStudent(scanner);
                    case 'e' -> printFullNames();
                    case 'f' -> printStudentCourses(scanner);
                    case 'g' -> sendInformation(new Email(), data);
                    case 'h' -> saveStudentsToFile();
                    case 'i' -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice. Please enter a valid option.");
                }
            } while (choice != 'i');
        }

    private static void saveStudentsToFile() {
        ToFile toFile = new ToFile();
        toFile.saveStudentsToFile(students);
    }


    private static void sendInformation(DataTransfer dataTransfer, String data) {
            // Prompt the user for the data to send
            System.out.println("Enter data to send:");
            Scanner scanner = new Scanner(System.in);
            String inputData = scanner.nextLine();

            // Send the data using the specified DataTransfer subclass
            dataTransfer.sendData(inputData);
        }


    private static void createStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        String id = scanner.nextLine();

        // Check if the length of the entered ID exceeds 5 characters
        if (id.length() > 5) {
            System.out.println("Error: Student ID cannot exceed 5 characters. Please try again.");
            return; // Return to the menu
        }

        // Check if the entered ID already exists
        if (isStudentIdUnique(id)) {
            System.out.println("Enter student full name:");
            String fullName = scanner.nextLine();

            // Display available courses with their IDs
            List<Course> sampleCourses = List.of(Course.getSoftwareEngineeringCourses());
            System.out.println("Enter courses taken by the student:");
            for (int i = 0; i < sampleCourses.size(); i++) {
                Course course = sampleCourses.get(i);
                System.out.println((i + 1) + ". " + course.getId() + ": " + course.getName());
            }

            // Create a list to hold the courses
            List<Course> courses = new ArrayList<>();

            // Prompt user to choose courses by entering their IDs
            boolean addingCourses = true;
            while (addingCourses) {
                System.out.println("Enter course ID (or 0 to finish):");
                String courseIdInput = scanner.nextLine();
                if (courseIdInput.equals("0")) {
                    break; // Exit loop if the user chooses to finish
                }
                // Check if the entered course ID is valid
                boolean validCourseId = false;
                for (Course course : sampleCourses) {
                    if (course.getId().equalsIgnoreCase(courseIdInput)) {
                        courses.add(course);
                        System.out.println("Added course: " + course.getName());
                        validCourseId = true;
                        break;
                    }
                }
                if (!validCourseId) {
                    System.out.println("Invalid course ID. Please choose a valid course.");
                }

                System.out.println("Do you want to add another course? (yes/no)");
                String continueInput = scanner.nextLine().toLowerCase();
                addingCourses = continueInput.equals("yes");
            }

            // Create the student object
            Student student = new Student(id, fullName, courses);

            // Add the student to the list
            students.add(student);
            System.out.println("Student created and added successfully!");
        } else {
            System.out.println("Student ID already exists. Please enter a unique ID.");
        }
    }

    // Method to check if the entered student ID is unique
    private static boolean isStudentIdUnique(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return false; // ID already exists
            }
        }
        return true; // ID is unique
    }

    private static void searchStudents(Scanner scanner) {
        System.out.println("Search by (1)ID or (2)Name:");
        int searchBy = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (searchBy) {
            case 1 -> {
                System.out.println("Enter student ID:");
                String searchId = scanner.nextLine();
                printMatchingStudentsById(searchId);
            }
            case 2 -> {
                System.out.println("Enter student name:");
                String searchName = scanner.nextLine();
                printMatchingStudentsByName(searchName);
            }
            default -> System.out.println("Invalid option. Please choose 1 or 2.");
        }
    }

    // Method to print matching students by ID
    private static void printMatchingStudentsById(String id) {
        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("ID: " + student.getId() + ", Full Name: " + student.getFullName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found with ID: " + id);
        }
    }

    // Method to print matching students by name
    private static void printMatchingStudentsByName(String name) {
        boolean found = false;
        for (Student student : students) {
            if (student.getFullName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("ID: " + student.getId() + ", Full Name: " + student.getFullName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found with name: " + name);
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.println("Enter student ID to delete:");
        String idToDelete = scanner.nextLine();

        boolean removed = false;
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId().equals(idToDelete)) {
                iterator.remove();
                removed = true;
                System.out.println("Student with ID " + idToDelete + " has been deleted.");
                break;
            }
        }
        if (!removed) {
            System.out.println("No student found with ID " + idToDelete + ".");
        }
    }

    private static void updateStudent(Scanner scanner) {
        System.out.println("Enter student ID to update:");
        String idToUpdate = scanner.nextLine();

        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(idToUpdate)) {
                found = true;
                System.out.println("Enter new full name:");
                String newFullName = scanner.nextLine();
                student.setFullName(newFullName);
                System.out.println("Student with ID " + idToUpdate + " has been updated.");
                break;
            }
        }
        if (!found) {
            System.out.println("No student found with ID " + idToUpdate + ".");
        }
    }

    private static void printFullNames() {
        System.out.println("Full Names of Students:");
        for (Student student : students) {
            System.out.println(student.getFullName());
        }
    }

    private static void printStudentCourses(Scanner scanner) {
        System.out.println("Enter student ID to print courses:");
        String idToPrintCourses = scanner.nextLine();

        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(idToPrintCourses)) {
                found = true;
                System.out.println("Courses taken by student with ID " + idToPrintCourses + ":");
                List<Course> courses = student.getCourses();
                if (courses.isEmpty()) {
                    System.out.println("No courses found for this student.");
                } else {
                    for (Course course : courses) {
                        System.out.println(course.getName());
                    }
                }
                break;
            }
        }
        if (!found) {
            System.out.println("No student found with ID " + idToPrintCourses + ".");
        }
    }




    }

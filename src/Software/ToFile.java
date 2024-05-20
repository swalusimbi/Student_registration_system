package Software;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ToFile extends DataTransfer {
    @Override
    public void sendData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            writer.write(data);
            System.out.println("Data has been saved to the file: students.txt");
        } catch (IOException e) {
            System.out.println("Failed to write data to the file. Error: " + e.getMessage());
        }
    }

    public void saveStudentsToFile(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Student list has been saved to the file: students.txt");
        } catch (IOException e) {
            System.out.println("Failed to write student list to the file. Error: " + e.getMessage());
        }
    }
}

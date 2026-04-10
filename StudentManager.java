import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "students.dat";

    // Read students from file
    private ArrayList<Student> readStudents() {
        ArrayList<Student> list = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            list = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            // file may not exist first time
        }

        return list;
    }

    // Write students to file
    private void writeStudents(ArrayList<Student> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("File write error.");
        }
    }

    // Add Student
    public void addStudent(Student s) {
        ArrayList<Student> list = readStudents();

        for (Student st : list) {
            if (st.getId() == s.getId()) {
                System.out.println("ID already exists!");
                return;
            }
        }

        list.add(s);
        writeStudents(list);
        System.out.println("Student added.");
    }

    // View All
    public void viewStudents() {
        ArrayList<Student> list = readStudents();

        if (list.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Student s : list) {
            System.out.println(s);
        }
    }

    // Search by ID
    public void searchStudent(int id) {
        ArrayList<Student> list = readStudents();

        for (Student s : list) {
            if (s.getId() == id) {
                System.out.println("Found: " + s);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Update Student
    public void updateStudent(int id, String name, double marks) {
        ArrayList<Student> list = readStudents();
        boolean found = false;

        for (Student s : list) {
            if (s.getId() == id) {
                s.setName(name);
                s.setMarks(marks);
                found = true;
                break;
            }
        }

        if (found) {
            writeStudents(list);
            System.out.println("Updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        ArrayList<Student> list = readStudents();
        boolean removed = list.removeIf(s -> s.getId() == id);

        if (removed) {
            writeStudents(list);
            System.out.println("Deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Sort by Marks
    public void sortByMarks() {
        ArrayList<Student> list = readStudents();

        list.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));

        for (Student s : list) {
            System.out.println(s);
        }
    }

    // Sort by Name
    public void sortByName() {
        ArrayList<Student> list = readStudents();

        list.sort(Comparator.comparing(Student::getName));

        for (Student s : list) {
            System.out.println(s);
        }
    }

    // Topper
    public void showTopper() {
        ArrayList<Student> list = readStudents();

        if (list.isEmpty()) return;

        Student top = list.get(0);

        for (Student s : list) {
            if (s.getMarks() > top.getMarks()) {
                top = s;
            }
        }

        System.out.println("Topper: " + top);
    }

    // Count Students
    public void countStudents() {
        ArrayList<Student> list = readStudents();
        System.out.println("Total Students: " + list.size());
    }

    // Statistics
    public void showStats() {
        ArrayList<Student> list = readStudents();

        if (list.isEmpty()) return;

        double sum = 0;

        for (Student s : list) {
            sum += s.getMarks();
        }

        double avg = sum / list.size();
        System.out.println("Average Marks: " + avg);
    }

    // Search by Name
    public void searchByName(String name) {
        ArrayList<Student> list = readStudents();
        boolean found = false;

        for (Student s : list) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(s);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No match found.");
        }
    }

    // Export to Text
    public void exportToText() {
        ArrayList<Student> list = readStudents();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student s : list) {
                bw.write(s.toString());
                bw.newLine();
            }
            System.out.println("Exported to students.txt");
        } catch (IOException e) {
            System.out.println("Error exporting.");
        }
    }
}
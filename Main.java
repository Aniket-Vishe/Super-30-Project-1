import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager sm = new StudentManager();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student (by ID/Name)");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students (Marks/Name)");
            System.out.println("7. Show Topper");
            System.out.println("8. Count Students");
            System.out.println("9. Export to File");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    if (name.isEmpty() || marks < 0 || marks > 100) {
                        System.out.println("Invalid input.");
                        break;
                    }

                    sm.addStudent(new Student(id, name, marks));
                    break;

                case 2:
                    sm.viewStudents();
                    break;

                case 3:
                    System.out.println("1. Search by ID");
                    System.out.println("2. Search by Name");
                    int opt = sc.nextInt();

                    if (opt == 1) {
                        System.out.print("Enter ID: ");
                        sm.searchStudent(sc.nextInt());
                    } else {
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        sm.searchByName(sc.nextLine());
                    }
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    String n = sc.nextLine();

                    System.out.print("New Marks: ");
                    double m = sc.nextDouble();

                    sm.updateStudent(uid, n, m);
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    sm.deleteStudent(sc.nextInt());
                    break;

                case 6:
                    System.out.println("1. Sort by Marks");
                    System.out.println("2. Sort by Name");
                    int s = sc.nextInt();

                    if (s == 1)
                        sm.sortByMarks();
                    else
                        sm.sortByName();
                    break;

                case 7:
                    sm.showTopper();
                    break;

                case 8:
                    sm.countStudents();
                    break;

                case 9:
                    sm.exportToText();
                    break;

                case 10:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
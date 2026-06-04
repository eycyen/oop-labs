import java.util.ArrayList;
import java.util.Collections;

public class UniversityManagementSystem {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        try {
            people.add(new Student(1001, "Ahmet", "Computer Engineering", 3.85));
            people.add(new Student(1002, "Ayse", "Software Engineering", 3.40));
        }
        catch (InvalidGPAException e) {
            System.out.println(e);
        }
        
        people.add(new Professor(2001, "Mehmet", "Prof. Dr.", 30000.0, 5));
        people.add(new Professor(2002, "Elif", "Assoc. Prof.", 30000.0, 2));
        people.add(new ResearchAssistant(3001, "Ali", "Res. Asst.", 20000.0, 4));
        people.add(new ResearchAssistant(3002, "Zeynep", "Res. Asst.", 20000.0, 3));

        System.out.println("=== University Members ===");
        for (Person p : people) {
            p.displayInfo();
        }
        System.out.println();

        System.out.println("=== Academic Staff Salaries ===");
        for (Person p : people) {
            if (p instanceof AcademicStaff) {
                AcademicStaff staff = (AcademicStaff) p;
                System.out.printf("%s salary: %.1f",staff.getName(),staff.calculateSalary());
            }
        }
        System.out.println();

        System.out.println("=== Students Sorted by GPA ===");
        Collections.sort(students);
        for (Student s : students) {
            System.out.printf("%s - %.2f",s.getName(),s.getGpa());
        }
    }
}

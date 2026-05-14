import java.io.*;
import java.util.*;

class Student {
    private int id;
    private String name;
    private double midterm;
    private double finalExam;

    public Student(int id, String name, double midterm, double finalExam) {
        this.id = id;
        this.name = name;

        if (midterm < 0.0) {
            this.midterm = 0.0;
        } else {
            this.midterm = midterm;
        }

        if (finalExam < 0.0) {
            this.finalExam = 0.0;
        } else {
            this.finalExam = finalExam;
        }
    }

    public double computeAverage() {
        double average = 0.4 * midterm + 0.6 * finalExam;
        return average;
    }

    public String determineStatus() {
        if (this.computeAverage() >= 60) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getMidterm() {
        return midterm;
    }

    public double getFinalExam() {
        return finalExam;
    }
}

public class StudentAnalyzer {

    public static List<Student> readStudentsFromCSV(String path) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double midterm = Double.parseDouble(parts[2].trim());
                    double finalExam = Double.parseDouble(parts[3].trim());

                    students.add(new Student(id, name, midterm, finalExam));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.printf("Error parsing the CSV file: %s\n",e.getMessage());
        }

        return students;
    }

    public static void writeResultsToCSV(String path, List<Student> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println("ID,Name,Average,Status");

            for (Student s : list) {
                String csvLine = s.getId() + "," +
                        s.getName() + "," +
                        s.computeAverage() + "," +
                        s.determineStatus();
                pw.println(csvLine);
            }
        } catch (IOException e) {
            System.err.println("Error writing to the CSV file");
        }
    }

    public static void printSummary(List<Student> list) {
        if (list.isEmpty())
            return;

        int passCount = 0;
        Student topPerformer = list.get(0);

        for (Student s : list) {
            if (s.determineStatus().equals("PASS")) {
                passCount++;
            }
            if (s.computeAverage() > topPerformer.computeAverage()) {
                topPerformer = s;
            }
        }

        System.out.println("Total students processed: " + list.size());
        System.out.println("Number of students passed: " + passCount);
        System.out.println(
                "Top performer: " + topPerformer.getName() + " (Score: " + topPerformer.computeAverage() + ")");
    }

    public static void promptPerformanceSummary(List<Student> list) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to see the best performing or worst performing students?");
        String userResponse = scanner.nextLine().trim().toLowerCase();

        if (userResponse.equals("yes") || userResponse.equals("y")) {
            System.out
                    .println("How many of those would you like to see? You may also provide a percentage (e.g., 25%).");
            String quantityInput = scanner.nextLine().trim();

            int count = 0;
            try {
                if (quantityInput.endsWith("%")) {
                    double percentage = Double.parseDouble(quantityInput.replace("%", "").trim());
                    count = (int) Math.round((percentage / 100.0) * list.size());
                } else {
                    count = Integer.parseInt(quantityInput);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid format entered. Exiting program.");
                scanner.close();
                return;
            }

            if (count < 1)
                count = 1;
            if (count > list.size())
                count = list.size();

            System.out.println("Show worst or best? (Type 'worst' or 'best')");
            String direction = scanner.nextLine().trim().toLowerCase();

            List<Student> sortedList = new ArrayList<>(list);

            if (direction.equals("best")) {
                Collections.sort(sortedList, (s1, s2) -> Double.compare(s2.computeAverage(), s1.computeAverage()));
            } else if (direction.equals("worst")) {
                Collections.sort(sortedList, Comparator.comparingDouble(Student::computeAverage));
            } else {
                System.out.println("Invalid selection. Exiting program.");
                scanner.close();
                return;
            }

            System.out.println("\n--- " + (direction.equals("best") ? "Top " : "Bottom ") + count + " Students ---");
            for (int i = 0; i < count; i++) {
                Student s = sortedList.get(i);
                System.out.println((i + 1) + ". " + s.getName() + " - Average: " + s.computeAverage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java StudentAnalyzer <input.csv> <output.csv>");
            return;
        }

        String inputPath = args[0];
        String outputPath = args[1];

        List<Student> students = readStudentsFromCSV(inputPath);

        writeResultsToCSV(outputPath, students);
        printSummary(students);
        promptPerformanceSummary(students);
    }

}
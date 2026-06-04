public class Student extends Person implements Comparable<Student> {
    private String department;
    private double gpa;

    public Student(int id, String name, String department, double gpa) throws InvalidGPAException {
        super(id,name);
        this.department = department;
        if (gpa < 0.0 || gpa > 4.0) {
            throw new InvalidGPAException("Error: GPA must be between 0 and 4");
        } 
        this.gpa = gpa;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGpa(double gpa) throws InvalidGPAException {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new InvalidGPAException("Error: GPA must be between 0 and 4");
        } 
        this.gpa = gpa;
    }

    public String getDepartment() {
        return this.department;
    }

    public double getGpa() {
        return this.gpa;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Student: %d | %s | %s | GPA: %.2f\n",
        this.getId(),
        this.getName(),
        this.getDepartment(),
        this.getGpa()
        );
    }

    @Override
    public int compareTo(Student other) {
        Double currentGpa = Double.valueOf(this.gpa);
        Double otherGpa = Double.valueOf(other.gpa);
        return currentGpa.compareTo(otherGpa);
    }
}

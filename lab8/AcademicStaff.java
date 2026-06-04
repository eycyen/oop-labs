public class AcademicStaff extends Person implements Payable {
    protected String title;
    protected double baseSalary;

    public AcademicStaff(int id, String name, String title, double baseSalary) {
        super(id,name);
        this.title = title;
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary cannot be less than zero.");
        }
        this.baseSalary = baseSalary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary cannot be less than zero.");
        }
        this.baseSalary = baseSalary;
    }

    public String getTitle() {
        return this.title;
    }

    public double getBaseSalary() {
        return this.baseSalary;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Academic Staff : %d | %s | %s | Salary : %.1f",
        this.getId(),
        this.getName(),
        this.getTitle(),
        this.getBaseSalary()
        );
    }

    public double calculateSalary() {
        return this.baseSalary;
    }
}

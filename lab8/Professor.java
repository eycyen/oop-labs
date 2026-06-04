public class Professor extends AcademicStaff {
    private int publishedPapers;

    public Professor(int id, String name, String title, double baseSalary, int publishedPapers) {
        super(id,name,title,baseSalary);
        if (publishedPapers < 0) {
            throw new IllegalArgumentException("Published papers cannot be less than zero");
        }
        this.publishedPapers = publishedPapers;
    }

    public void setPublishedPapers(int publishedPapers) {
        if (publishedPapers < 0) {
            throw new IllegalArgumentException("Published papers cannot be less than zero");
        }
        this.publishedPapers = publishedPapers;
    }

    public int getPublishedPapers() {
        return this.publishedPapers;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Professor: %d | %s | %s | Salary: %.1f\n",
        this.getId(),
        this.getName(),
        this.getTitle(),
        this.getBaseSalary()
        );
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + (publishedPapers * 1000);
    }
}

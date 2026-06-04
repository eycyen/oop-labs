public class ResearchAssistant extends AcademicStaff {
    private int projects;

    public ResearchAssistant(int id, String name, String title, double baseSalary, int projects) {
        super(id,name,title,baseSalary);
        if (projects < 0) {
            throw new IllegalArgumentException("Projects cannot be less than zero");
        }
        this.projects = projects;
    }

    public void setProjects(int projects) {
        if (projects < 0) {
            throw new IllegalArgumentException("Projects cannot be less than zero");
        }
        this.projects = projects;
    }

    public int getProjects() {
        return this.projects;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Research Assistant: %d | %s | %s | Salary: %.1f\n",
        this.getId(),
        this.getName(),
        this.getTitle(),
        this.getBaseSalary()
        );
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + (projects * 500);
    }
}

import java.util.ArrayList;

public class UniversityRepository<T> {
    private ArrayList<T> items;

    public UniversityRepository(ArrayList<T> items) {
        this.items = items; 
    }

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public void displayAll() {
        for (T p : items) {
            if (p instanceof Person) {
                Person person = (Person) p;
                person.displayInfo();
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }
}

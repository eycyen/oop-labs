import java.security.SecureRandom;

public class GameCharacter {
    private String name;
    private int health;

    public GameCharacter(String name, int health) {
        this.name = name;
        if (health < 0) {
            this.health = 0;
        }
        else {
            this.health = health;
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } 
        else {
            this.health = health; 
        }
    }

    private SecureRandom random = new SecureRandom();

    public void heal(int healAmount) {
        setHealth(getHealth() + healAmount);
    }

    public void takeDamage(int damageAmount) {
        setHealth(getHealth() - damageAmount);
    }

    public void attack(GameCharacter target) {
        int damage = random.nextInt(11);
        System.out.printf("%s attacks %s for %s damage. ",getName(),target.getName(),damage);

        if (damage == 0 || damage == 5 || damage == 10) {
            int healAmount = random.nextInt(11);
            System.out.printf("(Triggered heal: %s heals %s) ",target.getName(),healAmount);
            target.heal(healAmount);
        }
        target.takeDamage(damage);
    }

    @Override
    public String toString() {
        return String.format("Status -> Name: %s, Health: %s",getName(),getHealth());
    }
}

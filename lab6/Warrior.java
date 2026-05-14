import java.security.SecureRandom;

public class Warrior extends GameCharacter {
    private int strength;
    private final int initialStrength;

    public Warrior(String name, int health, int initialStrength) {
        super(name, health);
        if (initialStrength < 0) {
            this.initialStrength = 0;
        }
        else {
            this.initialStrength = initialStrength;
        }

        this.strength = this.initialStrength;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if (strength < 0) {
            this.strength = 0;
        }
        else {
            this.strength = strength;
        }
    }

    SecureRandom random = new SecureRandom();

    @Override
    public void attack(GameCharacter target) {
        super.attack(target);
        int reduceStrengthAmount = random.nextInt(initialStrength+1);
        setStrength(strength-reduceStrengthAmount);
        System.out.printf("[Warrior strength -%s, now %s]\n",reduceStrengthAmount,getStrength());
    }

    @Override
    public String toString() {
        return String.format("%s, Strength: %s",super.toString(),getStrength()); 
    }
}

import java.security.SecureRandom;

public class Wizard extends GameCharacter {
    private int mana;
    private final int initialMana;

    public Wizard(String name, int health, int initialMana) {
        super(name, health);
        if (initialMana < 0) {
            this.initialMana = 0;
        }
        else if (initialMana > 100) {
            this.initialMana = 100;
        }
        else {
            this.initialMana = initialMana;
        }

        this.mana = this.initialMana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            this.mana = 0;
        }
        else if (mana > 100) {
            this.mana = 100;
        }
        else {
            this.mana = mana;
        }
    }

    SecureRandom random = new SecureRandom();

    @Override
    public void attack(GameCharacter target) {
        super.attack(target);
        int reduceManaAmount = random.nextInt(initialMana+1);
        setMana(mana-reduceManaAmount);
        System.out.printf("[Wizard mana -%s, now %s]\n",reduceManaAmount,getMana());
    }

    @Override
    public String toString() {
        return String.format("%s, Mana: %s",super.toString(),getMana()); 
    }
}

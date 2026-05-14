import java.util.ArrayList;
import java.util.Scanner;

public class DuelRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter Wizard name: ");
        String wizardName = scanner.nextLine();
        System.out.printf("Enter Wizard health: ");
        int wizardHealth = scanner.nextInt();
        System.out.printf("Enter Wizard mana (0-100): ");
        int wizardMana = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("Enter Warrior name: ");
        String warriorName = scanner.nextLine();
        System.out.printf("Enter Warrior health: ");
        int warriorHealth = scanner.nextInt();
        System.out.printf("Enter Warrior strength (>=0): ");
        int warriorStrength = scanner.nextInt();
        System.out.println();

        scanner.close();

        Wizard wizard = new Wizard(wizardName,wizardHealth,wizardMana);
        Warrior warrior = new Warrior(warriorName, warriorHealth, warriorStrength);

        ArrayList<GameCharacter> characters = new ArrayList<>();
        characters.add(wizard);
        characters.add(warrior);

        System.out.println("--- Initial Status ---");
        for (GameCharacter character : characters) {
            if (character instanceof Wizard) {
                Wizard w = (Wizard) character;
                System.out.printf("Name: %s, Health: %s, Mana: %s\n", w.getName(), w.getHealth(), w.getMana());
            } else if (character instanceof Warrior) {
                Warrior w = (Warrior) character;
                System.out.printf("Name: %s, Health: %s, Strength: %s\n", w.getName(), w.getHealth(), w.getStrength());
            }
        }
        System.out.println();

        System.out.println("--- Duel Begins ---");
        while (wizard.getMana() > 0 && warrior.getStrength() > 0 && wizard.getHealth() > 0 && warrior.getHealth() > 0) {
            wizard.attack(warrior);
            System.out.println(wizard);
            System.out.println(warrior);
            System.out.println();

            if (wizard.getMana() <= 0 || warrior.getStrength() <= 0 || wizard.getHealth() <= 0 || warrior.getHealth() <= 0) {
                break;
            }

            warrior.attack(wizard);
            System.out.println(wizard);
            System.out.println(warrior);
            System.out.println();
        }

        System.out.println("--- Duel Ends ---");
        if (wizard.getHealth() <= 0 || wizard.getMana() <= 0) {
            System.out.printf("%s loses (health or mana reached 0). %s wins.\n",wizard.getName(),warrior.getName());
        }
        else if (warrior.getHealth() <= 0 || warrior.getStrength() <= 0) {
            System.out.printf("%s loses (health or strength reached 0). %s wins.\n",warrior.getName(),wizard.getName());
        }
        }
    }

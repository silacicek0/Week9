package week9;

import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members = new ArrayList<>();

    public boolean addCharacter(GameCharacter character) {
        if (character == null) {
            System.out.println("Cannot add a null character.");
            return false;
        }
        if (findByName(character.getName()) != null) {
            System.out.println("Cannot add " + character.getName()
                    + ": a character with the same name is already in the team.");
            return false;
        }
        members.add(character);
        System.out.println(character.getName() + " joined the team.");
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter found = findByName(name);
        if (found == null) {
            System.out.println("Cannot remove: " + name + " was not found in the team.");
            return false;
        }
        members.remove(found);
        System.out.println(name + " was removed from the team.");
        return true;
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

     
    public int attackAll(GameCharacter target) {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Attacker) {
                int dealt = ((Attacker) c).attack(target);
                System.out.println(c.getName() + " attacks " + target.getName()
                        + " and deals " + dealt + " damage.");
                total += dealt;
            }
        }
        return total;
    }

     
    public int healTeam() {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Supporter) {
                GameCharacter weakest = findLowestHealthRatio();
                if (weakest == null) {
                    continue;
                }
                if (weakest.getHealth() == weakest.getMaxHealth()) {
                    System.out.println("Everyone is at full health, " + c.getName() + " does not heal.");
                    continue;
                }
                int restored = ((Supporter) c).heal(weakest);
                System.out.println(c.getName() + " heals " + weakest.getName()
                        + " and restores " + restored + " health.");
                total += restored;
            }
        }
        return total;
    }

    private GameCharacter findLowestHealthRatio() {
        GameCharacter lowest = null;
        double lowestRatio = Double.MAX_VALUE;
        for (GameCharacter c : members) {
            if (c.isAlive()) {
                double ratio = (double) c.getHealth() / c.getMaxHealth();
                if (ratio < lowestRatio) {
                    lowestRatio = ratio;
                    lowest = c;
                }
            }
        }
        return lowest;
    }

     
    public int getTotalPower() {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive()) {
                total += c.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("----- TEAM -----");
        if (members.isEmpty()) {
            System.out.println("(empty)");
        }
        for (GameCharacter c : members) {
            System.out.println(c.getCharacterInfo());
        }
        System.out.println("Total power (alive members): " + getTotalPower());
        System.out.println("----------------");
    }
}
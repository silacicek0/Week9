package week9;

public class Warrior extends GameCharacter implements Attacker {
    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        this.armor = armor;
    }

    public int getArmor() { return armor; }

    @Override
    public int calculatePower() {
        return getLevel() * 10 + armor * 2;
    }

    @Override
    public int takeDamage(int damage) {
         
        int reduced = Math.max(1, damage - armor / 2);
        return super.takeDamage(reduced);
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive()) {
            System.out.println(getName() + " is defeated and cannot attack.");
            return 0;
        }
        if (!target.isAlive()) {
            System.out.println(getName() + " cannot attack " + target.getName()
                    + " because the target is already defeated.");
            return 0;
        }
        int damage = calculatePower() / 4;
        return target.takeDamage(damage);
    }

    @Override
    public String getCharacterInfo() {
        return "[Warrior] " + getName() + " | Level: " + getLevel()
                + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Armor: " + armor + " | Power: " + calculatePower()
                + " | " + (isAlive() ? "Alive" : "Defeated");
    }
}
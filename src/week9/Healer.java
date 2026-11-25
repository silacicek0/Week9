package week9;

public class Healer extends GameCharacter implements Supporter {
    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = healAmount;
    }

    public int getHealAmount() { return healAmount; }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive()) {
            System.out.println(getName() + " is defeated and cannot heal.");
            return 0;
        }
        if (!target.isAlive()) {
            System.out.println(getName() + " cannot heal " + target.getName()
                    + " because the target is defeated.");
            return 0;
        }
        return target.restoreHealth(healAmount + getLevel());
    }

    @Override
    public String getCharacterInfo() {
        return "[Healer] " + getName() + " | Level: " + getLevel()
                + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | HealAmount: " + healAmount + " | Power: " + calculatePower()
                + " | " + (isAlive() ? "Alive" : "Defeated");
    }
}
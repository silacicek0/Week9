package week9;

public class Mage extends GameCharacter implements Attacker, Supporter {
    private static final int ATTACK_COST = 20;
    private static final int HEAL_COST = 15;

    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = mana;
    }

    public int getMana() { return mana; }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
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
        if (mana < ATTACK_COST) {
            System.out.println(getName() + " does not have enough mana to attack! (mana: " + mana + ")");
            return 0;
        }
        int damage = calculatePower() / 3;  
        mana -= ATTACK_COST;
        return target.takeDamage(damage);
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
        if (mana < HEAL_COST) {
            System.out.println(getName() + " does not have enough mana to heal! (mana: " + mana + ")");
            return 0;
        }
        mana -= HEAL_COST;
        return target.restoreHealth(getLevel() * 2);
    }

    @Override
    public String getCharacterInfo() {
        return "[Mage] " + getName() + " | Level: " + getLevel()
                + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Mana: " + mana + " | Power: " + calculatePower()
                + " | " + (isAlive() ? "Alive" : "Defeated");
    }
}
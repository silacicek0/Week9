package week9;

public abstract class GameCharacter {
    private String name;
    private int level;
    private int health;
    private int maxHealth;

    public GameCharacter(String name, int level, int maxHealth) {
        this.name = name;

        if (level < 1 || level > 100) {
            System.out.println("Warning: Invalid level (" + level + ") for " + name
                    + ". Level set to 1.");
            this.level = 1;
        } else {
            this.level = level;
        }

        if (maxHealth <= 0) {
            System.out.println("Warning: Invalid maxHealth (" + maxHealth + ") for " + name
                    + ". maxHealth set to 100.");
            this.maxHealth = 100;
        } else {
            this.maxHealth = maxHealth;
        }

        this.health = this.maxHealth;  
    }

    
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

     
    public boolean isAlive() {
        return health > 0;
    }

     
    public int takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) {
            return 0;
        }
        int realDamage = Math.min(damage, health);
        health -= realDamage;
        return realDamage;
    }

    
    public int restoreHealth(int amount) {
        if (!isAlive()) {
            System.out.println(name + " is defeated and cannot be healed.");
            return 0;
        }
        if (amount <= 0) {
            return 0;
        }
        int realRestore = Math.min(amount, maxHealth - health);
        health += realRestore;
        return realRestore;
    }

     
    public abstract String getCharacterInfo();
    public abstract int calculatePower();
}
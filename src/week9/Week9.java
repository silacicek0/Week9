package week9;

public class Week9 {
    public static void main(String[] args) {
        Team team = new Team();

        System.out.println("=== Creating the team ===");
        Warrior arthur = new Warrior("Arthur", 20, 300, 30);
        Mage merlin = new Mage("Merlin", 18, 150, 80);
        Healer elara = new Healer("Elara", 15, 180, 25);
        team.addCharacter(arthur);
        team.addCharacter(merlin);
        team.addCharacter(elara);

        Warrior golem = new Warrior("Golem", 30, 600, 40);
        System.out.println("\nEnemy: " + golem.getCharacterInfo());
        team.displayTeam();

        System.out.println("\n=== Battle ===");
        GameCharacter[] targets = {arthur, merlin, elara};
        for (int round = 1; round <= 3; round++) {
            System.out.println("\n--- Round " + round + " ---");

            System.out.println("[Team attacks]");
            team.attackAll(golem);

            System.out.println("[Enemy attacks]");
            GameCharacter victim = targets[round - 1];
            int dealt = golem.attack(victim);
            System.out.println(golem.getName() + " attacks " + victim.getName()
                    + " and deals " + dealt + " damage.");

            System.out.println("[Team heals]");
            team.healTeam();

            System.out.println(golem.getCharacterInfo());
            team.displayTeam();
        }

        System.out.println("\n=== Remove / Find ===");
        team.removeCharacter("Elara");
        team.removeCharacter("Nobody");
        GameCharacter found = team.findByName("Merlin");
        System.out.println(found != null ? "Found: " + found.getCharacterInfo() : "Merlin not found.");
        System.out.println(team.findByName("Elara") == null ? "Elara is not in the team anymore." : "Elara still exists?");
        team.displayTeam();

        System.out.println("\n=== Invalid input tests ===");
        team.addCharacter(new Warrior("Arthur", 5, 100, 10));    
        Mage wrongLevel = new Mage("Rookie", 150, 100, 50);       
        team.addCharacter(wrongLevel);
        Healer wrongHealth = new Healer("Medic", 10, -5, 20);     
        team.addCharacter(wrongHealth);
        team.displayTeam();

        System.out.println("\n=== Defeated character tests ===");
        wrongLevel.takeDamage(9999);
        System.out.println(wrongLevel.getName() + " alive? " + wrongLevel.isAlive());
        System.out.println("Heal defeated: " + wrongHealth.heal(wrongLevel));
        System.out.println("Attack defeated: " + arthur.attack(wrongLevel));
        team.displayTeam();
    }
}